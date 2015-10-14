package ru.nicetu.messagerecorder.logging;

/**
 * Created by trusov on 21.03.2015.
 */
public class Message {
    private final long time;
    private final String message;
    private final SourceType sourceType;

    public Message(long time, String message, SourceType sourceType) {
        this.time = time;
        this.message = message;
        this.sourceType = sourceType;
    }

    public long getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "time=" + time +
                ", message='" + message + '\'' +
                ", sourceType=" + sourceType +
                '}';
    }
}
