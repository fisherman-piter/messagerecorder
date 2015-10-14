package ru.nicetu.messagerecorder.logging;

import java.util.Calendar;

/**
 * Created by trusov on 21.03.2015.
 * Утилита форматирования сообщений
 */
public final class FormatterUtil {
    private FormatterUtil() {
    }

    public static String formatMessage(Message message) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(message.getTime());

        StringBuilder result = new StringBuilder();
        result.append("[").append(message.getSourceType()).append("]");
        result.append(" ");
        result.append(calendar.get(Calendar.YEAR));
        result.append(".");
        result.append(String.format("%02d", calendar.get(Calendar.MONTH) + 1));
        result.append(".");
        result.append(String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)));

        result.append(" ");

        result.append(String.format("%02d", calendar.get(Calendar.HOUR)));
        result.append(":");
        result.append(String.format("%02d", calendar.get(Calendar.MINUTE)));
        result.append(":");
        result.append(String.format("%02d", calendar.get(Calendar.SECOND)));
        result.append(".");

        result.append(String.format("%02d", calendar.get(Calendar.MILLISECOND)));

        result.append(" - ");
        result.append(message.getMessage());
        return result.toString();
    }

    public static String getFileName(Message message) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(message.getTime());

        StringBuilder result = new StringBuilder();
        result.append(calendar.get(Calendar.YEAR));
        result.append(".");
        result.append(String.format("%02d", calendar.get(Calendar.MONTH) + 1));
        result.append(".");
        result.append(String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH)));

        result.append(".log");
        return result.toString();
    }
}
