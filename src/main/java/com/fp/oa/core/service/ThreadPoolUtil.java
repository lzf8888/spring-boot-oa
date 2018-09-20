package com.fp.oa.core.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.fp.oa.core.thread.NamedThreadFactory;

/**
 * utility for execute thread
 *
 */
public class ThreadPoolUtil {
    
    private ExecutorService executor= Executors.newFixedThreadPool(5,new NamedThreadFactory("DATA-SYNC-POOL"));

    public void run(Runnable runner) {
        executor.execute(runner);
    }
    
    public <T> T submit(Callable<T> caller) throws InterruptedException, ExecutionException {
        Future<T> future= executor.submit(caller);
        return future.get();
    }
}
