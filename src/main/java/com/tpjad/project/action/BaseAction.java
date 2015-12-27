package com.tpjad.project.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public class BaseAction extends ActionSupport implements ServletRequestAware {

    protected HttpServletRequest request;
    protected Object model;

    public void setServletRequest(javax.servlet.http.HttpServletRequest httpServletRequest) {
        request = httpServletRequest;
    }

    public void setModel(Object model) {
        this.model = model;
    }
}
