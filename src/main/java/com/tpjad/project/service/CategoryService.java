package com.tpjad.project.service;

import com.tpjad.project.model.CategoryModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public interface CategoryService {
    Collection<CategoryModel> getAll();
    CategoryModel getById(int id);
}
