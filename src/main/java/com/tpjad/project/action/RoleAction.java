package com.tpjad.project.action;

import com.opensymphony.xwork2.ModelDriven;
import com.tpjad.project.service.RoleService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * Created by Vlad Trenea on 12/26/2015.
 */
@ParentPackage("default")
public class RoleAction extends BaseAction implements ModelDriven<Object> {

    private RoleService roleService;

    @Action("/role/all")
    public String all() {
        setModel(roleService.getAll());

        return SUCCESS;
    }

    @Action("/role/byId")
    public String byId() {
        setModel(roleService.getById(Integer.parseInt(request.getParameter("id"))));

        return SUCCESS;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public Object getModel() {
        return model;
    }
}
