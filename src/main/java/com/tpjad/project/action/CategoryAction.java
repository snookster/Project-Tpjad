package com.tpjad.project.action;

import com.opensymphony.xwork2.ModelDriven;
import com.tpjad.project.service.CategoryService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
@Result(name="success",type="json")
@ParentPackage("json-default")
@Namespace("packCategories")
public class CategoryAction extends BaseAction implements ModelDriven<Object> {

    private CategoryService categoryService;

    @Action("/category/all")
    public String all() {
        setModel(categoryService.getAll());

        return SUCCESS;
    }

    @Action("/category/byId")
    public String byId() {
        setModel(categoryService.getById(Integer.parseInt(request.getParameter("id"))));

        return SUCCESS;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Object getModel() {
        return model;
    }
}
