package com.tpjad.project.action;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.tpjad.project.exception.InvalidRequestException;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public class BaseAction extends ActionSupport implements ServletRequestAware {

    protected HttpServletRequest request;
    protected Object model;

    protected <T> T getModel(Class<T> type) throws InvalidRequestException {
        StringBuilder buffer = new StringBuilder();
        String line;
        T result;

        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null)
            {
                buffer.append(line);
            }

            result = new Gson().fromJson(buffer.toString(),type);
        } catch (Exception e) {
            throw new InvalidRequestException("Invalid request format");
        }

        return result;
    }

    public void setServletRequest(javax.servlet.http.HttpServletRequest httpServletRequest) {
        request = httpServletRequest;
    }

    public void setModel(Object model) {
        this.model = model;
    }
}
