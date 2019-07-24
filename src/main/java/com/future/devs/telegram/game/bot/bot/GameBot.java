package com.future.devs.telegram.game.bot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Shoh Jahon tomonidan 7/24/2019 da qo'shilgan.
 */
@Component
public class GameBot extends TelegramLongPollingBot {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String chatId = update.getMessage().getChatId().toString();
            String msg = update.getMessage().getText();

            logger.info("update: -> chatId: {} <---> message: {} ",chatId,msg);

            sendMessage(chatId,msg);
        }
    }

    @Override
    public String getBotUsername() {
        return "shopping_winner_bot";
    }

    public synchronized void sendMessage(String chatId, String msg){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(msg);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Error",e);
        }
    }

    @Override
    public String getBotToken() {
        return "937267989:AAGZeWrdYc_1XPWAr6P_B9uGiZpSN6iohKI";
    }
}
