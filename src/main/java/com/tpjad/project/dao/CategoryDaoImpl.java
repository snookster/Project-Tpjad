package com.tpjad.project.dao;

import com.tpjad.project.entity.Category;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public class CategoryDaoImpl extends AbstractDao implements CategoryDao {
    public Collection<Category> getAll() {
        return entityManager.createQuery("SELECT c FROM categories c", Category.class).getResultList();
    }

    public Category getById(int id) {
        return entityManager.find(Category.class, id);
    }
}
