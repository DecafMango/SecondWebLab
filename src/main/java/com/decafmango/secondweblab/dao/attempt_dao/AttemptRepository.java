package com.decafmango.secondweblab.dao.attempt_dao;

import com.decafmango.secondweblab.model.attempt.Attempt;
import com.decafmango.secondweblab.model.User;

import java.util.List;

public interface AttemptRepository {
    List<Attempt> getUserAttempts(User user);
    Attempt saveAttempt(Attempt attempt);
    void deleteUserAttempts(User user);
}
