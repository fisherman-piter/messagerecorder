package ru.nicetu.messagerecorder.appenders;

import ru.nicetu.messagerecorder.logging.Message;

/**
 * Created by trusov on 21.03.2015.
 */
public interface MessageAppender {

    public void append(Message message);

    /**
     * Close appender
     */
    public void close();
}
