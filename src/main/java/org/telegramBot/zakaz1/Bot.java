package org.telegramBot.zakaz1;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class Bot extends TelegramLongPollingBot {
   boolean shutdown=false;
    Map<String,User> users=new HashMap<>();
    User user;
    String support_id="314254027";
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage())
        if(update.getMessage().hasSticker())
            shutdown=false;
        if (!shutdown)
        if (update.hasMessage()){
            System.out.println(update.getMessage().getChatId().toString());
            if (!users.containsKey(update.getMessage().getChatId().toString())) {
                users.put(update.getMessage().getChatId().toString(), new User(update.getMessage().getChatId().toString()));
            user=users.get(update.getMessage().getChatId().toString());
            }
            else
            {
             user=users.get(update.getMessage().getChatId().toString());
            }
            if (update.getMessage().isReply())
            {
                try {
                    sendApiMethod(new SendMessage().setChatId(update.getMessage().getReplyToMessage().getText().split("from")[1].trim()).setText(update.getMessage().getText()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (update.getMessage().getText()!=null)
            {

                switch (update.getMessage().getText())
                {
                    case "/shut":
                        shutdown=true;
                        break;
                    case "/start":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Hello i am DocumentBot",1,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Список документов":
                        user.setAdmin_support(false);

                        try {
                            sendApiMethod(send_Message_With_Remake("...",2,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Слудующие документы":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("...",3,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Вернуться назад в меню":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("...",1,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "1.Приглашения":
                        user.setAdmin_support(false);
                            sendMessage("все заполненные страницы паспорта"
                                    ,update.getMessage().getChatId().toString()
                                    ,"1.1.1"
                                    ,"1.1.2");
                        break;
                    case "2.Карта побыту":
                        user.setAdmin_support(false);
                        sendMessage("запись на консультацию (индивидуально"
                                ,update.getMessage().getChatId().toString()
                                ,"1.2.1"
                                ,"1.2.2");

                        break;
                    case "3.Мельдунок + песель":
                        user.setAdmin_support(false);
                        sendMessage("первая страница паспорта и печать, либо виза, либо карта"
                                ,update.getMessage().getChatId().toString()
                                ,"1.3.1"
                                ,"1.3.2");

                        break;
                    case "4.Умовы найму":
                        user.setAdmin_support(false);
                        sendMessage("фото первой страницы паспорта и с какого числа"
                                ,update.getMessage().getChatId().toString()
                                ,"1.4.1"
                                ,"1.4.2");

                        break;
                    case "5.Wstępne (powyżej 3 m)":
                        user.setAdmin_support(false);
                        sendMessage("фото паспорта, место жительства в Варшаве\n" +
                                        " (при необходиомости: название фирмы,\n" +
                                        " адрес фирмы, должность, с какого числа)"
                                ,update.getMessage().getChatId().toString()
                                ,"1.5.1"
                                ,"1.5.2");

                        break;
                    case "6.Сан-эпид с (orzeczeniem) и анализами":
                        user.setAdmin_support(false);
                        sendMessage("фото паспорта, место жительства в Варшаве\n" +
                                        " (при необходиомости: название фирмы,\n" +
                                        " адрес фирмы, должность, с какого числа)"
                                ,update.getMessage().getChatId().toString()
                                ,"1.6.1"
                                ,"1.6.2");

                        break;
                    case "7.Психотесты для водителей":
                        user.setAdmin_support(false);
                        sendMessage("фото паспорта, место жительства в Варшаве"
                                ,update.getMessage().getChatId().toString()
                                ,"1.7.1"
                                ,"1.7.2");

                        break;
                    case "8.Orzeczenie для водителей":
                        user.setAdmin_support(false);
                        sendMessage("фото паспорта, место жительства в Варшаве"
                                ,update.getMessage().getChatId().toString()
                                ,"1.8.1"
                                ,"1.8.2");

                        break;
                    case "9.Код 95 (литовский) с переводом на\n польский, психотестами и orzeczeniem":
                        user.setAdmin_support(false);
                        sendMessage("фото паспорта, место жительства в Варшаве,\n" +
                                        " фото прав две стороны"
                                ,update.getMessage().getChatId().toString()
                                ,"1.9.1"
                                ,"1.9.2");

                        break;
                    case "10.Получение банковского кредита":
                        user.setAdmin_support(false);
                        sendMessage("номер паспорта"
                                ,update.getMessage().getChatId().toString()
                                ,"1.10.1"
                                ,"1.10.2");

                        break;
                    case "11.Выписка из банка":
                        user.setAdmin_support(false);
                        sendMessage("-//-"
                                ,update.getMessage().getChatId().toString()
                                ,"1.11.1"
                                ,"1.11.2");
                        break;
                    case "12.Страховка авто/человек":
                        sendMessage("фото паспорта, место жит. \n" +
                                        "В Варшаве (дата с какого по какое)/ фото техпаспорта"
                                ,update.getMessage().getChatId().toString()
                                ,"1.12.1"
                                ,"1.12.2");
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("фото паспорта, место жит. \n" +
                                            "В Варшаве (дата с какого по какое)/ фото техпаспорта"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "13.Анализы на сальмонеллу (analiza na nosicielstwo)":
                        user.setAdmin_support(false);
                        sendMessage("фото паспорта, место жительства в Варшаве"
                                ,update.getMessage().getChatId().toString()
                                ,"1.13.1"
                                ,"1.13.2");

                        break;
                    case "14.Получение номеров PESEL и NIP":
                        sendMessage("первая страница паспорта и печать,\n" +
                                        " либо виза, либо карта"
                                ,update.getMessage().getChatId().toString()
                                ,"1.14.1"
                                ,"1.14.2");

                        break;
                    case "15.Водительское удостоверение Украины любой категории с записью в базе ГАИ":
                        user.setAdmin_support(false);
                        sendMessage("-//-"
                                ,update.getMessage().getChatId().toString()
                                ,"1.15.1"
                                ,"1.15.2");
                        break;
                    case "16.Заполнение налоговых деклараций PIT":
                        user.setAdmin_support(false);
                        sendMessage("PIT 11, номер банк. Счета, адрес"
                                ,update.getMessage().getChatId().toString()
                                ,"1.16.1"
                                ,"1.16.2");
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("PIT 11, номер банк. Счета, адрес"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "17.Справка о несудимости":
                        user.setAdmin_support(false);
                        sendMessage("сами пусть берут Czerniakowska 100\""
                                ,update.getMessage().getChatId().toString()
                                ,"1.17.1"
                                ,"1.17.2");

                        break;
                    case "18.БХП":
                        user.setAdmin_support(false);
                        sendMessage("первая страница паспорта, с какого числа"
                                ,update.getMessage().getChatId().toString()
                                ,"1.18.1"
                                ,"1.18.2");

                        break;
                    case "1.Приглашение воеводское":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("...",5,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "2.Полугодовое":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Страны: Украина, Росия, Беларусь.\n Цена 180 зл.\n Срок до 10 дн."));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "3.Комплект Документов на карту побыту (залончник, умова, КРС)":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Цена 400 зл.\n Срок 1-2 дн."));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "4.Карта побыту рабочая (оплата внеска входит)":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Любая страна, нет ничего.\n Цена 2900(900/900/110) зл.\n Срок 6-8 мес."));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "5.Карта побыту рабочая (оплата внеска не входит)":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Любая страна, есть работодатель.\n Цена 1000(500/500/0) зл.\n Срок 6-8 мес."));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "6.Мельдунок (1 мес) + ПЕСЕЛЬ":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Любая страна.\n Цена 150 зл.\n Срок 1 час.\n Каждый след месяц 75 зл"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "7.Умовы найму":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Любая страна.\n Цена 200 зл.\n Срок в течении 1 дня"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "8.Wstępne (powyżej 3 m)":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Любая страна.\n Цена 60 зл.\n Срок в течении 1 дня."));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "9.Сан-эпид с (orzeczeniem) и анализами":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Любая страна.\n Цена 80 зл.\n Срок в течении 1 дня."));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Слудующие усуги":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("...",6,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "1.Украина, Росиия, Белларусь":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Цена 900 зл.\n Срок до 40дн"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "2.Грузия и все зак. на -АН (страны СНГ бывшего)":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Цена 1100 зл.\n Срок до 40дн"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "3.Любые другие страны":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Цена 1400 зл.\n Срок до 40дн"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "10.Психотесты для водителей":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Любая страна.\n Цена 70 зл.\n Срок в течении 1 дня."));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "11.Orzeczenie для водителей":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Любая страна.\n Цена 70 зл.\n Срок в течении 1 дня."));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "12.Код 95 (литовский) с переводом на польский, психотестами и orzeczeniem":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("...",7,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "1.Короткий":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Любая страна.\n Цена 1300 зл.\n Срок до 10 дн."));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "2.Длинный":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Любая страна.\n Цена 2800 зл.\n Срок до 20 дн."));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "13.Получение банковского кредита":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Любая страна.\n Цена 10% от сумы.\n Срок по ситуации."));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "14.Выписка из банка":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setChatId(update.getMessage().getChatId())
                                    .setText("Любая страна.\n Цена 100 зл.\n Срок  1 дн."));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "15.Страховка авто/человек":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage()
                                    .setText("Любая страна.\n Цена по ситуации зл.\n Срок  1 дн.")
                                    .setChatId(update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Цены на услуги":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("...",4, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        if (user.isAdmin_support())
                        {
                            try {
                                sendApiMethod(new SendMessage().setChatId(support_id).setText(update.getMessage().getText()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }

                        break;
                }
            }

       }
        if(update.hasCallbackQuery())
        {
            if(update.getCallbackQuery().getData()!=null)
            {
                switch (update.getCallbackQuery().getData())
                {
                    case "1.1.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_1);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                        break;
                    case "1.2.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_2);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "1.3.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_3);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.4.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_4);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.5.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_5);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.6.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_6);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.7.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.8.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_8);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.9.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_9);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.10.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_10);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.11.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_11);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.12.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_12);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.13.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_13);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.14.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_14);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.15.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_15);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.16.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_16);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.17.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_17);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.18.1":
                        user.setAdmin_support(true);
                        try {
                            sendApiMethod(new SendMessage().setText("оформить").setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_18);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText(user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                }


            }
        }
    }



    public void sendMessage (String text, String chat_id, String data1, String data2)
    {
        List<List<InlineKeyboardButton>> rowList= new ArrayList<>();

            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();

            keyboardButtonsRow1.add(new InlineKeyboardButton()
                    .setText("Оформить")
                    .setCallbackData(data1));

            rowList.add(keyboardButtonsRow1);
        try {
            sendApiMethod(new SendMessage().setText(text).setChatId(chat_id).setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(rowList)));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public SendMessage send_Message_With_Remake(String text, int type, String chat_id){
        ReplyKeyboardMarkup keyboard =new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);
        List<KeyboardRow> rows=new ArrayList<>();
        if (type==1)
        {
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            row1.add(new KeyboardButton("Список документов"));
            row2.add(new KeyboardButton("Цены на услуги"));
            rows.add(row1);
            rows.add(row2);
        }
        if(type==2)
        {
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            KeyboardRow row4=new KeyboardRow();
            KeyboardRow row5=new KeyboardRow();
            KeyboardRow row6=new KeyboardRow();
            KeyboardRow row7=new KeyboardRow();
            KeyboardRow row8=new KeyboardRow();
            KeyboardRow row9=new KeyboardRow();
            KeyboardRow row10=new KeyboardRow();
            KeyboardRow row11=new KeyboardRow();
            row1.add(new KeyboardButton("1.Приглашения"));
            row2.add(new KeyboardButton("2.Карта побыту"));
            row3.add(new KeyboardButton("3.Мельдунок + песель"));
            row4.add(new KeyboardButton("4.Умовы найму"));
            row5.add(new KeyboardButton("5.Wstępne (powyżej 3 m)"));
            row6.add(new KeyboardButton("6.Сан-эпид с (orzeczeniem) и анализами"));
            row7.add(new KeyboardButton("7.Психотесты для водителей"));
            row8.add(new KeyboardButton("8.Orzeczenie для водителей"));
            row9.add(new KeyboardButton("9.Код 95 (литовский) с переводом на\n польский, психотестами и orzeczeniem"));
            row10.add(new KeyboardButton("Слудующие документы"));
            row11.add(new KeyboardButton("Вернуться назад в меню"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);
            rows.add(row5);
            rows.add(row6);
            rows.add(row7);
            rows.add(row8);
            rows.add(row9);
            rows.add(row10);
            rows.add(row11);
        }
        if(type==3)
        {
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            KeyboardRow row4=new KeyboardRow();
            KeyboardRow row5=new KeyboardRow();
            KeyboardRow row6=new KeyboardRow();
            KeyboardRow row7=new KeyboardRow();
            KeyboardRow row8=new KeyboardRow();
            KeyboardRow row9=new KeyboardRow();
            KeyboardRow row10=new KeyboardRow();
            row1.add(new KeyboardButton("10.Получение банковского кредита"));
            row2.add(new KeyboardButton("11.Выписка из банка"));
            row3.add(new KeyboardButton("12.Страховка авто/человек"));
            row4.add(new KeyboardButton("13.Анализы на сальмонеллу (analiza na nosicielstwo)"));
            row5.add(new KeyboardButton("14.Получение номеров PESEL и NIP"));
            row6.add(new KeyboardButton("15.Водительское удостоверение Украины любой категории с записью в базе ГАИ"));
            row7.add(new KeyboardButton("16.Заполнение налоговых деклараций PIT"));
            row8.add(new KeyboardButton("17.Справка о несудимости"));
            row9.add(new KeyboardButton("18.БХП"));
            row10.add(new KeyboardButton("Вернуться назад в меню"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);
            rows.add(row5);
            rows.add(row6);
            rows.add(row7);
            rows.add(row8);
            rows.add(row9);
            rows.add(row10);
        }
        if (type==4)

        {
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            KeyboardRow row4=new KeyboardRow();
            KeyboardRow row5=new KeyboardRow();
            KeyboardRow row6=new KeyboardRow();
            KeyboardRow row7=new KeyboardRow();
            KeyboardRow row8=new KeyboardRow();
            KeyboardRow row9=new KeyboardRow();
            KeyboardRow row10=new KeyboardRow();
            KeyboardRow row11=new KeyboardRow();
            row1.add(new KeyboardButton("1.Приглашение воеводское"));
            row2.add(new KeyboardButton("2.Полугодовое"));
            row3.add(new KeyboardButton("3.Комплект Документов на карту побыту (залончник, умова, КРС)"));
            row4.add(new KeyboardButton("4.Карта побыту рабочая (оплата внеска входит)"));
            row5.add(new KeyboardButton("5.Карта побыту рабочая (оплата внеска не входит)"));
            row6.add(new KeyboardButton("6.Мельдунок (1 мес) + ПЕСЕЛЬ"));
            row7.add(new KeyboardButton("7.Умовы найму"));
            row8.add(new KeyboardButton("8.Wstępne (powyżej 3 m)"));
            row9.add(new KeyboardButton("9.Сан-эпид с (orzeczeniem) и анализами"));
            row10.add(new KeyboardButton("Слудующие усуги"));
            row11.add(new KeyboardButton("Вернуться назад в меню"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);
            rows.add(row5);
            rows.add(row6);
            rows.add(row7);
            rows.add(row8);
            rows.add(row9);
            rows.add(row10);
            rows.add(row11);
        }
        if (type==5)
        {
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            KeyboardRow row4=new KeyboardRow();
            row1.add(new KeyboardButton("1.Украина, Росиия, Белларусь"));
            row2.add(new KeyboardButton("2.Грузия и все зак. на -АН (страны СНГ бывшего)"));
            row3.add(new KeyboardButton("3.Любые другие страны"));
            row4.add(new KeyboardButton("Вернуться назад в меню"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);

        }
        if(type==6)
        {
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            KeyboardRow row4=new KeyboardRow();
            KeyboardRow row5=new KeyboardRow();
            KeyboardRow row6=new KeyboardRow();
            KeyboardRow row7=new KeyboardRow();
            row1.add(new KeyboardButton("10.Психотесты для водителей"));
            row2.add(new KeyboardButton("11.Orzeczenie для водителей"));
            row3.add(new KeyboardButton("12.Код 95 (литовский) с переводом на польский, психотестами и orzeczeniem"));
            row4.add(new KeyboardButton("13.Получение банковского кредита"));
            row5.add(new KeyboardButton("14.Выписка из банка"));
            row6.add(new KeyboardButton("15.Страховка авто/человек"));
            row7.add(new KeyboardButton("Вернуться назад в меню"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);
            rows.add(row5);
            rows.add(row6);
            rows.add(row7);
        }
        if (type==7) {
            KeyboardRow row1 = new KeyboardRow();
            KeyboardRow row2 = new KeyboardRow();
            KeyboardRow row3 = new KeyboardRow();
            row1.add(new KeyboardButton("1.Короткий"));
            row2.add(new KeyboardButton("2.Длинный"));
            row3.add(new KeyboardButton("2.Длинный"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
        }
        keyboard.setKeyboard(rows);
        return new SendMessage().setChatId(chat_id).setText(text).setReplyMarkup(keyboard);
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
