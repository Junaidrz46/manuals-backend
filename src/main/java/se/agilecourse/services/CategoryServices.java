package se.agilecourse.services;

import se.agilecourse.model.Brand;
import se.agilecourse.model.Category;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryServices {

    Optional<Category> findById(String id);
    List<Category> findAllCategories();
    Category saveCategory(Category category);
    List<Product> getProductsByBrandId(String brandId);

    Brand saveBrandByCategory(Brand brand,String categoryId);
    public List<Brand> getBrandsByCategory(String categoryID);

    Product saveProductByBrand(Product product , String BrandId);
    Product saveProduct(Product product);
    Optional<Product> getProductById(String Id);
    List<Product> getProductsByBrand(String bandName);
    List<Product> getAllProuducts();

    Material saveMaterial(Material material);
    Material saveMaterialByProduct(Material material , String ProductId);
    Optional<Material> getMaterialById(String id);
    List<Material> getMaterialByProductId(String prouductId);
    List<Material> getAllMaterials();



}
