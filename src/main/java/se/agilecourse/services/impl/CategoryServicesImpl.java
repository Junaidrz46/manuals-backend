package se.agilecourse.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.agilecourse.model.Category;
import se.agilecourse.model.Company;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;
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
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    CompanyRepository companyRepository;

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



    @Override
    public List<Product> getProductsByCategoryId(String categoryId) {
        return productRepository.findProductsByCategoryid(categoryId);
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
    public Product saveProductByCategory(Product product , String categoryId) {//API maybe need to be deleted
        Product saveProduct = productRepository.save(product);
        Optional<Category> category = categoryRepository.findById(categoryId);
        List<Product> productslist = category.get().getProducts();
        if(productslist == null){
            productslist = new ArrayList<Product>();
        }
        productslist.add(saveProduct);
        category.get().setProducts(productslist);
        categoryRepository.save(category.get());
        return saveProduct;
    }

    @Override
    public Product createProductByCategoryIdAndCompanyId(Product product, String categoryId, Company company) {
        product.setBrand(company.getName());
        Product saveProduct = productRepository.save(product);
        Optional<Category> categoryFound = categoryRepository.findById(categoryId);
        Optional<Company> companyFound = companyRepository.findById(company.getId());

        List<Product> productslistOfCategory = categoryFound.get().getProducts();
        if(productslistOfCategory == null){
            productslistOfCategory = new ArrayList<Product>();
        }

        List<Product> productslistOfCompany = companyFound.get().getProducts();
        if(productslistOfCompany == null){
            productslistOfCompany = new ArrayList<Product>();
        }

        productslistOfCategory.add(saveProduct);

        productslistOfCompany.add(saveProduct);

        categoryFound.get().setProducts(productslistOfCategory);

        companyFound.get().setProducts(productslistOfCompany);

        categoryRepository.save(categoryFound.get());

        companyRepository.save(companyFound.get());

        return saveProduct;

    }








}
