package com.tpjad.project.service;

import com.tpjad.project.dao.CategoryDao;
import com.tpjad.project.entity.Category;
import com.tpjad.project.mapper.CategoryMapper;
import com.tpjad.project.model.CategoryModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    public Collection<CategoryModel> getAll() {
        Collection<Category> categories = categoryDao.getAll();

        return CategoryMapper.toCategoryModels(categories);
    }

    public CategoryModel getById(int id) {
        Category category = categoryDao.getById(id);

        return CategoryMapper.toCategoryModel(category);
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
}
