package se.agilecourse.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import se.agilecourse.model.Category;
import se.agilecourse.model.Company;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;
import se.agilecourse.repository.*;
import se.agilecourse.services.CategoryServices;
import se.agilecourse.services.CompanyServices;
// import se.agilecourse.model.Brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServicesImpl implements CategoryServices, CompanyServices {

    private final Logger logger = LoggerFactory.getLogger(CategoryServicesImpl.class);

    // @Autowired
    // BrandRepository brandRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    CompanyRepository companyRepository;


    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findByCategoryId(String id) {
        return categoryRepository.findById(id);  
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Optional<Company> findByCompanyId(String id) {
        return companyRepository.findById(id);
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Product saveProductByCompany(Product product , String CompanyId) {
        Product saveProduct = productRepository.save(product);
        Optional<Company> company = companyRepository.findById(CompanyId);
        List<Product> productslist = company.get().getProducts();
        if(productslist == null){
            productslist = new ArrayList<Product>();
        }
        productslist.add(saveProduct);
        company.get().setProducts(productslist);
        companyRepository.save(company.get());
        return saveProduct;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(String Id) {
        return productRepository.findById(Id);
    }

    @Override
    public Optional<Product> getProductByProductNumber(String ProductNumber) {
        return productRepository.findByProductNumber(ProductNumber);
    }

    @Override
    public List<Product> getAllProuducts() {
        return productRepository.findAll();
    }

    // @Override
    // public List<Product> getProductsByCompany(String companyID) {
    //     return companyRepository.findProducsByCompanyId(companyID);
    // 	}

    @Override
    public List<Product> getProductsByCompanyId(String companyId) {
        return productRepository.findProducsByCompanyId(companyId);
    }

    @Override
    public List<Product> getProductsByCategoryId(String categoryId) {
        return categoryRepository.findProductsByCategoryId(categoryId);
    }

    @Override
    public Product saveProductByCategory(Product product, String CategoryId) {
        Product saveProduct = productRepository.save(product);
        Optional<Category> category = categoryRepository.findById(CategoryId);
        List<Product> productslist = category.get().getProducts();
        if(productslist == null){
            productslist = new ArrayList<Product>();
        }
        productslist.add(saveProduct);
        category.get().setProducts(productslist);
        categoryRepository.save(category.get());
        return saveProduct;    }

    @Override
    public Material saveMaterial(Material material) {
        return materialRepository.save(material);
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
    public Product saveProductByCategoryandCompany(Product product, String CategoryId, String CompanyId) {
        Product saveProduct = productRepository.save(product);
        Optional<Category> category = categoryRepository.findById(CategoryId);
        Optional<Company> company = companyRepository.findById(CompanyId);
        List<Product> productslistOfCategory = category.get().getProducts();
        if(productslistOfCategory == null){
            productslistOfCategory = new ArrayList<Product>();
        }
        List<Product> productslistOfCompany = company.get().getProducts();
        if(productslistOfCompany == null){
            productslistOfCompany = new ArrayList<Product>();
        }
        productslistOfCategory.add(saveProduct);
        productslistOfCompany.add(saveProduct);
        category.get().setProducts(productslistOfCategory);
        company.get().setProducts(productslistOfCompany);
        categoryRepository.save(category.get());
        companyRepository.save(company.get());
        return saveProduct;    }



    // @Override
    // public List<Product> getProductsByBrandId(String brandId) {
    //     return productRepository.findProductsByBrand(brandId);
    // }

    // @Override
    // public Brand saveBrandByCategory(Brand brand, String categoryId) {
    //     Brand saveBrand = brandRepository.save(brand);
    //     Optional<Category> category = categoryRepository.findById(categoryId);
    //     List<Brand> brandList = category.get().getBrands();
    //     if(brandList == null){
    //         brandList = new ArrayList<Brand>();
    //     }
    //     brandList.add(brand);
    //     category.get().setBrands(brandList);
    //     categoryRepository.save(category.get());

    //     return saveBrand;
    // }


    // @Override
    // public Product saveProductByBrand(Product product , String brandId) {
    //     Product saveProduct = productRepository.save(product);
    //     Optional<Brand> brand = brandRepository.findById(brandId);
    //     List<Product> productslist = brand.get().getProducts();
    //     if(productslist == null){
    //         productslist = new ArrayList<Product>();
    //     }
    //     productslist.add(saveProduct);
    //     brand.get().setProducts(productslist);
    //     brandRepository.save(brand.get());
    //     return saveProduct;
    // }

    // @Override
    // public List<Product> getProductsByBrand(String bandName) {
    //     return productRepository.findByBrand(bandName);
    // }

    // public List<Brand> getBrandsByCategory(String categoryID){
    //     return categoryRepository.fidnBrandByCategoryId(categoryID);
    // }

}
