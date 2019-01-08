package se.agilecourse.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.agilecourse.exceptions.*;
import se.agilecourse.model.*;
import se.agilecourse.repository.*;
import se.agilecourse.repository.CategoryRepository;
import se.agilecourse.repository.CompanyRepository;
import se.agilecourse.repository.MaterialRepository;
import se.agilecourse.repository.ProductRepository;
import se.agilecourse.services.CategoryServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServicesImpl implements CategoryServices {

    private final Logger logger = LoggerFactory.getLogger(CategoryServicesImpl.class);

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    UserRatedMaterialsRepository userRatedMaterialsRepository;



    @Override
    public Optional<Category> findById(String Id) {
        return categoryRepository.findById(Id);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<ProductMini> getProductsByCategoryId(String cid) {
        List<Product> list=categoryRepository.findProductsByCategoryId(cid);
        List<ProductMini> listMini=new ArrayList<>();
        for(Product product:list){
            String id=product.getId();
            String name=product.getName();
            ProductMini productMini=new ProductMini();
            productMini.setId(id);
            productMini.setName(name);
            listMini.add(productMini);
        }
        return listMini;
    }



    @Override
    public Company saveCompany(Company company) {

        return companyRepository.save(company);

    }

    @Override
    public Optional<Product> getProductById(String Id) {
        return productRepository.findById(Id);
    }

    @Override
    public List<Product> getAllProuducts() {
        return productRepository.findAll();
    }

    @Override
    public Material saveMaterial(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public Material saveMaterialByProduct(Material material, String ProductId) {
        Material saveMaterial = materialRepository.save(material);
        logger.info("Save Material "+saveMaterial.getName());
        logger.info("Product ID : "+ProductId);
        Optional<Product> product = productRepository.findById(ProductId);
        List<Material> materialList = product.get().getMaterials();
        if(materialList == null){
            materialList = new ArrayList<Material>();
        }
        materialList.add(saveMaterial);
        product.get().setMaterials(materialList);
        productRepository.save(product.get());
        return saveMaterial;
    }

    @Override
    public Material getMaterialById(String id) {
        Optional<Material> material=materialRepository.findById(id);
        if(!material.isPresent())
            throw new MaterialNotFoundException("there is no such material!");
        String materialId=material.get().getId();
        String averageRate= userRatedMaterialsRepository.getAverageRateForMaterial(materialId);
        material.get().setAverageRate(averageRate);
        Material saveMaterial=materialRepository.save(material.get());
        return saveMaterial;
    }

    @Override
    public List<Material> getMaterialByProductId(String prouductId) {

        return materialRepository.findMaterialsByProductId(prouductId);
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }
    @Override
    public Product saveProductByCategoryAndCompany(String categoryId,String companyId,Product product)
            throws CompanyIdMismatchException {

        Product saveProduct=null;
        saveProduct=new Product(product.getProductNumber(),product.getName(),product.getDescription(),product.getCompanyId(),product.getCategoryId(),product.getProfileImage());

        if(product.getCompanyId() != null && !product.getCompanyId().equalsIgnoreCase("")){
            if(!product.getCompanyId().equalsIgnoreCase(companyId)){
                throw new CompanyIdMismatchException("CompanyId Mismatched");
            }else{
                logger.debug("company ID matched "+companyId+" : "+product.getCompanyId());
                saveProduct = productRepository.save(saveProduct);
            }
        }else{
            product.setCompanyId(companyId);
            saveProduct = productRepository.save(saveProduct);
        }

        Optional<Category> categoryFound = categoryRepository.findById(categoryId);

        if(!categoryFound.isPresent()){
            throw new CategoryNotFoundException("Category with Id "+categoryId+" not found");
        }

        List<Product> productslistOfCategory = categoryFound.get().getProducts();
        if(productslistOfCategory == null){
            productslistOfCategory = new ArrayList<Product>();
        }
        productslistOfCategory.add(saveProduct);
        categoryFound.get().setProducts(productslistOfCategory);
        categoryRepository.save(categoryFound.get());
        return saveProduct;

    }

    public Optional<Company> getCompanyById(String companyId){
        return companyRepository.findById(companyId);
    }

    public Optional<Category> getCategoryById(String categoryId){
        return categoryRepository.findById(categoryId);
    }

    @Override
    public List<Product> getProductsByProductNo(String productNo) {
        return productRepository.findByProductNumber(productNo);
    }

    @Override
    public List<Product> getProductsByCompanyId(String companyId) {
        return productRepository.findByCompanyId(companyId);
    }

    @Override
    public List<Product> getProductsByName(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public Material deleteMaterialById(String Id) throws MaterialNotFoundException {
        Optional<Material> material = materialRepository.findById(Id);
        if(!material.isPresent()){
            throw new MaterialNotFoundException("Material with given Id not found");
        }
        materialRepository.delete(material.get());
        return material.get();
    }

    public Material saveMaterialAsProfileImage(String productId, Material material){
        materialRepository.save(material);
        Optional<Product> savedProduct = productRepository.findById(productId);
        savedProduct.get().setProfileImage(material.getId());
        productRepository.save(savedProduct.get());
        return material;
    }
   public Material updateMaterialDescrption(String materialId , String descritpion){
        Optional<Material> material = materialRepository.findById(materialId);
        material.get().setDescription(descritpion);
        materialRepository.save(material.get());
        return  material.get();
    }

    @Override
    public List<Product> getMostRecentlyProducts() {

        return productRepository.getMostRecentlyAddedProducts();
    }

    @Override
    public Optional<Material> increaseAccessCounterForMaterial(String materialId) {
        Optional<Material> material=materialRepository.findById(materialId);

        if(!material.isPresent())
            throw new GeneratRunTimeException("material id is invalid!");
        Integer counter=0;
        if(material.get().getAccessCounter() == null || material.get().getAccessCounter() == 0 ){
            counter=0;
        }else{
           counter=material.get().getAccessCounter();
        }
        material.get().setAccessCounter(++counter);
        materialRepository.save(material.get());
        return material;
    }

    @Override
    public Optional<Product> increaseLikedCounterForProduct(String productId) {
        logger.info("ProdouctID::"+productId);
        Optional<Product> product=productRepository.findById(productId);
        if(!product.isPresent())
            throw new GeneratRunTimeException("product id is invalid!");
        Integer counter=0;
        if(product.get().getLikedCounter() == null || product.get().getLikedCounter() == 0 ){
            counter=0;
        }else{
            counter=product.get().getLikedCounter();
        }

        product.get().setLikedCounter(++counter);
        product.get().setPersisted(true);
        productRepository.save(product.get());
        return product;
    }

    @Override
    public Optional<Product> decreaseLikedCounterForProduct(String productId) {
        Optional<Product> product=productRepository.findById(productId);
        if(!product.isPresent())
            throw new GeneratRunTimeException("product id is invalid!");
        Integer counter=0;
        if(product.get().getLikedCounter() == null || product.get().getLikedCounter() <= 0 ){
            counter=0;
        }else{
            counter=product.get().getLikedCounter();
        }
        if(counter<=0){
            product.get().setLikedCounter(counter);
        }

        else{
            product.get().setLikedCounter(--counter);
        }
        productRepository.save(product.get());
        return product;
    }

    @Override
    public List<Product> getProductsByThree(String condition) {//combined productNumber,
        //companyId
        //name
        return productRepository.findAll(condition);
    }





}
