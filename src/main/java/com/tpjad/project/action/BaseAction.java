package com.tpjad.project.action;

import com.google.gson.Gson;
import com.tpjad.project.exception.InvalidRequestException;
import com.tpjad.project.exception.ResourceNotFoundException;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

/**
 * Created by Vlad Trenea on 12/27/2015.
 */
public class BaseAction implements ServletRequestAware {

    protected final String SUCCESS = "success";
    protected final String ERROR = "errorResult";

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

    protected String  getExceptionError(Exception ex) {
        int statusCode;
        String errorMessage = ex.getMessage();

        if (ex instanceof InvalidRequestException) {
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        } else if (ex instanceof ResourceNotFoundException) {
            statusCode = HttpServletResponse.SC_NOT_FOUND;
        } else {
            statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            errorMessage = "Technical Error";
        }

        request.setAttribute("errorMsg", errorMessage);
        request.setAttribute("errorCode", statusCode);

        return ERROR;
    }

    public void setServletRequest(javax.servlet.http.HttpServletRequest httpServletRequest) {
        request = httpServletRequest;
    }

    public void setModel(Object model) {
        this.model = model;
    }
}
