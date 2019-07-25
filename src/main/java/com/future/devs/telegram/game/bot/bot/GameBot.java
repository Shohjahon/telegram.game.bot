package com.future.devs.telegram.game.bot.bot;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.games.Game;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

/**
 * Shoh Jahon tomonidan 7/24/2019 da qo'shilgan.
 */
@Component
public class GameBot extends TelegramLongPollingBot {
    private final Logger logger = LoggerFactory.getLogger(getClass());
//    @Value("#{systemEnvironment['token'] ?: 'DEFAULT_VALUE'}")
    private String token;


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
        return "uzb_abiturent_bot";
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
        token = getSytemEnv("token");
        logger.info("TOKEN: {}",token);
        return token;
    }

    public String getSytemEnv(String name){
        Map<String,String> env = System.getenv();
        for (String envName:env.keySet()){
            System.out.format("%s=%s%n",
                    envName,
                    env.get(envName));
            if (envName.equals(name)) return env.get(envName);
        }
        return null;
    }
}
