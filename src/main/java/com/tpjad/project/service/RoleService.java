package com.tpjad.project.service;

import com.tpjad.project.model.RoleModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/26/2015.
 */
public interface RoleService {
    Collection<RoleModel> getAll();
    RoleModel getById(int id);
}
