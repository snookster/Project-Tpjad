package com.tpjad.project.action;

import com.opensymphony.xwork2.ModelDriven;
import com.tpjad.project.service.CategoryService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
@ParentPackage("default")
@InterceptorRef("authenticationStack")
public class CategoryAction extends BaseAction implements ModelDriven<Object> {

    private CategoryService categoryService;

    @Action("/category/all")
    public String all() {
        try {
            setModel(categoryService.getAll());
        } catch (Exception e) {
            return getExceptionError(e);
        }

        return SUCCESS;
    }

    @Action("/category/byId")
    public String byId() {
        try {
            setModel(categoryService.getById(Integer.parseInt(request.getParameter("id"))));
        } catch (Exception e) {
            return getExceptionError(e);
        }

        return SUCCESS;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Object getModel() {
        return model;
    }
}
