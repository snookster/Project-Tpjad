package com.tpjad.project.mapper;

import com.tpjad.project.entity.Role;
import com.tpjad.project.model.RoleModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Vlad Trenea on 12/26/2015.
 */
public class RoleMapper {

    public static RoleModel toRoleModel(Role role) {
        if (role == null) {
            return null;
        }

        RoleModel roleModel = new RoleModel();
        roleModel.setId(role.getId());
        roleModel.setName(role.getName());

        return roleModel;
    }

    public static Collection<RoleModel> toRoleModels(Collection<Role> roles) {
        List<RoleModel> roleModels = new ArrayList<RoleModel>();

        for (Role role : roles) {
            roleModels.add(toRoleModel(role));
        }

        return roleModels;
    }
}
