package ru.nicetu.messagerecorder.logging;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by trusov on 21.03.2015.
 */
public class FormatterUtilTest {
    private static String matchingMessageString = "[SYSTEM] 2015.03.21 02:03:52.555 - Hello, world!";
    private static String matchingFileNameString = "2015.03.22.log";


    @Test
    public void messageShouldMatch() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.MARCH, 21, 14, 3, 52);
        calendar.set(Calendar.MILLISECOND, 555);
        Message message = new Message(calendar.getTimeInMillis(), "Hello, world!", SourceType.SYSTEM);
        String test = FormatterUtil.formatMessage(message);
        assertEquals(test, matchingMessageString);
    }

    @Test
    public void fileNameShouldMatch() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.MARCH, 22, 14, 3, 52);
        calendar.set(Calendar.MILLISECOND, 555);
        Message message = new Message(calendar.getTimeInMillis(), "Hello, world!", SourceType.SYSTEM);
        String test = FormatterUtil.getFileName(message);
        assertEquals(test, matchingFileNameString);
    }
}
