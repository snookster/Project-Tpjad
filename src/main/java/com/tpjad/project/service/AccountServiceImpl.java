package com.tpjad.project.service;

import com.tpjad.project.dao.UserDao;
import com.tpjad.project.entity.User;
import com.tpjad.project.exception.InvalidRequestException;
import com.tpjad.project.model.AuthenticationModel;
import com.tpjad.project.model.LoginModel;
import com.tpjad.project.utils.AuthenticationCacheImpl;
import com.tpjad.project.utils.HashUtils;

import java.util.UUID;

/**
 * Created by Vlad Trenea on 1/17/2016.
 */
public class AccountServiceImpl implements AccountService {

    private UserDao userDao;
    private AuthenticationCacheImpl authenticationCache;

    @Override
    public AuthenticationModel login(LoginModel loginModel) throws InvalidRequestException {
        validateLogin(loginModel);

        User user = userDao.getByUsernameAndPassword(loginModel.getUsername(), HashUtils.hashMd5(loginModel.getPassword()));
        if (user == null) {
            throw new InvalidRequestException("Invalid username or password");
        }

        String token = UUID.randomUUID().toString();
        authenticationCache.put(token, user.getId());

        AuthenticationModel authenticationModel = new AuthenticationModel();
        authenticationModel.setId(user.getId());
        authenticationModel.setToken(token);

        return authenticationModel;
    }

    public boolean isTokenValid(String token) {
        return authenticationCache.get(token) != null;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public void setAuthenticationCache(AuthenticationCacheImpl authenticationCache) {
        this.authenticationCache = authenticationCache;
    }

    private void validateLogin(LoginModel loginModel) throws InvalidRequestException {
        if (loginModel.getUsername() == null || loginModel.getUsername().isEmpty()) {
            throw new InvalidRequestException("Username is required");
        }

        if (loginModel.getPassword() == null || loginModel.getPassword().isEmpty()) {
            throw new InvalidRequestException("Password is required");
        }
    }
}
