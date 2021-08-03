package ru.javawebinar.basejava;

public class MainDeadLock {

    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> makeDeadLock(LOCK1, LOCK2)).start();
        new Thread(() -> makeDeadLock(LOCK2, LOCK1)).start();
    }

    public static void makeDeadLock(Object lock1, Object lock2) {
        synchronized (lock1) {
            String threadName = Thread.currentThread().getName();
            System.out.println("Поток " + threadName + " захватил ресурс " + lock1);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Поток " + threadName + " ожидает захвата ресурса " + lock2);

            synchronized (lock2) {
                System.out.println("Поток " + threadName + " захватил ресурс " + lock2);
            }
        }

        synchronized (lock2) {
            String threadName = Thread.currentThread().getName();
            System.out.println("Поток " + threadName + " захватил ресурс " + lock2);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Поток " + threadName + " ожидает захвата ресурса " + lock1);

            synchronized (lock1) {
                System.out.println("Поток " + threadName + " захватил ресурс " + lock1);
            }
        }
    }
}
