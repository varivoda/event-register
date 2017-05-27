package com.varivoda.registrator;

import com.varivoda.DateBaseUtil;
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
    
    @Test
    public void parallel() throws Exception {
    
        int countEvents = 1000;
        long rowContBefore = dateBaseUtil.getRowCount();
        System.out.printf("BEFORE %d", rowContBefore );
    
        for (int i = 0; i < countEvents; i++) {
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
                    System.out.printf("%s is going\n", Thread.currentThread().getName());
                    eventRegister.registerEvent(new Event());
                    System.out.printf("%s is done\n", Thread.currentThread().getName());
                    
                } catch (EventRegisterException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    
        RUN = true;
        SECONDS.sleep(20);
        long rowCount = dateBaseUtil.getRowCount();
        System.out.printf("AFTER %d \n", rowCount);
        Assert.assertThat((long)countEvents, is(rowCount -rowContBefore));    }
    
    @Test
    public void addSomeCountEvents() throws Exception {
    
        
        int countEvents = 10;
    
        long rowContBefore = dateBaseUtil.getRowCount();
    
        for (int i = 0; i < countEvents; i++) {
            eventRegister.registerEvent(new Event());
        }
    
        Assert.assertThat((long)countEvents, is(dateBaseUtil.getRowCount()-rowContBefore));
        
    }
}