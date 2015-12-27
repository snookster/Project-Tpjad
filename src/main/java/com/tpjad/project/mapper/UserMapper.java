package com.tpjad.project.mapper;

import com.tpjad.project.entity.User;
import com.tpjad.project.model.UserModel;
import com.tpjad.project.utils.HashUtils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Vlad Trenea on 12/23/2015.
 */
public class UserMapper {

    public static UserModel toUserModel(User user) {
        if (user == null) {
            return null;
        }

        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setUsername(user.getUsername());
        userModel.setPassword(user.getPassword());
        userModel.setEmail(user.getEmail());
        userModel.setRole(RoleMapper.toRoleModel(user.getRole()));

        return userModel;
    }

    public static Collection<UserModel> toUserModels(Collection<User> users) {
        List<UserModel> userModels = new ArrayList<UserModel>();

        for (User user : users) {
            userModels.add(toUserModel(user));
        }

        return userModels;
    }

    public static User toUser(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setUsername(userModel.getUsername());
        user.setPassword(HashUtils.hashMd5(userModel.getPassword()));
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setDeleted(false);

        return user;
    }

    public static void refreshUser(User user, UserModel userModel) {
        user.setUsername(userModel.getUsername());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
    }
}
