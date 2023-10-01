package com.decafmango.secondweblab.dao.attempt_dao;

import com.decafmango.secondweblab.model.attempt.Attempt;
import com.decafmango.secondweblab.model.User;
import jakarta.ejb.Singleton;

import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class AttemptRepositoryImpl implements AttemptRepository {

    private List<Attempt> attempts = new ArrayList<>();

    @Override
    public List<Attempt> getUserAttempts(User user) {
        return attempts.stream()
                .filter(attempt -> attempt.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public Attempt saveAttempt(Attempt attempt) {
        attempts.add(attempt);
        return attempt;
    }

    @Override
    public void deleteUserAttempts(User user) {
        attempts = attempts.stream()
                .filter(attempt -> !attempt.getUser().equals(user))
                .collect(Collectors.toList());
    }
}
