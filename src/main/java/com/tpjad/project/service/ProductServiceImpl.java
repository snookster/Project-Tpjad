package com.tpjad.project.service;

import com.tpjad.project.dao.CategoryDao;
import com.tpjad.project.dao.ProductDao;
import com.tpjad.project.entity.Category;
import com.tpjad.project.entity.Product;
import com.tpjad.project.exception.ConflictException;
import com.tpjad.project.exception.InvalidRequestException;
import com.tpjad.project.exception.ResourceNotFoundException;
import com.tpjad.project.mapper.ProductMapper;
import com.tpjad.project.model.ProductModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/26/2015.
 */
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private CategoryDao categoryDao;

    @Override
    public Collection<ProductModel> getAll() {
        Collection<Product> products = productDao.getAll();

        return ProductMapper.toProductModels(products);
    }

    @Override
    public Collection<ProductModel> getByCategory(int categoryId) {
        Collection<Product> products = productDao.getByCategory(categoryId);

        return ProductMapper.toProductModels(products);
    }

    @Override
    public ProductModel getProductById(int id) throws ResourceNotFoundException {
        Product product = getById(id);

        return ProductMapper.toProductModel(product);
    }

    @Override
    public void add(ProductModel productModel) throws InvalidRequestException, ResourceNotFoundException, ConflictException {
        validateProduct(productModel);

        if (productDao.getByName(productModel.getName()) != null) {
            throw new ConflictException("Product name already exists");
        }

        Product product = ProductMapper.toProduct(productModel);
        Category category = categoryDao.getById(productModel.getCategory().getId());
        if (category == null) {
            throw new ResourceNotFoundException("Invalid category identifier");
        }

        product.setCategory(category);
        category.getProducts().add(product);

        productDao.add(product);
    }

    @Override
    public void update(ProductModel productModel) throws InvalidRequestException, ResourceNotFoundException, ConflictException {
        validateProduct(productModel);

        Product product = productDao.getByName(productModel.getName());
        if (product != null && product.getId() != productModel.getId()) {
            throw new ConflictException("Product name already exists");
        }

        Product currentProduct = getById(productModel.getId());
        Category category = categoryDao.getById(productModel.getCategory().getId());
        if (category == null) {
            throw new ResourceNotFoundException("Invalid category identifier");
        }
        ProductMapper.refreshProduct(currentProduct, productModel);

        currentProduct.setCategory(category);

        productDao.update(currentProduct);
    }

    @Override
    public void delete(int id) throws ResourceNotFoundException {
        Product product = getById(id);

        productDao.delete(product);
    }

    @Override
    public boolean updateStock(int id) throws ResourceNotFoundException {
        Product product = getById(id);
        return productDao.buyProduct(product);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    private Product getById(int id) throws ResourceNotFoundException {
        Product product = productDao.getById(id);

        if (product == null) {
            throw new ResourceNotFoundException("Invalid product identifier");
        }

        return product;
    }

    private void validateProduct(ProductModel productModel) throws InvalidRequestException {
        if (productModel == null) {
            throw new InvalidRequestException("Product must not be null");
        }

        if (productModel.getName() == null || productModel.getName().isEmpty()) {
            throw new InvalidRequestException("Product name is required");
        }

        if (productModel.getDescription() == null || productModel.getDescription().isEmpty()) {
            throw new InvalidRequestException("Product description is required");
        }
    }
}
