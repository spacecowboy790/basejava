package ru.javawebinar.basejava;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainDeadLock {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public Lock getLock1() {
        return lock1;
    }

    public Lock getLock2() {
        return lock2;
    }

    public static void main(String[] args) {
        MainDeadLock deadlock = new MainDeadLock();
        new Thread(() -> deadlock.makeDeadLock(deadlock.getLock1(), deadlock.getLock2())).start();
        new Thread(() -> deadlock.makeDeadLock(deadlock.getLock2(), deadlock.getLock1())).start();
    }

    public void makeDeadLock(Lock lock1, Lock lock2) {
        lock1.lock();
        System.out.println(lock1 + " — захвачен");
        lock2.lock();
        System.out.println(lock2 + " — захвачен");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock2.lock();
        System.out.println(lock2 + " — захвачен");
        lock1.lock();
        System.out.println(lock1 + " — захвачен");

        lock1.unlock();
        lock2.unlock();
    }
}
