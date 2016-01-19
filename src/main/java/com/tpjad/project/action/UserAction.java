package com.tpjad.project.action;

import com.opensymphony.xwork2.ModelDriven;
import com.tpjad.project.service.UserService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * Created by Vlad Trenea on 12/23/2015.
 */
@ParentPackage("default")
@InterceptorRef("authenticationStack")
public class UserAction extends BaseAction implements ModelDriven<Object> {

    private UserService userService;

    @Action("/user/all")
    public String all() {
        try {
            setModel(userService.getAll());
        } catch (Exception e) {
            return getExceptionError(e);
        }

        return SUCCESS;
    }

    @Action("/user/byId")
    public String byId() {
        try {
            setModel(userService.getById(Integer.parseInt(request.getParameter("id"))));
        } catch (Exception e) {
            return getExceptionError(e);
        }

        return SUCCESS;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Object getModel() {
        return model;
    }
}
