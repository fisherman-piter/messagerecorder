package ru.nicetu.messagerecorder.logging;

import org.apache.log4j.Logger;
import ru.nicetu.messagerecorder.appenders.DatabaseAppender;
import ru.nicetu.messagerecorder.appenders.FileAppender;
import ru.nicetu.messagerecorder.appenders.MessageAppender;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by trusov on 21.03.2015.
 */
public final class LogSystem {
    private static final Logger LOG = Logger.getLogger(LogSystem.class);
    private static final LogSystem INSTANCE = new LogSystem();

    private final BlockingQueue<Message> queue = new LinkedBlockingDeque<Message>();
    private final List<MessageAppender> appenderList = new ArrayList<MessageAppender>();
    private LoggingThread loggingThread;
    private volatile boolean initialized;

    private LogSystem() {

    }

    public static LogSystem getInstance() {
        return INSTANCE;
    }

    public void init() {
        loggingThread = new LoggingThread(queue, appenderList);
        loggingThread.start();

        registerAppenders();

        initialized = true;
        LOG.info("LogSystem initialized");
    }

    private void registerAppenders() {
        appenderList.add(new FileAppender());
        appenderList.add(new DatabaseAppender());
    }

    private void closeAppenders() {
        for (MessageAppender appender : appenderList) {
            appender.close();
        }
    }

    public void log(Message message) {
        if (!initialized) {
            throw new IllegalStateException("LogSystem is uninitialized");
        }

        try {
            queue.put(message);
        } catch (InterruptedException e) {
        }
    }

    public void stop() {
        if (loggingThread != null) {
            loggingThread.interrupt();
        }
        closeAppenders();
        LOG.info("LogSystem stopped");
    }
}
