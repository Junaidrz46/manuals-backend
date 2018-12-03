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

    @RequestMapping(value="/findCategoryById",method = RequestMethod.GET)
    public Optional<Category> findCategoryById (@RequestParam ("id") String id){
        return categoryServices.findById(id);
    }

    @PostMapping("/saveMaterialByProductId")
    public Material saveMaterial(@RequestBody WrapperMaterial wrapperMaterial){
        return categoryServices.saveMaterialByProduct(wrapperMaterial.getMaterial(),wrapperMaterial.getProductId());
    }

    @RequestMapping(value="/findMaterialByProductId",method = RequestMethod.GET)
    public List<Material> getMaterialsByProductId(@RequestParam("ProductId") String productId){
        return categoryServices.getMaterialByProductId(productId);
    }

    @RequestMapping(value="/findAllMaterials",method = RequestMethod.GET)
    public List<Material> getAllMaterials(){
        return categoryServices.getAllMaterials();
    }




    @PostMapping("/saveProductByCatergoryId")
    public Product saveProductByCatergoryId(@RequestBody WrapperProduct wrapperProduct){
        logger.info("cat ID : "+wrapperProduct.getCategoryId());
        logger.info("Product "+wrapperProduct.getProduct().getName());
        return categoryServices.saveProductByCategory(wrapperProduct.getProduct(),wrapperProduct.getCategoryId());
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

    @PostMapping(value="/createProductByCategoryIdAndCompanyId")
    public Product createProductByCategoryIdAndCompanyId(@RequestBody WrapperProduct wrapperProduct){
        return categoryServices.createProductByCategoryIdAndCompanyId(wrapperProduct.getProduct(),wrapperProduct.getCategoryId(),wrapperProduct.getCompany());
    }

}
