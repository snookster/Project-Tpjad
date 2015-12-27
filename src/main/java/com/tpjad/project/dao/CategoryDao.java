package com.tpjad.project.dao;

import com.tpjad.project.entity.Category;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public interface CategoryDao {
    Collection<Category> getAll();
    Category getById(int id);
}
