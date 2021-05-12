package com.example.baselibrary.schedule;

public class ScheduleRunnable {
    private Runnable runnable;
    private Schedule.ThreadMode mode;

    private Schedule schedule;

    public ScheduleRunnable(Runnable runnable, Schedule.ThreadMode mode, Schedule schedule) {
        this.runnable = runnable;
        this.mode = mode;
        this.schedule = schedule;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public Schedule.ThreadMode getMode() {
        return mode;
    }

    public void setMode(Schedule.ThreadMode mode) {
        this.mode = mode;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
