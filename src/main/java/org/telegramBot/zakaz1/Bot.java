package org.telegramBot.zakaz1;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        int a=0;
        if (update!=null)
        {
            if (update.hasMessage())
            {a=1;}
            else if(update.hasCallbackQuery())
            {a=2;}
            else if(update.hasInlineQuery())
            {a=3;}
         switch (a){
             case 1:
                 break;
             case 2:
                 break;
             case 3:
                 break;
         }
        }
    }

    @Override
    public String getBotUsername() {
        return "@DocumentPolanbot";
    }

    @Override
    public String getBotToken() {
        return "752923360:AAFIgs8LlIse4dqW-xxeVDxgQILuadKUvFk";
    }
}
