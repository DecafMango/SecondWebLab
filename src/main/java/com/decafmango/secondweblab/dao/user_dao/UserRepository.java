package com.decafmango.secondweblab.dao.user_dao;

import com.decafmango.secondweblab.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User saveUser(User user);
    Optional<User> getUserByLogin(String login);
}
