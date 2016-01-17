package com.tpjad.project.service;

import com.tpjad.project.exception.InvalidRequestException;
import com.tpjad.project.model.AuthenticationModel;
import com.tpjad.project.model.LoginModel;

/**
 * Created by Vlad Trenea on 1/17/2016.
 */
public interface AccountService {
    AuthenticationModel login(LoginModel loginModel) throws InvalidRequestException;
    boolean isTokenValid(String token);
}
