package com.tpjad.project.service;

import com.tpjad.project.dao.CategoryDao;
import com.tpjad.project.entity.Category;
import com.tpjad.project.exception.ConflictException;
import com.tpjad.project.exception.InvalidRequestException;
import com.tpjad.project.exception.ResourceNotFoundException;
import com.tpjad.project.mapper.CategoryMapper;
import com.tpjad.project.model.CategoryModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    @Override
    public Collection<CategoryModel> getAll() {
        Collection<Category> categories = categoryDao.getAll();

        return CategoryMapper.toCategoryModels(categories);
    }

    @Override
    public CategoryModel getCategoryById(int id) throws ResourceNotFoundException {
        Category category = getById(id);

        return CategoryMapper.toCategoryModel(category);
    }

    @Override
    public void add(CategoryModel categoryModel) throws InvalidRequestException, ConflictException {
        validateCategory(categoryModel);

        if (categoryDao.getByName(categoryModel.getName()) != null) {
            throw new ConflictException("Category name already exists");
        }

        Category category = CategoryMapper.toCategory(categoryModel);

        categoryDao.add(category);
    }

    @Override
    public void update(CategoryModel categoryModel) throws InvalidRequestException, ResourceNotFoundException, ConflictException {
        validateCategory(categoryModel);

        Category category = categoryDao.getByName(categoryModel.getName());
        if (category != null && category.getId() != categoryModel.getId()) {
            throw new ConflictException("Category name already exists");
        }

        Category currentCategory = getById(categoryModel.getId());
        currentCategory.setName(categoryModel.getName());

        categoryDao.update(currentCategory);
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        Category category = getById(id);

        categoryDao.delete(category);
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    private Category getById(int id) throws ResourceNotFoundException {
        Category category = categoryDao.getById(id);

        if (category == null) {
            throw new ResourceNotFoundException("Invalid category identifier");
        }

        return category;
    }

    private void validateCategory(CategoryModel categoryModel) throws InvalidRequestException {
        if (categoryModel == null) {
            throw new InvalidRequestException("Category must not be null");
        }

        if (categoryModel.getName() == null || categoryModel.getName().isEmpty()) {
            throw new InvalidRequestException("Category name is required");
        }
    }
}
