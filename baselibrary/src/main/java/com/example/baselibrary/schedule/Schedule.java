package com.example.baselibrary.schedule;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 简单的实现线程的切换
 * <p>
 * ThreadMode.POSTING，默认的线程模式，在那个线程发送事件就在对应线程处理事件
 * ThreadMode.MAIN，如在主线程（UI线程）发送事件，则直接在主线程处理事件；如果在子线程发送事件，然后通过 Handler 切换到主线程
 * ThreadMode.MAIN_ORDERED，无论在那个线程发送事件，通过 Handler 切换到主线程
 * ThreadMode.BACKGROUND，如果在主线程发送事件，通过线程池依次处理事件；如果在子线程发送事件，则直接在发送事件的线程处理事件。
 * ThreadMode.ASYNC，无论在那个线程发送事件，都将事件通过线程池处理。
 * <p>
 * 没有包含任务队列
 */
public class Schedule {
    private static final int CORE_POOL_SIZE = 0;
    private static final int MAX_POOL_SIZE = 8;
    private static final int KEEP_ALIVE_TIME = 3;

    private static final int RUN_MAIN_THREAD = 0x0001;

    private static final MainHandler handler = new MainHandler(Looper.getMainLooper());

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>());

    public enum ThreadMode {
        POSITION,
        MAIN,
        MAIN_ORDERED,
        BACKGROUND,
        ASYNC
    }

    private static class MainHandler extends Handler {

        public MainHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case RUN_MAIN_THREAD:
                    Runnable runnable = (Runnable) msg.obj;
                    runnable.run();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 在UI线程执行，则直接交给UI线程执行
     * 在子线程执行，则通过handler发送给主线程执行
     */
    public static void runOnMainThread(Runnable runnable) {
        runOnMainThread(0, runnable);
    }

    public static void runOnMainThread(int delay, Runnable runnable) {
        Thread thread = Thread.currentThread();
        inMainThread(delay, runnable, thread);
    }

    private static void inMainThread(Runnable runnable, Thread thread) {
        inMainThread(0, runnable, thread);
    }

    private static void inMainThread(int delay, Runnable runnable, Thread thread) {
        if (thread == Looper.getMainLooper().getThread()) {
            if (delay > 0) {
                sendToMainThread(delay, runnable);
            } else {
                runnable.run();
            }
        } else {
            sendToMainThread(delay, runnable);
        }
    }

    private static void sendToMainThread(Runnable runnable) {
        sendToMainThread(0, runnable);
    }

    private static void sendToMainThread(int delay, Runnable runnable) {
        if (delay < 0) {
            delay = 0;
        }
        Message message = Message.obtain();
        message.what = RUN_MAIN_THREAD;
        message.obj = runnable;
        handler.sendMessageDelayed(message, delay);
    }

    /**
     * 在子线程中执行，则直接交给当前子线程运行
     * 在UI线程执行，则放入线程池交给子线程运行
     */
    public static void runOnBackground(Runnable runnable) {
        Thread thread = Thread.currentThread();
        inAsync(runnable, thread);
    }

    private static void inAsync(Runnable runnable, Thread thread) {
        if (thread == Looper.getMainLooper().getThread()) { // 在主线程中运行
            executor.execute(runnable);
        } else {
            runnable.run();
        }
    }

    /**
     * 制定线程运行类型
     */
    public static void runWithThreadMode(ThreadMode mode, Runnable runnable) {
        Thread thread = Thread.currentThread();
        switch (mode) {
            case POSITION:
                runnable.run();
                break;
            case MAIN:
                inMainThread(runnable, thread);
                break;
            case MAIN_ORDERED:
                sendToMainThread(runnable);
                break;
            case BACKGROUND:
                inAsync(runnable, thread);
                break;
            case ASYNC:
                executor.execute(runnable);
                break;
            default:
                break;
        }
    }

}
