package com.tpjad.project.service;

import com.tpjad.project.dao.CategoryDao;
import com.tpjad.project.dao.ProductDao;
import com.tpjad.project.entity.Product;
import com.tpjad.project.mapper.ProductMapper;
import com.tpjad.project.model.ProductModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/26/2015.
 */
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private CategoryDao categoryDao;

    public Collection<ProductModel> getAll() {
        Collection<Product> products = productDao.getAll();

        return ProductMapper.toProductModels(products);
    }

    public Collection<ProductModel> getByCategory(int categoryId) {
        Collection<Product> products = productDao.getByCategory(categoryId);

        return ProductMapper.toProductModels(products);
    }

    public ProductModel getById(int id) {
        Product product = productDao.getById(id);

        return ProductMapper.toProductModel(product);
    }

    public void add(ProductModel productModel) {
        //TODO: validation
        Product product = ProductMapper.toProduct(productModel);
        product.setCategory(categoryDao.getById(product.getCategory().getId()));

        productDao.add(product);
    }

    public void update(ProductModel productModel) {
        //TODO: validation
        Product product = productDao.getById(productModel.getId());
        ProductMapper.refreshProduct(product, productModel);
        product.setCategory(categoryDao.getById(productModel.getCategory().getId()));

        productDao.update(product);
    }

    public void delete(int id) {
        Product product = productDao.getById(id);
        productDao.delete(product);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
}
