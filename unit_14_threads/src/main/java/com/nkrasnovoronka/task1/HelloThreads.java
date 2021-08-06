package com.nkrasnovoronka.task1;

import java.util.ArrayDeque;
import java.util.Deque;

public class HelloThreads {

    public static void main(String[] args) {
        BlockingDeque blockingDeque = new BlockingDeque();

        for (int i = 0; i < 50; i++) {
            blockingDeque.put(getTask(String.valueOf(i)));
        }

        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    blockingDeque.get().run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();


        try {
            Thread.sleep(5000);
            thread.interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static  synchronized Runnable getTask(String name) {
        return () -> {
            System.out.printf("Hello from thread (%s)%n", name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
    }
}

class BlockingDeque {
    Deque<Runnable> tasks = new ArrayDeque<>();

    public synchronized Runnable get() throws InterruptedException {
        while (tasks.isEmpty()) {
            wait();
        }
        return tasks.pollLast();
    }

    public synchronized void put(Runnable task) {
        tasks.addLast(task);
        notifyAll();
    }


}


