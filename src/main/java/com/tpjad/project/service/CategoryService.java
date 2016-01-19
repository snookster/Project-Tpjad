package com.tpjad.project.service;

import com.tpjad.project.exception.ConflictException;
import com.tpjad.project.exception.InvalidRequestException;
import com.tpjad.project.exception.ResourceNotFoundException;
import com.tpjad.project.model.CategoryModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public interface CategoryService {
    Collection<CategoryModel> getAll();

    CategoryModel getCategoryById(int id) throws ResourceNotFoundException;

    void add(CategoryModel categoryModel) throws InvalidRequestException, ConflictException;

    void update(CategoryModel categoryModel) throws InvalidRequestException, ResourceNotFoundException, ConflictException;

    void delete(int id) throws ResourceNotFoundException;
}
