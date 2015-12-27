package com.tpjad.project.dao;

import com.tpjad.project.entity.Role;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/26/2015.
 */
public class RoleDaoImpl extends AbstractDao implements RoleDao {
    public Collection<Role> getAll() {
        return entityManager.createQuery("SELECT r FROM roles r", Role.class).getResultList();
    }

    public Role getById(int id) {
        return entityManager.find(Role.class, id);
    }
}
