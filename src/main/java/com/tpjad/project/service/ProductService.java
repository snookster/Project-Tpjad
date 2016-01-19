package com.tpjad.project.service;

import com.tpjad.project.exception.ConflictException;
import com.tpjad.project.exception.InvalidRequestException;
import com.tpjad.project.exception.ResourceNotFoundException;
import com.tpjad.project.model.ProductModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/26/2015.
 */
public interface ProductService {
    Collection<ProductModel> getAll();
    Collection<ProductModel> getByCategory(int categoryId);
    ProductModel getProductById(int id) throws ResourceNotFoundException;
    void add(ProductModel productModel) throws InvalidRequestException, ResourceNotFoundException, ConflictException;
    void update(ProductModel productModel) throws InvalidRequestException, ResourceNotFoundException, ConflictException;
    void delete(int id) throws ResourceNotFoundException;
}
