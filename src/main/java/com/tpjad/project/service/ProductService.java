package com.tpjad.project.service;

import com.tpjad.project.exception.InvalidRequestException;
import com.tpjad.project.model.ProductModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/26/2015.
 */
public interface ProductService {
    Collection<ProductModel> getAll();
    Collection<ProductModel> getByCategory(int categoryId);
    ProductModel getProductById(int id);
    void add(ProductModel productModel) throws InvalidRequestException;
    void update(ProductModel productModel) throws InvalidRequestException;
    void delete(int id) throws InvalidRequestException;
}
