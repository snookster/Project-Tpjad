package com.tpjad.project.action;

import com.opensymphony.xwork2.ModelDriven;
import com.tpjad.project.exception.InvalidRequestException;
import com.tpjad.project.model.LoginModel;
import com.tpjad.project.service.AccountService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * Created by Vlad Trenea on 1/17/2016.
 */
@ParentPackage("default")
@InterceptorRef("basicStack")
public class AccountAction extends BaseAction implements ModelDriven<Object> {

    private AccountService accountService;

    @Action("/account/login")
    public String login() throws InvalidRequestException {
        LoginModel loginModel = getModel(LoginModel.class);
        model = accountService.login(loginModel);

        return SUCCESS;
    }

    @Override
    public Object getModel() {
        return model;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
