package ru.nicetu.messagerecorder.appenders;

import ru.nicetu.messagerecorder.logging.Message;

/**
 * Created by trusov on 21.03.2015.
 * Аппендер записи лога в БД
 */
public class DatabaseAppender implements MessageAppender {

    @Override
    public void append(Message message) {
        System.out.println("Database appender is unimplemented!");
    }

    @Override
    public void close() {
        System.out.println("Closingb database appender is not implemented yet");
    }
}
