package com.tpjad.project.action;

import com.opensymphony.xwork2.ModelDriven;
import com.tpjad.project.model.LoginModel;
import com.tpjad.project.service.UserService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/**
 * Created by Vlad Trenea on 12/23/2015.
 */
@Result(name="success",type="json")
@ParentPackage("json-default")
@Namespace("packUsers")
public class UserAction extends BaseAction implements ModelDriven<Object> {

    private UserService userService;

    @Action("/user/all")
    public String all() {
        setModel(userService.getAll());

        return SUCCESS;
    }

    @Action("/user/byId")
    public String byId() {
        setModel(userService.getById(Integer.parseInt(request.getParameter("id"))));

        return SUCCESS;
    }

    @Action("/user/login")
    public String login(){

        if(request.getContentLength() > 0) {

            LoginModel model = super.getModel(LoginModel.class);
            return SUCCESS;
        }
        return ERROR;
    }


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Object getModel() {
        return model;
    }
}
