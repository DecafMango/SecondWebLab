package com.decafmango.secondweblab.model.attempt;

public class AttemptMapper {

    public static AttemptDto toAttemptDto(Attempt attempt) {
        return new AttemptDto(attempt.getX(), attempt.getY(), attempt.getR(), attempt.isHit(), attempt.getAttemptTime(), attempt.getScriptDuration());
    }
}
