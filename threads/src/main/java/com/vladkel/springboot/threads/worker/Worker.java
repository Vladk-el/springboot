package com.vladkel.springboot.threads.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Worker implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Worker.class);

    public Worker() {
        LOGGER.info("Hello worker {} !", Thread.currentThread().getName());
    }

    @Override
    public void run() {
        try {

            LOGGER.info("Worker {} is running ...", Thread.currentThread().getName());

            while(!Thread.currentThread().isInterrupted()) {
                LOGGER.info("Hey, i'm worker {} !", Thread.currentThread().getName());
                Thread.sleep(2500);
            }
        } catch (InterruptedException e) {
            LOGGER.error("Worker {} interrupted by ThreadPool: {}", Thread.currentThread().getName(), e.getMessage());
        } finally {
            close();
        }
    }

    private void close() {
        LOGGER.info("Worker {} is closing down !", Thread.currentThread().getName());
    }
}
