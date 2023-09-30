package com.decafmango.secondweblab.model.attempt;

import com.decafmango.secondweblab.model.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Attempt {
    private final float x;
    private final float y;
    private final int r;
    private final boolean isHit;
    private final String attemptTime;
    private final long scriptDuration;
    private final User user;

    public boolean isHit() {
        return isHit;
    }

    public Attempt(float x, float y, int r, boolean isHit, LocalDateTime attemptTime, Duration scriptDuration, User user) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.attemptTime = attemptTime.format(DateTimeFormatter.ofPattern("u-M-d k-m-s"));
        this.scriptDuration = scriptDuration.toNanos();
        this.user = user;
    }

    @Override
    public String toString() {
        return "Attempt{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", isHit=" + isHit +
                ", attemptTime=" + attemptTime +
                ", scriptDuration=" + scriptDuration +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attempt attempt = (Attempt) o;
        return Float.compare(x, attempt.x) == 0 && Float.compare(y, attempt.y) == 0 && r == attempt.r && isHit == attempt.isHit && Objects.equals(attemptTime, attempt.attemptTime) && Objects.equals(scriptDuration, attempt.scriptDuration) && Objects.equals(user, attempt.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, isHit, attemptTime, scriptDuration, user);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public String getAttemptTime() {
        return attemptTime;
    }

    public long getScriptDuration() {
        return scriptDuration;
    }

    public User getUser() {
        return user;
    }
}
