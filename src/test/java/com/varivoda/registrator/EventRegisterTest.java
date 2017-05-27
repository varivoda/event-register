package com.varivoda.registrator;

import com.varivoda.DateBaseUtil;
import com.varivoda.EventRegister;
import com.varivoda.EventRegisterException;
import com.varivoda.event.Event;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.CoreMatchers.is;

public class EventRegisterTest {
    
    private volatile boolean RUN = false;
    private DateBaseUtil dateBaseUtil;
    private EventRegister eventRegister;
    
    @Before
    public void setUp() throws Exception {
        eventRegister = new EventRegister();
        dateBaseUtil = new DateBaseUtil();
    }
    
    /**
     * Примитивнеый тест для добавления записей из нескольких потоков
     *
    **/
    @Test
    public void addSomeCountEventsByParallelThreads() throws Exception {
    
        int threadCount = 1000;
        int loopCount = 10;
        long rowContBefore = dateBaseUtil.getRowCount();
     
        // СОздаем потоки и ждем команды RUN
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                System.out.printf("%s is ready\n", Thread.currentThread().getName());
                while (!RUN){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
    
                try {
                    // Каждый поток доабвит несколько записей
                    System.out.printf("%s is going\n", Thread.currentThread().getName());
                    for (int j = 0; j < loopCount; j++) {
                        eventRegister.registerEvent(new Event());
                    }
                    System.out.printf("%s is done\n", Thread.currentThread().getName());
                    
                } catch (EventRegisterException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    
        RUN = true;
        SECONDS.sleep(20);
        
        long rowCount = dateBaseUtil.getRowCount();
        Assert.assertThat((long)loopCount*threadCount, is(rowCount -rowContBefore));    }
    
    @Test
    public void addSomeCountEvents() throws Exception {
        
        int countEvents = 10000;
    
        long rowContBefore = dateBaseUtil.getRowCount();
    
        for (int i = 0; i < countEvents; i++) {
            eventRegister.registerEvent(new Event());
        }
    
        Assert.assertThat((long)countEvents, is(dateBaseUtil.getRowCount()-rowContBefore));
        
    }
}