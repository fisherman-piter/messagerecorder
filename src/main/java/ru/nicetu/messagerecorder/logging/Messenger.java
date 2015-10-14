package ru.nicetu.messagerecorder.logging;

/**
 * Created by trusov on 21.03.2015.
 */
public final class Messenger {

    private Messenger() {
    }


    /**
     * Отправляет сообщение
     *
     * @param message    - текстовое сообщение
     * @param sourceType - тип сообщения (система или пользователь)
     * @throws - IllegalArgumentException - если сообщение пустое, или null, либо тип
     *           сообщения Null
     */
    public static void recordMessage(String message, SourceType sourceType) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message is empty");
        }

        if (sourceType == null) {
            throw new IllegalArgumentException("Source type is null");
        }

        long time = System.currentTimeMillis();
        LogSystem.getInstance().log(new Message(time, message, sourceType));
    }
}
