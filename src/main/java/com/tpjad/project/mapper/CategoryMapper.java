package com.tpjad.project.mapper;

import com.tpjad.project.entity.Category;
import com.tpjad.project.model.CategoryModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public class CategoryMapper {
    public static CategoryModel toCategoryModel(Category category) {
        if (category == null) {
            return null;
        }

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(category.getId());
        categoryModel.setName(category.getName());

        return categoryModel;
    }

    public static Collection<CategoryModel> toCategoryModels(Collection<Category> categories) {
        List<CategoryModel> categoryModels = new ArrayList<CategoryModel>();

        for (Category category : categories) {
            categoryModels.add(toCategoryModel(category));
        }

        return categoryModels;
    }

    public static Category toCategory(CategoryModel categoryModel) {
        if (categoryModel == null) {
            return null;
        }

        Category category = new Category();
        category.setName(categoryModel.getName());

        return category;
    }
}
