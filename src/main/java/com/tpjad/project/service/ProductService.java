package com.tpjad.project.service;

import com.tpjad.project.model.ProductModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/26/2015.
 */
public interface ProductService {
    Collection<ProductModel> getAll();
    Collection<ProductModel> getByCategory(int categoryId);
    ProductModel getById(int id);
    void add(ProductModel productModel);
    void update(ProductModel productModel);
    void delete(int id);
}
