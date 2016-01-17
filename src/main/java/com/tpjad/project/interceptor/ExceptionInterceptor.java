package com.tpjad.project.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.tpjad.project.exception.AuthenticationException;
import com.tpjad.project.exception.InvalidRequestException;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vlad Trenea on 1/17/2016.
 */
public class ExceptionInterceptor implements Interceptor {

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        String result;
        try {
            result = invocation.invoke();
        } catch (Exception ex) {
            result = "errorResult";
            attachErrorResponse(invocation, ex);
        }

        return result;
    }

    private void attachErrorResponse(ActionInvocation invocation, Exception ex) {
        int statusCode;
        String errorMessage = ex.getMessage();

        if (ex instanceof AuthenticationException) {
            statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        } else if (ex instanceof InvalidRequestException) {
            statusCode = HttpServletResponse.SC_BAD_REQUEST;
        } else {
            statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            errorMessage = "Technical Error";
        }

        StrutsRequestWrapper requestWrapper = (StrutsRequestWrapper) invocation.getInvocationContext().getContextMap().get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
        requestWrapper.setAttribute("errorMsg", errorMessage);
        requestWrapper.setAttribute("errorCode", statusCode);
    }
}
