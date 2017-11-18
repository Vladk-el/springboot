package com.vladkel.springboot.threads;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.vladkel.springboot.threads.worker.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * Next step
 *  add a blocking queue
 *  create reader worker
 *  create insert worker
 *  fill queue then let's see how workers live and ... DIE on future.cancel(true)
 */
@Service
public class ThreadPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPool.class);

    private final int parallelism = 4;

    private Map<Integer, Future<?>> workerStatuses;
    private ExecutorService workerPool;


    @Autowired
    public ThreadPool() {
        workerStatuses = Collections.synchronizedMap(new HashMap<Integer, Future<?>>());

        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("talkwalker-worker-%d").setDaemon(true).build();
        workerPool = Executors.newFixedThreadPool(parallelism, threadFactory);

        startWorkers();
        manage();
    }

    private void manage() {

        for (; ; ) {
            try {
                Thread.sleep(10 * 1000);
                startWorkers();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void startWorkers() {

        LOGGER.info("startWorkers() => should start {} workers", parallelism);

        // first stop the running workers if any (security first!).
        if(!workerStatuses.isEmpty()) {
            stopWorkers();
        }

        // then launch new workers with the new BigFishManager
        for (int i = 1; i <= parallelism; i++) {
            Worker worker = new Worker();
            workerStatuses.put(i, workerPool.submit(worker));
        }

    }

    private void stopWorkers() {

        LOGGER.info("############ stopWorkers() ############");

        for (int i = 1; i <= parallelism; i++) {
            Future<?> future = workerStatuses.get(i);
            if (future != null) {
                future.cancel(true);
            }
        }

    }


}
