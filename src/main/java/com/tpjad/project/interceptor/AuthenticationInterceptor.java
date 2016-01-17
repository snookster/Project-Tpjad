package com.tpjad.project.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.tpjad.project.exception.AuthenticationException;
import com.tpjad.project.service.AccountService;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;

/**
 * Created by Vlad Trenea on 1/11/2016.
 */
public class AuthenticationInterceptor implements Interceptor {

    private static final String AUTH_TOKEN_HEADER = "authToken";

    private AccountService accountService;

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        StrutsRequestWrapper requestWrapper = (StrutsRequestWrapper) actionInvocation.getInvocationContext().getContextMap().get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
        String token = requestWrapper.getHeader(AUTH_TOKEN_HEADER);

        if (token == null || token.isEmpty() || !accountService.isTokenValid(token)) {
            throw new AuthenticationException("Accesing this resource requires authentication");
        }

        return actionInvocation.invoke();
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
