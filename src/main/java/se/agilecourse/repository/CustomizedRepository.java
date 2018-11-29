package se.agilecourse.repository;

import se.agilecourse.model.Brand;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;

import java.util.List;

public interface CustomizedRepository {

    public List<Brand> fidnBrandByCategoryId(String categoryId);
    public List<Product> findProductsByBrandId(String brandId);
    public List<Material> findMaterialsByProductId(String productId);
    public List<Product> findProductsByBrand(String brandName);
}
