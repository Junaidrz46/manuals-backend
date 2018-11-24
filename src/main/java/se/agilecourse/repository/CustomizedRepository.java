package se.agilecourse.repository;

import se.agilecourse.model.Product;

import java.util.List;

public interface CustomizedRepository {
    List<Product> findProductsByCid(String cid);
}
