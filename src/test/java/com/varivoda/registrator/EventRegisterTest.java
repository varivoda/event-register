package com.varivoda.registrator;

import com.varivoda.EventRegister;
import com.varivoda.EventRegisterException;
import com.varivoda.event.Event;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.CoreMatchers.is;

public class EventRegisterTest {
    
    private volatile boolean RUN = false;
    private EventRegister eventRegister;
    
    @Before
    public void setUp() throws Exception {
        eventRegister = new EventRegister();
    }
    
    /**
     * Примитивнеый тест для добавления записей из нескольких потоков
     *
    **/
    @Test
    public void addSomeCountEventsByParallelThreads() throws Exception {
        
        
        long expEventsCountInLastMinute = 10_000;
        long expEventsCountInLastHour = 10_000 + expEventsCountInLastMinute;
        long expEventsCountInLastDay = 10_000 + expEventsCountInLastHour;
        
        // Создаем данные для проверки. Добавляем в азные промежутки времени по 10_000 тыс заисей
        registerEvents(LocalDateTime.now().minusDays(2), 10_000);
        registerEvents(LocalDateTime.now().minusHours(10), 10_000);
        registerEvents(LocalDateTime.now().minusMinutes(30), 10_000);
        registerEvents(LocalDateTime.now().minusSeconds(10), 10_000);
    
        
        int threadCount = 1000;
        int loopCount = 1000;
     
        // Нагружаем и регистрируем 1000K эвентов из параллельных потоков
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                while (!RUN){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    // Каждый поток доабвит несколько записей
                    for (int j = 0; j < loopCount; j++) {
                        eventRegister.registerEvent(new Event());
                    }
                    
                } catch (EventRegisterException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    
        // Запускаем почти одновременно потоки
        RUN = true;
        // Ждем завершения
        SECONDS.sleep(10);
        
        //Проверям, что кол-во записей за последние мин, час, день совпадает с ожид-ым
        Assert.assertThat("Кол-во за посл. мин не совпадает", eventRegister.getEventsCountInLastMinute(), is((long) loopCount * threadCount + expEventsCountInLastMinute));
        Assert.assertThat("Кол-во за посл. час не совпадает", eventRegister.getEventsCountInLastHour(), is((long) loopCount * threadCount + expEventsCountInLastHour));
        Assert.assertThat("Кол-во за посл. день не совпадает", eventRegister.getEventsCountInLastDay(), is((long) loopCount * threadCount + expEventsCountInLastDay));
    }
    
    //Заполняет значения с указ. датой
    public void registerEvents(LocalDateTime time, int count) throws EventRegisterException {
        for (int i = 0; i < count; i++) {
            eventRegister.registerEvent(new Event(0, time));
        }
    }
    
}