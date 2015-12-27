package com.tpjad.project.service;

import com.tpjad.project.dao.RoleDao;
import com.tpjad.project.entity.Role;
import com.tpjad.project.mapper.RoleMapper;
import com.tpjad.project.model.RoleModel;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/26/2015.
 */
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public Collection<RoleModel> getAll() {
        Collection<Role> roles = roleDao.getAll();

        return RoleMapper.toRoleModels(roles);
    }

    public RoleModel getById(int id) {
        Role role = roleDao.getById(id);

        return RoleMapper.toRoleModel(role);
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

}
