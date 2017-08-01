package com.teamtreehouse.pomodoro.model;

public enum AttemptKind { // enum for attempt
    FOCUS(25*60, "Focus Time"),
    BREAK(5*60, "Break Time");

    private String mDisplayName;
    private int mTotalSeconds;

    AttemptKind(int totalSeconds, String displayName) {
        mTotalSeconds = totalSeconds;
        mDisplayName = displayName;
    }

    public int getmTotalSeconds() {
        return mTotalSeconds;
    }

    public String getDisplayName() {
        return mDisplayName;
    }
}
