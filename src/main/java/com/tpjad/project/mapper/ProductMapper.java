package com.tpjad.project.mapper;

import com.tpjad.project.entity.Product;
import com.tpjad.project.model.ProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public class ProductMapper {
    public static ProductModel toProductModel(Product product) {
        if (product == null) {
            return null;
        }

        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setName(product.getName());
        productModel.setDescription(product.getDescription());
        productModel.setStock(product.getStock());
        productModel.setCategory(CategoryMapper.toCategoryModel(product.getCategory()));

        return productModel;
    }

    public static Collection<ProductModel> toProductModels(Collection<Product> products) {
        List<ProductModel> productModels = new ArrayList<ProductModel>();

        for (Product product : products) {
            productModels.add(toProductModel(product));
        }

        return productModels;
    }

    public static Product toProduct(ProductModel productModel) {
        if (productModel == null) {
            return null;
        }

        Product product = new Product();
        product.setName(productModel.getName());
        product.setDescription(productModel.getDescription());
        product.setStock(productModel.getStock());

        return product;
    }

    public static void refreshProduct(Product product, ProductModel productModel) {
        product.setName(productModel.getName());
        product.setDescription(productModel.getDescription());
        product.setStock(productModel.getStock());
    }

}
