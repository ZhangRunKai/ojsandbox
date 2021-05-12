package com.ojsandbox.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author zhang run kai
 * @version 1.0
 * @date 2021/5/12 20:22
 */
public class Sandbox {
    private Integer maxLen;
    private static ThreadGroup threadGroup = new ThreadGroup("requestThread");

    private static ExecutorService judgeThreadPool = Executors.newSingleThreadExecutor(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(threadGroup,r);
            thread.setName("judgeThreadPool");
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {

                }
            });
            return thread;
        }
    });
}
