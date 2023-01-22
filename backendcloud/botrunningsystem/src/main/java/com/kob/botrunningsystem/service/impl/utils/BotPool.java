package com.kob.botrunningsystem.service.impl.utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BotPool extends Thread{
    private final ReentrantLock lock = new ReentrantLock();
    //两种循环机制，一种是无论怎样都结束本次循环。
    //另一种如果空就要阻塞，有消息就要唤醒
    private final Condition condition = lock.newCondition();
    private final Queue<Bot> bots = new LinkedList<>();//不一定线程安全，需要手动管理
    //queue在两个线程中操作，一个喂任务，一个消耗任务。有冲突需要加锁，

    public void addBot(Integer userId, String botCode, String input) {
        lock.lock();
        try {
            bots.add(new Bot(userId, botCode, input));
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private void consume (Bot bot) {
        Consumer consumer = new Consumer();
        consumer.startTimeout(2000, bot);
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (bots.isEmpty()) {
                try {
                    condition.await(); //阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    lock.unlock();
                    break;
                }
            } else {
                Bot bot = bots.remove(); //队头
                lock.unlock();
                consume(bot);  //比较耗时，所以要在解锁之后，否则加操作也会被阻塞
                //实现了一个消息队列
            }
        }
    }
}
