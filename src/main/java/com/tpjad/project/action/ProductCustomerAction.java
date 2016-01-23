package com.tpjad.project.action;

import com.opensymphony.xwork2.ModelDriven;
import com.tpjad.project.service.ProductService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * Created by OvyIs on 1/23/2016.
 */
@ParentPackage("default")
@InterceptorRef("noAuthenticationStack")
public class ProductCustomerAction extends BaseAction implements ModelDriven<Object> {

    private ProductService productService;

    @Action("/customer/all")
    public String all() {
        try {
            setModel(productService.getAll());
        } catch (Exception e) {
            return getExceptionError(e);
        }

        return SUCCESS;
    }

    @Action("/customer/byCategory")
    public String byCategory() {
        try {
            setModel(productService.getByCategory(Integer.parseInt(request.getParameter("id"))));
        } catch (Exception e) {
            return getExceptionError(e);
        }

        return SUCCESS;
    }

    @Action("/customer/buy")
    public String buyProduct(){
        try{
            if(productService.updateStock(Integer.parseInt(request.getParameter("id"))))
                return SUCCESS;

        }catch (Exception e){
            return getExceptionError(e);
        }

        return getExceptionError(new Exception("Out Of Stock"));
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public Object getModel() {
        return model;
    }
}
