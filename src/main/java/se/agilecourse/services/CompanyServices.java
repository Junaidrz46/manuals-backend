package se.agilecourse.services;

import se.agilecourse.model.Category;
import se.agilecourse.model.Company;
import se.agilecourse.model.Material;
import se.agilecourse.model.Product;
// import se.agilecourse.model.Brand;

import java.util.List;
import java.util.Optional;

public interface CompanyServices {

    Optional<Company> findByCompanyId(String Companyid);
    List<Company> findAllCompanies();
    Company saveCompany(Company company);
    List<Product> getProductsByCompanyId(String companyId);

    // Brand saveBrandByCategory(Brand brand,String categoryId);
    // public List<Brand> getBrandsByCategory(String categoryID);


    Product saveProductByCompany(Product product , String CompanyId);
    List<Product> getProductsByCompany(String companyName);




}
