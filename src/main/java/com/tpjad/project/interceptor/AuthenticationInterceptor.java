package com.tpjad.project.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.tpjad.project.exception.AuthenticationException;
import com.tpjad.project.service.AccountService;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Vlad Trenea on 1/11/2016.
 */
public class AuthenticationInterceptor extends AbstractInterceptor {

    private static final String AUTH_TOKEN_HEADER = "authToken";
    private static final String ERROR = "errorResult";

    private AccountService accountService;

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        StrutsRequestWrapper requestWrapper = (StrutsRequestWrapper) actionInvocation.getInvocationContext().getContextMap().get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
        String token = requestWrapper.getHeader(AUTH_TOKEN_HEADER);

        if (token == null || token.isEmpty() || !accountService.isTokenValid(token)) {
            attachErrorResponse(actionInvocation);

            return ERROR;
        }

        return actionInvocation.invoke();
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private void attachErrorResponse(ActionInvocation invocation) {
        StrutsRequestWrapper requestWrapper = (StrutsRequestWrapper)
                invocation.getInvocationContext().getContextMap().get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");

        requestWrapper.setAttribute("errorMsg", "Accessing this resource requires authorization");
        requestWrapper.setAttribute("errorCode", HttpServletResponse.SC_UNAUTHORIZED);
    }
}
