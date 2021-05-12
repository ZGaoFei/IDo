package com.example.baselibrary.schedule;

import java.util.LinkedList;
import java.util.Queue;

public class ScheduleQueue {
    private static boolean isActive = false;
    private static final Queue<ScheduleRunnable> list = new LinkedList<>();

    public static void offer(ScheduleRunnable runnable) {
        list.offer(runnable);
        dispatch();
    }

    /**
     * 如果分发完了则将isActive置为false
     * 如果正在分发在isActive置为true
     * 每次添加都会触发dispatch分发
     */
    private static void dispatch() {
        if (isActive) {
            return;
        }
        isActive = true;

        while (true) {
            ScheduleRunnable poll = list.poll();
            if (poll == null) {
                isActive = false;
                return;
            }

            Schedule.ThreadMode mode = poll.getMode();
            Runnable runnable = poll.getRunnable();
            Schedule schedule = poll.getSchedule();
            switch (mode) {
                case MAIN:
                case MAIN_ORDERED:
                    schedule.sendToMainThread(runnable);
                    break;
                case BACKGROUND:
                case ASYNC:
                    schedule.execute(runnable);
                    break;
                default:
                    break;
            }
        }
    }
}
