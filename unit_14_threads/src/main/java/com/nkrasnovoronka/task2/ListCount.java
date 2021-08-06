package com.nkrasnovoronka.task2;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListCount {
    public static void main(String[] args) {
        List<Integer> primeNumbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.toList());
        FutureTask<Integer> leftPart = new FutureTask<>(new MyThread(primeNumbers.subList(0, primeNumbers.size() / 2)));
        FutureTask<Integer> rightPart = new FutureTask<>(new MyThread(primeNumbers.subList(primeNumbers.size() / 2, primeNumbers.size())));

        Thread t1 = new Thread(leftPart);
        Thread t2 = new Thread(rightPart);

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int res = 0;
        try {
            Integer leftPartRes = leftPart.get();
            Integer rightPartRes = rightPart.get();
            System.out.println("leftPart = " + leftPartRes);
            System.out.println("rightPart = " + rightPartRes);
            res = rightPartRes + leftPartRes;
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Total number of primes: " + res);

    }

}

class MyThread implements Callable<Integer> {
    private final List<Integer> numbers;

    public MyThread(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer call() {
        int primeNumbers = 0;
        for (int i : numbers) {
            if (isPrime(i)) {
                primeNumbers++;
            }
        }
        return primeNumbers;
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
