package com.tpjad.project.service;

import com.tpjad.project.model.UserModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/23/2015.
 */
public interface UserService {
    Collection<UserModel> getAll();
    UserModel getById(int id);
    UserModel getByUsername(String username);
    void addUser(UserModel userModel);
    void updateUser(UserModel userModel);
    void deleteUser(int id);
    boolean exists(String username, String password);
}
