package se.agilecourse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.agilecourse.model.*;
import se.agilecourse.services.CategoryServices;
import se.agilecourse.services.CompanyServices;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8888/", maxAge = 3600)
@RestController()
@RequestMapping("/rest/categories")
public class CategoryController {

    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryServices categoryServices;

    @Autowired
    CompanyServices companyServices;

    @PostMapping("/saveProductByCompanyIdandCategoryId")
    public Product saveProductByCompanyIdandCategoryId(@RequestBody WrapperProduct wrapperProduct){
        logger.info("Company ID : "+wrapperProduct.getCompanyId());
        logger.info("Category ID : "+wrapperProduct.getCategoryId());
        logger.info("Product "+wrapperProduct.getProduct().getName());
        logger.info("Product NUMBER"+wrapperProduct.getProduct().getProductNumber());
        return categoryServices.saveProductByCategoryandCompany(wrapperProduct.getProduct(), 
        wrapperProduct.getCategoryId(),wrapperProduct.getCompanyId());
    }



    @GetMapping("/findAllCategories")
    public List<Category> getAllCategories() {
        logger.debug("Get users from all : "+categoryServices.findAllCategories());
        return categoryServices.findAllCategories();
    }

    @PostMapping("/saveCategory")
    public Category saveCategory(@RequestBody Category category){
        return categoryServices.saveCategory(category);
    }

    @GetMapping("/findAllCompanies")
    public List<Company> getAllCompanies() {
        logger.debug("Get users from all : "+companyServices.findAllCompanies());
        return companyServices.findAllCompanies();
    }

    @PostMapping("/saveCompany")
    public Company saveCompany(@RequestBody Company company){
        return companyServices.saveCompany(company);
    }

    @PostMapping("/saveProduct")
    public Product saveProduct(@RequestBody Product product){
        return categoryServices.saveProduct(product);
    }
    
    @PostMapping("/saveProductByCompanyId")
    public Product saveProductByCompanyId(@RequestBody WrapperProduct wrapperProduct){
        logger.info("Company ID : "+wrapperProduct.getCompanyId());
        logger.info("Product "+wrapperProduct.getProduct().getName());
        logger.info("Product NUMBER"+wrapperProduct.getProduct().getProductNumber());
        return companyServices.saveProductByCompany(wrapperProduct.getProduct(),wrapperProduct.getCompanyId());
    }

    @PostMapping("/saveMaterialByProductId")
    public Material saveMaterial(@RequestBody WrapperMaterial wrapperMaterial){
        return categoryServices.saveMaterialByProduct(wrapperMaterial.getMaterial(),wrapperMaterial.getProductId());
    }

    @RequestMapping(value="/findCategoryById",method = RequestMethod.GET)
    public Optional<Category> findCategoryById (@RequestParam ("id") String id){
        return categoryServices.findByCategoryId(id);
    }


    @RequestMapping(value="/findProductById",method = RequestMethod.GET)
    public Optional<Product> getProdctById(@RequestParam("ProductId") String productId){
        return categoryServices.getProductById(productId);
    }

    @RequestMapping(value="/findProductByProductNumber",method = RequestMethod.GET)
    public Optional<Product> getProductByProductNumber(@RequestParam("ProductNumber") String productNumber){
        return categoryServices.getProductByProductNumber(productNumber);
    }

    @RequestMapping(value="/findAllProducts",method = RequestMethod.GET)
    public List<Product> getAllProdcts(){
        return categoryServices.getAllProuducts();
    }

    @RequestMapping(value="/findMaterialByProductId",method = RequestMethod.GET)
    public List<Material> getMaterialsByProductId(@RequestParam("ProductId") String productId){
        return categoryServices.getMaterialByProductId(productId);
    }

    @RequestMapping(value="/findAllMaterials",method = RequestMethod.GET)
    public List<Material> getAllMaterials(){
        return categoryServices.getAllMaterials();
    }

    // @PostMapping("/saveProductByCategory")
    // public Product saveProductByCategoryId(@RequestBody WrapperProduct wrapperProduct){
    //     logger.info("Category ID : "+wrapperProduct.getCategoryId());
    //     logger.info("Product "+wrapperProduct.getProduct().getName());
    //     logger.info("Product "+wrapperProduct.getProduct().getProductNumber());
    //     return categoryServices.saveProductByCategory(wrapperProduct.getProduct(),wrapperProduct.getCategoryId());
    // }

    // @RequestMapping(value="/findProductByCompany",method = RequestMethod.GET)
    // public List<Product> getProductByCompany(@RequestParam("CompanyName") String companyName){
    //     return companyServices.getProductsByCompany(companyName);
    // }

    // @PostMapping("/saveBrandByCategoryId")
    // public Brand saveBrandByCatergoryId(@RequestBody WrapperBrand wrapperBrand){
    //     logger.info("Category ID : "+wrapperBrand.getCategoryId());
    //     logger.info("Brand : "+wrapperBrand.getBrand().getName());
    //     return categoryServices.saveBrandByCategory(wrapperBrand.getBrand(),wrapperBrand.getCategoryId());
    // }

    // @PostMapping("/saveProductByBrandId")
    // public Product saveProductByCatergoryId(@RequestBody WrapperProduct wrapperProduct){
    //     logger.info("Brand ID : "+wrapperProduct.getBrandId());
    //     logger.info("Product "+wrapperProduct.getProduct().getName());
    //     return categoryServices.saveProductByBrand(wrapperProduct.getProduct(),wrapperProduct.getBrandId());
    // }

    // @RequestMapping(value="/findBrandByCategoryId",method = RequestMethod.GET)
    // public List<Brand> getBrandsByCategory(@RequestParam("cateogryId") String categoryId){
    //     return categoryServices.getBrandsByCategory(categoryId);
    // }

    // @RequestMapping(value="/findProductByBrand",method = RequestMethod.GET)
    // public List<Product> getProductByBrand(@RequestParam("BrandName") String brandName){
    //     return categoryServices.getProductsByBrand(brandName);
    // }
}
