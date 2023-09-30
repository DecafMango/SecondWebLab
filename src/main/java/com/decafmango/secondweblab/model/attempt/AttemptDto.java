package com.decafmango.secondweblab.model.attempt;

import com.decafmango.secondweblab.model.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AttemptDto {
    private final float x;
    private final float y;
    private final int r;
    private final boolean isHit;
    private final String attemptTime;
    private final long scriptDuration;

    public boolean isHit() {
        return isHit;
    }

    public AttemptDto(float x, float y, int r, boolean isHit, String attemptTime, long scriptDuration) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.attemptTime = attemptTime;
        this.scriptDuration = scriptDuration;
    }

    @Override
    public String toString() {
        return "AttemptDto{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", isHit=" + isHit +
                ", attemptTime='" + attemptTime + '\'' +
                ", scriptDuration=" + scriptDuration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttemptDto that = (AttemptDto) o;
        return Float.compare(x, that.x) == 0 && Float.compare(y, that.y) == 0 && r == that.r && isHit == that.isHit && scriptDuration == that.scriptDuration && Objects.equals(attemptTime, that.attemptTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, isHit, attemptTime, scriptDuration);
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
}