package ru.nicetu.messagerecorder.appenders;

import org.apache.log4j.Logger;
import ru.nicetu.messagerecorder.logging.FormatterUtil;
import ru.nicetu.messagerecorder.logging.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by trusov on 21.03.2015.
 * Аппендер записи лога в файл
 */
public class FileAppender implements MessageAppender {
    private static final Logger LOG = Logger.getLogger(FileAppender.class);

    private static final int WRITE_INTERVAL = 1000 * 60 * 5;
    private final List<Message> messageList = new ArrayList<Message>();
    private final WriterThread writerThread = new WriterThread();

    public FileAppender() {
        writerThread.start();
    }

    @Override
    public void append(Message message) {
        synchronized (messageList) {
            messageList.add(message);
        }
    }

    @Override
    public void close() {
        writerThread.interrupt();
        try {
            writerThread.join();
        } catch (InterruptedException e) {

        }
        LOG.info("File appender closed");
    }

    private class WriterThread extends Thread {

        @Override
        public void run() {
            LOG.info("Writer thread started");

            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(WRITE_INTERVAL);
                    List<Message> list = new ArrayList<Message>();
                    synchronized (messageList) {
                        list.addAll(messageList);
                        messageList.clear();
                    }

                    for (Message message : list) {
                        LOG.info("Write message: " + FormatterUtil.formatMessage(message));
                        FileWriter fw = null;
                        try {
                            fw = new FileWriter(FormatterUtil.getFileName(message), true);
                            fw.write(FormatterUtil.formatMessage(message) + "\n");
                        } catch (IOException e) {
                            logError(message, e);
                        } finally {
                            closeQuietly(fw);
                        }
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            LOG.info("Writer thread interrupted.");
        }

        private void logError(Message message, Exception e) {
            FileWriter fw = null;
            final String fileName = FormatterUtil.formatMessage(message);
            try {
                fw = new FileWriter(fileName + ".error");
                fw.write("Error occured while writing log entry to the file: " + fileName + "\n");
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                fw.write(errors.toString());
            } catch (IOException e1) {
                LOG.error("Error occured while trying to write error report");
            } finally {
                closeQuietly(fw);
            }
        }

        private void closeQuietly(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
