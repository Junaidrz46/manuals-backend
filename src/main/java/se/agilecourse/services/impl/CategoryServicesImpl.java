package se.agilecourse.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.agilecourse.exceptions.CategoryNotFoundException;
import se.agilecourse.exceptions.CompanyIdMismatchException;
import se.agilecourse.model.Company;
import se.agilecourse.model.Category;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;
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
    public Product saveProductByCategory(Product product , String categoryId) {
        Product saveProduct=null;
        try {
            product.setCategoryId(categoryId);
            saveProduct = productRepository.save(product);
            Optional<Category> category = categoryRepository.findById(categoryId);
            List<Product> productslist = category.get().getProducts();
            if (productslist == null) {
                productslist = new ArrayList<Product>();
            }
            productslist.add(saveProduct);
            category.get().setProducts(productslist);
            categoryRepository.save(category.get());
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return saveProduct;
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
    public Optional<Material> getMaterialById(String id) {
        return materialRepository.findById(id);
    }

    @Override
    public List<Material> getMaterialByProductId(String prouductId) {
        return materialRepository.findMaterialsByProductId(prouductId);
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }
    public Product saveProductByCategoryAndCompany(String categoryId,String companyId,Product product)
            throws CompanyIdMismatchException {

        Product saveProduct=null;
        product.setCategoryId(categoryId);
        if(product.getCompanyId() != null && !product.getCompanyId().equalsIgnoreCase("")){
            if(!product.getCompanyId().equalsIgnoreCase(companyId)){
                throw new CompanyIdMismatchException("CompanyId Mismatched");
            }else{
                logger.debug("company ID matched "+companyId+" : "+product.getCompanyId());
                saveProduct = productRepository.save(product);
            }
        }else{
            product.setCompanyId(companyId);
            saveProduct = productRepository.save(product);
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




}
