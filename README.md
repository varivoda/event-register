# Event-Register

## Data base
Для регистрации и хранения событий ипользуется Mysql DB и паттерн DAO. 

Простая таблица выглядит так:

    CREATE TABLE EVENTS
    ( 
    ID BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    DATE DATETIME(3) NOT NULL
    );

для простоты конфигурция подключению описана в константах в классе **EventDAOMySQL**

## Conection pool

Для распределения нагрузки на запись в БД используется connection pool:  **Apache DBCP**.

Его конфигурация также для простоты задается в **EventDAOMySQL**

## Tests

Также реализовано 2 теста, оба проверяют корректное добавление записей в БД.

Но один загружает event-register одновременными запросами. (Баловался на 1000 потоках, записывающих по 10 событий - полет нормальный) 