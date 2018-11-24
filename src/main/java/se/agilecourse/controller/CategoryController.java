package se.agilecourse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import se.agilecourse.model.Category;
import se.agilecourse.model.Product;
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

    @RequestMapping(value="/findProductByCid",method = RequestMethod.GET)
    public  List<Product> getProductsById(@RequestParam("id") String cid){return categoryServices.getProductsByCid(cid);}



}
