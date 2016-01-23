package com.tpjad.project.dao;

import com.tpjad.project.entity.Product;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public class ProductDaoImpl extends AbstractDao implements ProductDao {
    @Override
    public Collection<Product> getAll() {
        return entityManager.createQuery("SELECT p FROM products p", Product.class).getResultList();
    }

    @Override
    public Collection<Product> getByCategory(int categoryId) {
        return entityManager.createQuery("SELECT p FROM products p WHERE p.category.id = ?1", Product.class)
                .setParameter(1, categoryId)
                .getResultList();
    }

    @Override
    public Product getById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product getByName(String name) {
        Collection<Product> products = entityManager.createQuery("SELECT p from products p WHERE p.name = ?1", Product.class)
                .setParameter(1, name)
                .getResultList();

        return getProductFromResultList(products);
    }

    @Override
    public void add(Product product) {
        entityManager.merge(product.getCategory());
    }

    @Override
    public void update(Product product) {
        entityManager.merge(product);
    }

    @Override
    public void delete(Product product) {
        entityManager.remove(entityManager.merge(product));
    }

    @Override
    public boolean buyProduct(Product product) {
        if(product.getStock() > 0) {
            product.setStock(product.getStock() - 1);
            entityManager.merge(product);
            return true;
        }
        return false;
    }

    private Product getProductFromResultList(Collection<Product> resultList) {
        Product productToBeReturned = null;
        if (resultList.size() > 0) {
            productToBeReturned = resultList.iterator().next();
        }

        return productToBeReturned;
    }
}
