package com.varivoda;

import com.varivoda.event.Event;

/**
 * Basic interface
 */
public interface IEventRegister {
    
    /**
     * Регестрирует событие в системе
     * @param event событие, которое должно быть зарегестрировано
     * @throws EventRegisterException Если происходит ошибка при регистрации
     */
    void registerEvent(Event event) throws EventRegisterException;
    
    /**
     * @return Количесвто записей, зарегестрированных за последнюю минуту
     * @throws EventRegisterException Если происходит ошибка получении данных
     */
    long getEventsCountInLastMinute() throws EventRegisterException;
    
    /**
     * @return Количесвто записей, зарегестрированных за последний час
     * @throws EventRegisterException Если происходит ошибка получении данных
     */
    long getEventsCountInLastHour() throws EventRegisterException;
    
    /**
     * @return Количесвто записей, зарегестрированных за последний день
     * @throws EventRegisterException Если происходит ошибка получении данных
     */
    long getEventsCountInLastDay() throws EventRegisterException;
}

