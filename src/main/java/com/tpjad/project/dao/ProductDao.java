package com.tpjad.project.dao;

import com.tpjad.project.entity.Product;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public interface ProductDao {
    Collection<Product> getAll();
    Collection<Product> getByCategory(int categoryId);
    Product getById(int id);
    Product getByName(String name);
    void add(Product product);
    void update(Product product);
    void delete(Product product);
    boolean buyProduct(Product product);
}
