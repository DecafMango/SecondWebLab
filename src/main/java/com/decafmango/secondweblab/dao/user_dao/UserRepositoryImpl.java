package com.decafmango.secondweblab.dao.user_dao;

import com.decafmango.secondweblab.model.User;
import jakarta.ejb.Singleton;

import java.util.*;

@Singleton
public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public User saveUser(User user) {
        users.put(user.getLogin(), user);
        return user;
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return Optional.ofNullable(users.get(login));
    }
}
