package com.varivoda;

import com.varivoda.event.Event;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Выполняет регистрацию и получение данный о количестве записей
 */
public class EventRegister implements IEventRegister {
    
    // Счетчик записей, которые были добавлены после последнего апдейта
    private volatile int countEventsAfterLastUpdate = 0;
    
    public static final int EVENTS_COUNT_UPDATE_TRIGGER = 10_000;
    
    private ConcurrentLinkedDeque<Event> events;
    
    public EventRegister() {
        events = new ConcurrentLinkedDeque<>();
    }
    
    public void registerEvent(Event event) throws EventRegisterException {
        events.add(event);
        // Если опр. число записей ужде не было апдайта - тогда апдейтим
        if (++countEventsAfterLastUpdate > EVENTS_COUNT_UPDATE_TRIGGER) {
            countEventsAfterLastUpdate = 0;
            updateEvents(1);
        }
    }
    
    /**
     * Очищает записи, которым больше определенного кол-ва дней
     */
    private void updateEvents(int daysCount) {
        LocalDateTime nowMinusDays = LocalDateTime.now().minusDays(daysCount);
        Event event;
        while ( (event = events.peekFirst()) != null && event.getStartTime().isBefore(nowMinusDays)) {
            events.removeFirst();
        }
    }
    
    public long getEventsCountInLastMinute() throws EventRegisterException {
        return getEventsCount(60);
    }
    
    public long getEventsCountInLastHour() throws EventRegisterException {
        return getEventsCount(60 * 60);
    }
    
    public long getEventsCountInLastDay() throws EventRegisterException {
        return getEventsCount(24 * 60 * 60);
    }
    
    /**
     * @param secCount количество секунд
     * @return Возвращает количество записей за указанное количество секунд
     * @throws EventRegisterException Возникает при некорректной работе с БД
     */
    private long getEventsCount(long secCount) throws EventRegisterException {
        
        LocalDateTime nowMinusCountSec = LocalDateTime.now().minusSeconds(secCount);
        long count = 0;
    
        // Проходим элементы от хвоста к голове. Т.е. от новых к старым
        Iterator<Event> iterator = events.descendingIterator();
    
        while (iterator.hasNext()) {
            Event next = iterator.next();
            if (next.getStartTime().isAfter(nowMinusCountSec)) {
                count++;
            }
            else {
                break;
            }
        }
        return count;
        
    }
}