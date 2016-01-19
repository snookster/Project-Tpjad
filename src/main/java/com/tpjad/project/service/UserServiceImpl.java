package com.tpjad.project.service;

import com.tpjad.project.dao.UserDao;
import com.tpjad.project.entity.User;
import com.tpjad.project.exception.ResourceNotFoundException;
import com.tpjad.project.mapper.UserMapper;
import com.tpjad.project.model.UserModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/23/2015.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public Collection<UserModel> getAll() {
        Collection<User> users = userDao.getAll();

        return UserMapper.toUserModels(users);
    }

    @Override
    public UserModel getById(int id) throws ResourceNotFoundException {
        User user = userDao.getById(id);

        if (user == null) {
            throw new ResourceNotFoundException("Invalid user identifier");
        }

        return UserMapper.toUserModel(user);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
