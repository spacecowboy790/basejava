package ru.javawebinar.basejava;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        MainConcurrency deadlock = new MainConcurrency();
        new Thread(deadlock::operation1, "T1").start();
        new Thread(deadlock::operation2, "T2").start();
    }

    public void operation1() {
        lock1.lock();
        System.out.println("lock1 захвачен, ожидание захвата lock2.");

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock2.lock();
        System.out.println("lock2 захвачен");

        System.out.println("выполнение первой операции");

        lock2.unlock();
        lock1.unlock();
    }

    public void operation2() {
        lock2.lock();
        System.out.println("lock2 захвачен, ожидание захвата lock1.");

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock1.lock();
        System.out.println("lock1 захвачен");

        System.out.println("выполнение второй операции");

        lock1.unlock();
        lock2.unlock();
    }
}
