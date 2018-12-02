// package se.agilecourse.model;

// import org.springframework.data.mongodb.core.mapping.DBRef;
// import org.springframework.data.mongodb.core.mapping.Document;
// import se.agilecourse.annotation.CascadeSave;

// import java.util.List;

// @Document(collection="brands")
// public class Brand{

//     String id;
//     String name;
//     String description;

//     @DBRef
//     @CascadeSave
//     private List<Product> products;

//     public String getId() {
//         return id;
//     }

//     public void setId(String id) {
//         id = id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public List<Product> getProducts() {
//         return products;
//     }

//     public void setProducts(List<Product> products) {
//         this.products = products;
//     }
// }
