package se.agilecourse.repository;

import se.agilecourse.model.Material;
import se.agilecourse.model.Product;
// import se.agilecourse.model.Brand;

import java.util.List;

public interface CustomizedRepository {

    public List<Product> fidnProductsByCategoryId(String categoryId);
    public List<Product> findProducsByCompanyId(String companyId);
    public List<Material> findMaterialsByProductId(String productId);
    public List<Product> findProductsByCompany(String companyName);
    // public List<Brand> fidnBrandByCategoryId(String categoryId);
    // public List<Product> findProductByBrandId(String brandId);
    // public List<Product> findProductsByBrand(String brandName);
}
