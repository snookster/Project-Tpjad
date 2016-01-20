package com.tpjad.project.dao;

import com.tpjad.project.entity.Category;
import com.tpjad.project.entity.Product;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public class CategoryDaoImpl extends AbstractDao implements CategoryDao {

    @Override
    public Collection<Category> getAll() {
        return entityManager.createQuery("SELECT c FROM categories c", Category.class).getResultList();
    }

    @Override
    public Category getById(int id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public Category getByName(String name) {
        Collection<Category> categories = entityManager.createQuery("SELECT c FROM categories c WHERE c.name = ?1", Category.class)
                .setParameter(1, name)
                .getResultList();

        return getCategoryFromResultList(categories);
    }

    @Override
    public void add(Category category) {
        entityManager.persist(category);
    }

    @Override
    public void update(Category category) {
        entityManager.merge(category);
    }

    @Override
    public void delete(Category category) {
        Category categoryToDelete = entityManager.merge(category);

        for (Product p : categoryToDelete.getProducts()) {
            entityManager.remove(p);
        }
        entityManager.remove(categoryToDelete);
    }

    private Category getCategoryFromResultList(Collection<Category> resultList) {
        Category categoryToBeReturned = null;
        if (resultList.size() > 0) {
            categoryToBeReturned = resultList.iterator().next();
        }

        return categoryToBeReturned;
    }
}
