package com.tpjad.project.dao;

import com.tpjad.project.entity.User;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/23/2015.
 */
public interface UserDao {
    Collection<User> getAll();
    User getById(int id);
    User getByUsername(String username);
    void add(User user);
    void update(User user);
}
