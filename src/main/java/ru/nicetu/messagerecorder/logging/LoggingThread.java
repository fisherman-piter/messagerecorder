package ru.nicetu.messagerecorder.logging;

import org.apache.log4j.Logger;
import ru.nicetu.messagerecorder.appenders.MessageAppender;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by trusov on 21.03.2015.
 */
public class LoggingThread extends Thread {
    private static final Logger LOG = Logger.getLogger(LoggingThread.class);

    private final BlockingQueue<Message> queue;
    private final List<MessageAppender> appenderList;

    //test commit #1
    //test commit #2
    //test commit #4
    public LoggingThread(BlockingQueue<Message> queue, List<MessageAppender> appenderList) {
        this.queue = queue;
        this.appenderList = appenderList;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Message message = queue.take();
                for (MessageAppender appender : appenderList) {
                    appender.append(message);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        LOG.info("Logging thread interrupted");
    }
}
