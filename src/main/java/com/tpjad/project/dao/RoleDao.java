package com.tpjad.project.dao;

import com.tpjad.project.entity.Role;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/26/2015.
 */
public interface RoleDao {
    Collection<Role> getAll();
    Role getById(int id);
}
