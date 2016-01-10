package com.tpjad.project.service;

import com.tpjad.project.dao.RoleDao;
import com.tpjad.project.dao.UserDao;
import com.tpjad.project.entity.User;
import com.tpjad.project.mapper.UserMapper;
import com.tpjad.project.model.UserModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/23/2015.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    public Collection<UserModel> getAll() {
        Collection<User> users = userDao.getAll();

        return UserMapper.toUserModels(users);
    }

    public UserModel getById(int id) {
        User user = userDao.getById(id);

        return UserMapper.toUserModel(user);
    }

    public UserModel getByUsername(String username) {
        User user = userDao.getByUsername(username);

        return UserMapper.toUserModel(user);
    }

    public void addUser(UserModel userModel) {
        //TODO: validation
        User user = UserMapper.toUser(userModel);
        user.setRole(roleDao.getById(userModel.getRole().getId()));

        userDao.add(user);
    }

    public void updateUser(UserModel userModel) {
        //TODO: validation
        User user = userDao.getById(userModel.getId());
        UserMapper.refreshUser(user, userModel);
        user.setRole(roleDao.getById(userModel.getRole().getId()));
    }

    public void deleteUser(int id) {
        User user = userDao.getById(id);
        user.setDeleted(true);

        userDao.update(user);
    }

    public boolean exists(String username, String password) {
        User entity = userDao.getByUsername(username);
        return entity.getPassword().equals(password);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}
