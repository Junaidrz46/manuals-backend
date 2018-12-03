package se.agilecourse.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.agilecourse.model.Brand;
import se.agilecourse.model.Category;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;
import se.agilecourse.repository.*;
import se.agilecourse.services.CategoryServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServicesImpl implements CategoryServices {

    private final Logger logger = LoggerFactory.getLogger(CategoryServicesImpl.class);

    @Autowired
    BrandRepository brandRepository;

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
    public List<Product> getProductsByBrandId(String brandId) {
        return productRepository.findProductsByBrand(brandId);
    }

    @Override
    public Brand saveBrandByCategory(Brand brand, String categoryId) {
        Brand saveBrand = brandRepository.save(brand);
        Optional<Category> category = categoryRepository.findById(categoryId);
        List<Brand> brandList = category.get().getBrands();
        if(brandList == null){
            brandList = new ArrayList<Brand>();
        }
        brandList.add(brand);
        category.get().setBrands(brandList);
        categoryRepository.save(category.get());

        return saveBrand;
    }


    @Override
    public Product saveProductByBrand(Product product , String brandId) {
        Product saveProduct = productRepository.save(product);
        Optional<Brand> brand = brandRepository.findById(brandId);
        List<Product> productslist = brand.get().getProducts();
        if(productslist == null){
            productslist = new ArrayList<Product>();
        }
        productslist.add(saveProduct);
        brand.get().setProducts(productslist);
        brandRepository.save(brand.get());
        return saveProduct;
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
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

    @Override
    public List<Product> getProductsByBrand(String bandName) {
        return productRepository.findByBrand(bandName);
    }

    public List<Brand> getBrandsByCategory(String categoryID){
        return categoryRepository.fidnBrandByCategoryId(categoryID);
    }



}
