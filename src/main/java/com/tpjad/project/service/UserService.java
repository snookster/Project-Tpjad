package com.tpjad.project.service;

import com.tpjad.project.exception.ResourceNotFoundException;
import com.tpjad.project.model.UserModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/23/2015.
 */
public interface UserService {
    Collection<UserModel> getAll();
    UserModel getById(int id) throws ResourceNotFoundException;
}
