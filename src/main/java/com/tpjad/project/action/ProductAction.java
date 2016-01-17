package com.tpjad.project.action;

import com.opensymphony.xwork2.ModelDriven;
import com.tpjad.project.exception.InvalidRequestException;
import com.tpjad.project.model.ProductModel;
import com.tpjad.project.service.ProductService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * Created by Vlad Trenea on 12/17/2015.
 */
@ParentPackage("default")
@InterceptorRef("customStack")
public class ProductAction extends BaseAction implements ModelDriven<Object> {

    private ProductService productService;

    @Action("/product/all")
    public String all() {
        setModel(productService.getAll());

        return SUCCESS;
    }

    @Action("/product/byCategory")
    public String byCategory() {
        setModel(productService.getByCategory(Integer.parseInt(request.getParameter("id"))));

        return SUCCESS;
    }

    @Action("/product/byId")
    public String byId() {
        setModel(productService.getById(Integer.parseInt(request.getParameter("id"))));

        return SUCCESS;
    }

    @Action("/product/add")
    public void add() throws InvalidRequestException {
        if(request.getContentLength() > 0) {
            ProductModel productModel = this.getModel(ProductModel.class);
        }
    }

    @Action("/product/update")
    public void update() {


    }

    @Action("/product/delete")
    public void delete() {

    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Object getModel() {
        return model;
    }
}
