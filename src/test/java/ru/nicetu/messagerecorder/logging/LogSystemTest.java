package ru.nicetu.messagerecorder.logging;

import org.junit.Test;

/**
 * Created by trusov on 21.03.2015.
 */
public class LogSystemTest {

    @Test(expected = IllegalStateException.class)
    public void shouldFailWhenNotInitialized() {
        LogSystem sut = LogSystem.getInstance();
        Message message = new Message(System.currentTimeMillis(), "Hello", SourceType.USER);
        sut.log(message);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenMessageNull() {
        String msg = null;
        Messenger.recordMessage(msg, SourceType.SYSTEM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenMessageEmpty() {
        String msg = " ";
        Messenger.recordMessage(msg, SourceType.SYSTEM);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenSourceTypeNull() {
        String msg = "message";
        Messenger.recordMessage(msg, null);
    }
}
