package com.example.baselibrary.serializer;

import com.example.baselibrary.schedule.Schedule;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1、将任务按顺序执行
 * 2、可以指定下一个任务所在的线程
 * 3、可以将上一个任务的执行结果传递给下一个任务
 * 4、链式调用
 */
public class Serializer {
    // 任务队列，先进来的任务优先执行
    private Queue<Op> queue;

    public static Serializer op(Op op) {
        Serializer serializer = new Serializer();
        serializer.addOp(op);
        return serializer;
    }

    public Serializer addOp(Op op) {
        if (queue == null) {
            queue = new LinkedList<>();
        }
        if (op != null) {
            queue.offer(op);
        }
        return Serializer.this;
    }

    public void next() {
        innerNext(null);
    }

    public void next(Object o) {
        innerNext(o);
    }

    public void next(Object ...objects) {
        Object[] o = new Object[0];
        if (objects != null) {
            o = objects;
        }
        innerNext(o);
    }

    private <Z> void innerNext(Z z) {
        if (queue != null && !queue.isEmpty()) {
            Op poll = queue.poll();
            if (poll instanceof MainOp) {
                int delay = ((MainOp) poll).delay;
                Schedule.getInstance().runOnMainThread(delay, new Runnable() {
                    @Override
                    public void run() {
                        poll.onNext(Serializer.this, z);
                    }
                });
            } else if (poll instanceof AsyncOp) {
                Schedule.getInstance().runOnBackground(new Runnable() {
                    @Override
                    public void run() {
                        poll.onNext(Serializer.this, z);
                    }
                });
            } else {
                poll.onNext(Serializer.this, z);
            }
        }
    }

    public interface Op<T> {
        void onNext(Serializer serializer, T t);
    }

    public static abstract class MainOp<T> implements Op<T> {
        private final int delay;

        public MainOp() {
            delay = 0;
        }

        public MainOp(int delay) {
            this.delay = delay;
        }
    }

    public interface AsyncOp<T> extends Op<T> {
    }
}
