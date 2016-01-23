package com.tpjad.project.action;

import com.opensymphony.xwork2.ModelDriven;
import com.tpjad.project.model.ProductModel;
import com.tpjad.project.model.RequestIdentifierModel;
import com.tpjad.project.service.ProductService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * Created by Vlad Trenea on 12/17/2015.
 */
@ParentPackage("default")
@InterceptorRef("authenticationStack")
public class ProductAction extends BaseAction implements ModelDriven<Object> {

    private ProductService productService;

    @Action("/product/all")
    public String all() {
        try {
            setModel(productService.getAll());
        } catch (Exception e) {
            return getExceptionError(e);
        }

        return SUCCESS;
    }

    @Action("/product/byCategory")
    public String byCategory() {
        try {
            setModel(productService.getByCategory(Integer.parseInt(request.getParameter("id"))));
        } catch (Exception e) {
            return getExceptionError(e);
        }

        return SUCCESS;
    }

    @Action("/product/byId")
    public String byId() {
        try {
            setModel(productService.getProductById(Integer.parseInt(request.getParameter("id"))));
        } catch (Exception e) {
            return getExceptionError(e);
        }

        return SUCCESS;
    }

    @Action("/product/add")
    public String add() {
        try {
            ProductModel productModel = getModelFromRequestBody(ProductModel.class);
            productService.add(productModel);
        } catch (Exception e) {
            return getExceptionError(e);
        }

        return SUCCESS;
    }

    @Action("/product/update")
    public String update() {
        try {
            ProductModel productModel = getModelFromRequestBody(ProductModel.class);
            productService.update(productModel);
        } catch (Exception e) {
            return getExceptionError(e);
        }

        return SUCCESS;
    }

    @Action("/product/delete")
    public String delete() {
        try {
            productService.delete(getModelFromRequestBody(RequestIdentifierModel.class).getId());
        } catch (Exception e) {
            return getExceptionError(e);
        }

        return SUCCESS;
    }


    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Object getModel() {
        return model;
    }
}
