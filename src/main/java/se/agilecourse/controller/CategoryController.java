package se.agilecourse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.agilecourse.model.*;
import se.agilecourse.services.CategoryServices;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8888/", maxAge = 3600)
@RestController()
@RequestMapping("/rest/categories")
public class CategoryController {

    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryServices categoryServices;


    @GetMapping("/findAllCategories")
    public List<Category> getAll() {
        logger.debug("Get users from all : "+categoryServices.findAllCategories());

        return categoryServices.findAllCategories();
    }

    @PostMapping("/saveCategory")
    public Category saveCategory(@RequestBody Category category){
        return categoryServices.saveCategory(category);
    }

    @PostMapping("/saveCompany")
    public Company saveCompany(@RequestBody Company company){
        return categoryServices.saveCompany(company);
    }

    @PostMapping("/saveProductByCatergoryId")
    public Product saveProductByCatergoryId(@RequestBody WrapperProduct wrapperProduct){
        logger.info("cat ID : "+wrapperProduct.getCategoryId());
        logger.info("Product "+wrapperProduct.getProduct().getName());
        return categoryServices.saveProductByCategory(wrapperProduct.getProduct(),wrapperProduct.getCategoryId());
    }

    @PostMapping("/saveProductByCategroyAndCompany")
    public Product saveProductByCategoryAndCompany(@RequestBody WrapperProduct wrapperProduct){
        return categoryServices.saveProductByCompany(wrapperProduct.getCategoryId(),
                wrapperProduct.getCompanyId(),wrapperProduct.getProduct());
    }



    @PostMapping("/saveMaterialByProductId")
    public Material saveMaterial(@RequestBody WrapperMaterial wrapperMaterial){
        return categoryServices.saveMaterialByProduct(wrapperMaterial.getMaterial(),wrapperMaterial.getProductId());
    }

    @RequestMapping(value="/findCategoryById",method = RequestMethod.GET)
    public Optional<Category> findCategoryById (@RequestParam ("id") String id){
        return categoryServices.findById(id);
    }


    @RequestMapping(value="/findMaterialByProductId",method = RequestMethod.GET)
    public List<Material> getMaterialsByProductId(@RequestParam("ProductId") String productId){
        return categoryServices.getMaterialByProductId(productId);
    }

    @RequestMapping(value="/findProductByCategoryId",method = RequestMethod.GET)
    public  List<Product> getProductsByCategoryId(@RequestParam("CategoryId") String cid){
        return categoryServices.getProductsByCategoryId(cid);
    }
    @RequestMapping(value="/findProductById",method = RequestMethod.GET)
    public Optional<Product> getProdctById(@RequestParam("ProductId") String productId){
        return categoryServices.getProductById(productId);
    }

    @RequestMapping(value="/findAllProducts",method = RequestMethod.GET)
    public List<Product> getAllProdcts(){
        return categoryServices.getAllProuducts();
    }


    @RequestMapping(value="/findProductsByProductNo",method = RequestMethod.GET)
    public List<Product> getProductsByProductId(@RequestParam("productNo") String productNo){
        return categoryServices.getProductsByProductNo(productNo);
    }
    @RequestMapping(value="/findProductsByCompanyId",method = RequestMethod.GET)
    public List<Product> getProductsByCompanyId(@RequestParam("companyId") String brand){
        return categoryServices.getProductsByCompanyId(brand);
    }
    @RequestMapping(value="/findProductsByProductName",method = RequestMethod.GET)
    public List<Product> getProductsByProductName(@RequestParam("productName") String productName){
        return categoryServices.getProductsByName(productName);
    }



}
