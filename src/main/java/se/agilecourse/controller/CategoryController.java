package se.agilecourse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.agilecourse.model.*;
import se.agilecourse.services.CategoryServices;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/saveProductByCategroyAndCompany")
    public Product saveProductByCategoryAndCompany(@RequestBody WrapperProduct wrapperProduct){
        return categoryServices.saveProductByCategoryAndCompany(wrapperProduct.getCategoryId(),
                wrapperProduct.getCompanyId(),wrapperProduct.getProduct());
    }

    @PostMapping("/saveMaterialByProductId")
    public Material saveMaterial(@RequestBody WrapperMaterial wrapperMaterial){
        return categoryServices.saveMaterialByProduct(wrapperMaterial.getMaterial(),wrapperMaterial.getProductId());
    }

    @RequestMapping(value="/findMaterialByProductId",method = RequestMethod.GET)
    public List<Material> getMaterialsByProductId(@RequestParam("ProductId") String productId){
        return categoryServices.getMaterialByProductId(productId);
    }

    @RequestMapping(value="/findProductByCategoryId",method = RequestMethod.GET)
    public  List<ProductMini> getProductsByCategoryId(@RequestParam("CategoryId") String cid){
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

    @RequestMapping(value="/findAllMaterials",method = RequestMethod.GET)
    public List<Material> getAllMaterials(){
        return categoryServices.getAllMaterials();
    }

    @RequestMapping(value="/findMaterialById",method=RequestMethod.GET)
    public Optional<Material> getMaterialById(@RequestParam("materialId") String materialId){
        return categoryServices.getMaterialById(materialId);
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
    @RequestMapping(value="/findProductsByCombinedCondition",method = RequestMethod.GET)
    public List<Product> getProductsByThree(@RequestParam("condition") String condition){
        return categoryServices.getProductsByThree(condition);
    }

    @RequestMapping(value="/findCompanyById",method = RequestMethod.GET)
    public Company getCompanyById(@RequestParam("CompanyId") String companyId){
        return categoryServices.getCompanyById(companyId).get();
    }

    @RequestMapping(value="/findCategoryById",method = RequestMethod.GET)
    public Category getCategoryById(@RequestParam("CategoryId") String categoryId){
        return categoryServices.getCategoryById(categoryId).get();
    }

    @RequestMapping(value="/updateMaterialDescription",method = RequestMethod.GET)
    public Material updateMaterialDescription(@RequestParam("materialId") String materialId,
                                              @RequestParam("description") String description){
        return categoryServices.updateMaterialDescrption(materialId,description);
    }

    @RequestMapping(value="/findMaterialById",method = RequestMethod.GET)
    public Material findMaterialById(@RequestParam("materialId") String materialId){
       return categoryServices.getMaterialById(materialId).get();
    }

}
