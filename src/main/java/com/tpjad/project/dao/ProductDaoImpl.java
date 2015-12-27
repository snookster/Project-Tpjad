package com.tpjad.project.dao;

import com.tpjad.project.entity.Product;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public class ProductDaoImpl extends AbstractDao implements ProductDao {
    public Collection<Product> getAll() {
        return entityManager.createQuery("SELECT p FROM products p", Product.class).getResultList();
    }

    public Collection<Product> getByCategory(int categoryId) {
        return entityManager.createQuery("SELECT p FROM products p WHERE p.category.id = ?1", Product.class)
                .setParameter(1, categoryId)
                .getResultList();
    }

    public Product getById(int id) {
        return entityManager.find(Product.class, id);
    }

    public void add(Product product) {
        entityManager.persist(product);
    }

    public void update(Product product) {
        entityManager.merge(product);
    }

    public void delete(Product product) {
        entityManager.remove(product);
    }
}
