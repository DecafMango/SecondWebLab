package com.decafmango.secondweblab.dao.attempt_dao;

import com.decafmango.secondweblab.model.Attempt;
import com.decafmango.secondweblab.model.User;

public interface AttemptRepository {
    Attempt getUserAttempts(User user);
    Attempt saveAttempt(Attempt attempt);
    Attempt deleteUserAttempts(User user);
}
