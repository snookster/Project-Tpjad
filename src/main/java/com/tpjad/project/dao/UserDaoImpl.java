package com.tpjad.project.dao;

import com.tpjad.project.entity.User;

import java.util.Collection;

/**
 * Created by Vlad Trenea on 12/23/2015.
 */
public class UserDaoImpl extends AbstractDao implements UserDao {

    public Collection<User> getAll() {
        return entityManager.createQuery("SELECT u FROM users u WHERE u.isDeleted = ?1", User.class)
                .setParameter(1, false)
                .getResultList();
    }

    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    public User getByUsername(String username) {
        Collection<User> users = entityManager.createQuery("SELECT u FROM users u WHERE u.username = ?1 AND u.isDeleted = ?2", User.class)
                .setParameter(1, username)
                .setParameter(2, false)
                .getResultList();

        return getUserFromResult(users);
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        Collection<User> users = entityManager.createQuery("SELECT u FROM users u WHERE u.username = ?1 AND u.password = ?2 " +
                "AND u.isDeleted = ?3", User.class)
                .setParameter(1, username)
                .setParameter(2, password)
                .setParameter(3, false)
                .getResultList();

        return getUserFromResult(users);
    }

    public void add(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    private User getUserFromResult(Collection<User> resultList) {
        User userToBeReturned = null;
        if (resultList.size() > 0) {
            userToBeReturned = resultList.iterator().next();
        }

        return userToBeReturned;
    }
}
