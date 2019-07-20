package org.telegramBot.zakaz1;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.*;

public class Bot extends TelegramLongPollingBot {
   boolean shutdown=false;
   boolean shut=false;
   boolean flag=false;
    Map<String,User> users=new HashMap<>();
    List<String>listNickname=new ArrayList<>();
    User user;
    String support_id="516538254";//"314254027";
    int count=0;
    public String uploadFile(String file_name, String file_id, String chat_id) throws IOException {
        GetFile getFile = new GetFile();
        getFile.setFileId(file_id);

        org.telegram.telegrambots.meta.api.objects.File file = null;
        try {
            file = execute(getFile);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        InputStream fileUrl = null;
        try {
            fileUrl = new URL(file.getFileUrl(getBotToken())).openStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File localFile = new File("src/main/resources/"+chat_id+"/"+file_name);
        String path="src/main/resources/"+chat_id+"/"+file_name;
        try {
            FileUtils.copyInputStreamToFile(fileUrl, localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Uploaded!");
        return path;
    }
    @Override
    public void onUpdateReceived(Update update) {
        flag=false;
//        if (update.hasMessage()){
//            if (shutdown==true){
//                System.out.println("Close");
//            }
//        }
        if(update.getMessage().hasText() && update.getMessage().getText().equals("/on")){
            shutdown=false;
        }

        if(shutdown!=true){

        if (update.hasMessage()) {
            System.out.println(update.getMessage().getChatId().toString());
            if (!users.containsKey(update.getMessage().getChatId().toString())) {
                users.put(update.getMessage().getChatId().toString(), new User(update.getMessage().getChatId().toString()));
                user = users.get(update.getMessage().getChatId().toString());
            } else {
                user = users.get(update.getMessage().getChatId().toString());
            }
            if (update.getMessage().hasDocument()) {
                System.out.println("Document");
                if (user.isAdmin_support()) {
                    try {
                        users.get(update.getMessage().getChatId()).AddDocument(uploadFile(update.getMessage().getDocument().getFileName()
                                , update.getMessage().getDocument().getFileId()
                                , update.getMessage().getChatId().toString()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    sendApiMethod(send_Message_With_Remake("Это ещё не всё?.если ты закончил нажмите на [Отправить]"
                            , 8, user.getChat_id()));

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (update.getMessage().getText() != null) {
                String pass=update.getMessage().getText();
                if (("/admin"+pass).equals("/adminandrew")) {
                    try {
                        sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Привет, Андрей, это режим администратора..Для запуска пользовательського режима нажми /start"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    try {
                        sendApiMethod(send_Message_With_Remake("Доступные документы для просмотра информации: 1.Приглашение воеводское" + "\n" + "2.Полугодовое приглашение" + "\n" + "3.Комплект документов на карту побыту" + "\n" + "4.Карта побыту рабочая(с внеском)" + "\n" + "5.Карта побыту рабочая(без внеска)" + "\n" + "6.Мельдунок" + "\n" + "7.Умовы найму" + "\n" + "8.Wstepne" + "\n" + "9.Cан-эпид" + "\n" + "10.Психотесты для водителей" + "\n" + "11.Orzeczenie для водителей" + "\n" + "12.Код 95" + "\n" + "13.Получение банковского кредита" + "\n" + "14.Выписка из банка" + "\n" + "1.Страховка авто/человек" + "\n" + "Команды для управления ботом:" + "\n" + "/show - статистика посетителей "
                                + "\n" + "/on - включить бота" + "\n" + "/off - выключить бота", 666, update.getMessage().getChatId().toString()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                else if (flag==true && ("/admin"+pass)!=("/adminandrew")){
                    try {
                        sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Неправильный ввод"));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                }

                switch (update.getMessage().getText())
                ///dd
                {
//                    case "/stat":
//                        try {
//                            sendApiMethod(new SendMessage().setText("Общее колbчество заказов:"+ count_user).setChatId(user.getChat_id()));
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                        break;
                    case "/on":
                        shutdown=false;
                        break;
                    case "/off" :
                        shutdown=true;
                        break;
                    case "Заказать":

                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n" + "В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:" + "@" + update.getMessage().getChat().getUserName() + "\n" + user.getType_doc() + " from " + update.getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "/admin":
                        flag=true;


                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Введите пароль:"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }


//                        else {
//                            try {
//                                sendApiMethod(new SendMessage().setText("Некоректный ввод...").setChatId(update.getMessage().getChatId().toString()));
//                            } catch (TelegramApiException e) {
//                                e.printStackTrace();
//                            }
//                        }
                        break;
                    case "/show":
                        String res = "";
                        for (String s : listNickname) {
                            res += "\n" + "@" + s;
                            count += 1;

                        }
                        try {
                            sendApiMethod(new SendMessage().setText("Список пользователей, которые посящали бота: " + res + "\n" + "Общее количество посещений:" + listNickname.size()).setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "Отправить":
                        for (String path : user.getDocument_path()) {
                            try {
                                execute(new SendDocument().setChatId(support_id).setDocument(new File(path)));
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "Назад\uD83D\uDD19":
                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете вашу страну"
                                    , 5, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    case "Назад <--":
                        try {
                            sendApiMethod(send_Message_With_Remake("..."
                                    , 6, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }


                    case "/shut":
                        shutdown = true;
                        break;

                    case "/start":
                        flag=false;
                        listNickname.add(update.getMessage().getChat().getUserName());
                        count += 1;
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Здравстуйте, с помощью этого бота стало возможно оформлять документы в Польше\uD83C\uDDF5\uD83C\uDDF1\n" +
                                            "Для начала оформления, воспользуйтесь клавишами на клавиатуре\uD83D\uDD3D\n"
                                    , 1, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Инструкция по использованию\uD83D\uDCD6":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage().setChatId(user.getChat_id()).setText("Здравствуйте, пользование этим ботом очень простое..\n" +
                                    "1.Для начала работы, выберите пункт на клавиатуре который вас интересует.\n" +
                                    "2.При нажатии на Список документов, вы получите возможность оформить доступные документы.\n" +
                                    "3.Для этого вам нужно выбрать документ и нажать кнопочку заказать.\n" +
                                    "4.После прохождения этой процедуры, с вами свяжеться наш админимтратор. Благодорим что вы снами)\n"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Список документов\uD83D\uDCC4":
                        user.setAdmin_support(false);

                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете необходимый пункт ⬇️ ", 4, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Слудующие документы➡️":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете необходимый пункт ⬇", 6, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Вернуться  в главное меню↩️":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете необходимый пункт ⬇", 1, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Назад↩️":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете необходимый пункт ⬇", 4, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "1.Приглашение воеводское":

                        user.setAdmin_support(false);
                        user.setType_doc(TypeDoc.type_1_1_1);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: все заполненные страницы паспорта, выберете страну ниже", 5, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "2.Полугодовое":
                        user.setType_doc(TypeDoc.type_1_1_2);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: все заполненные страницы паспорта" + "\n" + "Страны: Украина, Росия, Беларусь.\n Цена 180 зл.\n Срок до 10 дн.", 33, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "3.Комплект Документов на карту побыту (залончник, умова, КРС)":
                        user.setType_doc(TypeDoc.type_7_7_7);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: По указанию менеджера" + "\n" + "Цена 400 зл.\n Срок 1-2 дн.", 33, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "4.Карта побыту рабочая (оплата внеска входит)":
                        user.setType_doc(TypeDoc.type_1_2);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: По индивидуальной записи" + "\n" + " Любая страна,\n Цена: 2900 зл.\n Срок: 6-8 мес.", 33, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "5.Карта побыту рабочая (оплата внеска не входит)":
                        user.setType_doc(TypeDoc.type_1_2_2);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: По индивидуальной записи" + "\n" + " Любая страна,\n Цена: 1000 зл.\n Срок: 6-8 мес.", 33, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "6.Мельдунок (1 мес) + ПЕСЕЛЬ":
                        user.setType_doc(TypeDoc.type_1_3);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: первая страница паспорта и печать, либо виза, либо карта" + "\n" + "Любая страна.\n Цена: 150 зл.\n Срок: 1 час.\n Каждый след месяц: 75 зл", 33, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "7.Умовы найму":
                        user.setType_doc(TypeDoc.type_1_4);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: фото первой страницы паспорта и с какого числа" + "\n" + "Любая страна.\n Цена 200 зл.\n Срок в течении 1 дня", 33, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "8.Wstępne (powyżej 3 m)":
                        user.setType_doc(TypeDoc.type_1_5);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: фото паспорта, место жительства в Варшаве\n" +
                                    " (при необходиомости: название фирмы," +
                                    " адрес фирмы, должность, с какого числа)" + "\n" + "Любая страна.\n Цена 60 зл.\n Срок в течении 1 дня.", 33, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "9.Сан-эпид с (orzeczeniem) и анализами":
                        user.setType_doc(TypeDoc.type_1_6);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: фото паспорта, место жительства в Варшаве\n" +
                                    " (при необходиомости: название фирмы," +
                                    " адрес фирмы, должность, с какого числа)" + "\n" + "Любая страна.\n Цена 80 зл.\n Срок в течении 1 дня.", 33, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Следующие усуги➡️":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("...", 6, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "1.Украина, Росиия, Белларусь":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Цена 900 зл.\n Срок до 40дн", 33, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "2.Грузия и все зак. на -АН (страны СНГ бывшего)":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Цена 1100 зл.\n Срок до 40дн", 33, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "3.Любые другие страны":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Цена 1400 зл.\n Срок до 40дн", 33, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "10.Психотесты для водителей":
                        user.setType_doc(TypeDoc.type_1_7);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: фото паспорта, место жительства в Варшаве" + "\n" + "Любая страна.\n Цена 70 зл.\n Срок в течении 1 дня.", 777, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "11.Orzeczenie для водителей":
                        user.setType_doc(TypeDoc.type_1_8);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: фото паспорта, место жительства в Варшаве" + "\n" + "Любая страна.\n Цена 70 зл.\n Срок в течении 1 дня.", 777, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "12.Код 95 (литовский) с переводом на польский, психотестами и orzeczeniem":

                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: фото паспорта, место жительства в Варшаве, фото прав(две стороны)", 7, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "1.Короткий":
                        user.setType_doc(TypeDoc.type_1_9_1);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Любая страна.\n Цена 1300 зл.\n Срок до 10 дн.", 777, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "2.Длинный":
                        user.setType_doc(TypeDoc.type_1_9_2);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Любая страна.\n Цена 2800 зл.\n Срок до 20 дн.", 777, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "13.Получение банковского кредита":
                        user.setType_doc(TypeDoc.type_1_10);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: номер паспорта" + "\n" + "Любая страна.\n Цена 10% от сумы.\n Срок по ситуации.", 777, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "14.Выписка из банка":
                        user.setType_doc(TypeDoc.type_1_11);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: по указанию менеджера \n" + "Любая страна.\n Цена 100 зл.\n Срок  1 дн.", 777, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "15.Страховка авто/человек":
                        user.setType_doc(TypeDoc.type_1_12);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Cписок необходимых документов: фото паспорта, место жит. " +
                                    "В Варшаве (дата с какого по какое)/ фото техпаспорта" + "\n" + "Любая страна.\n Цена по ситуации зл.\n Срок  1 дн.", 777, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "1":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: все заполненные страницы паспорта, выберете страну ниже"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "2":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: все заполненные страницы паспорта, выберете страну ниже"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                        break;
                    case "3":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: По указанию менеджера"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "4":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: По индивидуальной записи"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "5":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: По индивидуальной записи"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "6":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: первая страница паспорта и печать, либо виза, либо карта"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "7":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: фото первой страницы паспорта и с какого числа"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "8":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: фото паспорта, место жительства в Варшаве\n" +
                                    " (при необходиомости: название фирмы," +
                                    " адрес фирмы, должность, с какого числа)"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "9":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: фото паспорта, место жительства в Варшаве\n" +
                                    " (при необходиомости: название фирмы," +
                                    " адрес фирмы, должность, с какого числа)"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "10":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: фото паспорта, место жительства в Варшаве"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "11":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: фото паспорта, место жительства в Варшаве"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "12":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: фото паспорта, место жительства в Варшаве, фото прав(две стороны)"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "13":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: номер паспорта"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "14":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: по указанию менеджера"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "15":
                        try {
                            sendApiMethod(new SendMessage().setChatId(update.getMessage().getChatId()).setText("Cписок необходимых документов: фото паспорта, место жит. " +
                                    "В Варшаве (дата с какого по какое)/ фото техпаспорта"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Cвязаться с менеджером":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Профиль администратора: @Frikok", 333, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        if (update.getMessage().isReply()) {
                            try {
                                sendApiMethod(new SendMessage().setChatId(update.getMessage().getReplyToMessage().getText().split("from")[1].trim()).setText(update.getMessage().getText()));
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        } else if (user.isAdmin_support()) {
                            try {
                                sendApiMethod(new SendMessage().setChatId(support_id).setText(update.getMessage().getText() + " from " + update.getMessage().getChatId()));
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }

                        break;
                }
            }
        }

       }

        if(update.hasCallbackQuery())
        {
            if (!users.containsKey(update.getCallbackQuery().getMessage().getChatId().toString())) {
                users.put(update.getCallbackQuery().getMessage().getChatId().toString(), new User(update.getCallbackQuery().getMessage().getChatId().toString()));
                user=users.get(update.getCallbackQuery().getMessage().getChatId().toString());
            }
            else
            {
                user=users.get(update.getCallbackQuery().getMessage().getChatId().toString());
            }
            if(update.getCallbackQuery().getData()!=null)
            {
                switch (update.getCallbackQuery().getData())
                {
                    case "1.1.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }

                        break;
                    case "1.2.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "1.3.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.4.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.5.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.6.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.7.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.8.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.9.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.10.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.11.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.12.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.13.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.14.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.15.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.16.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.17.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.18.1":
                        user.setAdmin_support(true);
                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n"+"В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(user.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        user.setType_doc(TypeDoc.type_1_7);
                        try {
                            sendApiMethod(new SendMessage().setChatId(support_id).setText("Запрос от пользователя:"+"@"+update.getCallbackQuery().getMessage().getChat().getUserName()+"\n"+user.getType_doc()+" from "+update.getCallbackQuery().getMessage().getChatId()));
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
                    .setText("Оформить✍️")
                    .setCallbackData(data1));

            rowList.add(keyboardButtonsRow1);
        try {
            sendApiMethod(new SendMessage().setText(text).setChatId(chat_id).setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(rowList)));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public SendMessage send_Message_With_Remake(String text, int type, String chat_id){
        System.out.println("in");
        ReplyKeyboardMarkup keyboard =new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);
        List<KeyboardRow> rows=new ArrayList<>();
        if (type==1)
        {
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();

            row1.add(new KeyboardButton("Список документов\uD83D\uDCC4"));
            row2.add(new KeyboardButton("Cвязаться с менеджером"));
            row3.add(new KeyboardButton("Инструкция по использованию\uD83D\uDCD6"));

            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            //
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
            row10.add(new KeyboardButton("Слудующие документы➡️"));
            row11.add(new KeyboardButton("Вернуться назад в меню↩️"));
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
            row10.add(new KeyboardButton("Вернуться назад в меню↩️"));
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
            row10.add(new KeyboardButton("Следующие усуги➡️"));
            row11.add(new KeyboardButton("Вернуться  в главное меню↩️"));
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
            System.out.println("in block");
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            KeyboardRow row4=new KeyboardRow();
            row1.add(new KeyboardButton("1.Украина, Росиия, Белларусь"));
            row2.add(new KeyboardButton("2.Грузия и все зак. на -АН (страны СНГ бывшего)"));
            row3.add(new KeyboardButton("3.Любые другие страны"));
            row4.add(new KeyboardButton("Назад↩️"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);
            System.out.println("Complete Reply");

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
            KeyboardRow row8=new KeyboardRow();
            row1.add(new KeyboardButton("10.Психотесты для водителей"));
            row2.add(new KeyboardButton("11.Orzeczenie для водителей"));
            row3.add(new KeyboardButton("12.Код 95 (литовский) с переводом на польский, психотестами и orzeczeniem"));
            row4.add(new KeyboardButton("13.Получение банковского кредита"));
            row5.add(new KeyboardButton("14.Выписка из банка"));
            row6.add(new KeyboardButton("15.Страховка авто/человек"));
            row7.add(new KeyboardButton("Назад↩️"));
            row8.add(new KeyboardButton("Вернуться  в главное меню↩️"));

            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);
            rows.add(row5);
            rows.add(row6);
            rows.add(row7);
            rows.add(row8);
        }
        if (type==7)
        {
            KeyboardRow row1 = new KeyboardRow();
            KeyboardRow row2 = new KeyboardRow();
            KeyboardRow row3 = new KeyboardRow();
            row1.add(new KeyboardButton("1.Короткий"));
            row2.add(new KeyboardButton("2.Длинный"));
            row3.add(new KeyboardButton("Назад↩️"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
        }
        if(type==8)
        {   KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            row1.add(new KeyboardButton("Список документов"));
            row2.add(new KeyboardButton("Цены на услуги"));
            row3.add(new KeyboardButton("Отправить"));

            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            }
        if(type==33)
        {   KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            row2.add(new KeyboardButton("Вернуться  в главное меню↩️"));
            row1.add(new KeyboardButton("Заказать"));
            row3.add(new KeyboardButton("Назад↩️"));

            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
        }
        if(type==333)
        {   KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            row1.add(new KeyboardButton("Вернуться  в главное меню↩️"));


            rows.add(row1);

        }



        //553
        if(type==777)
        {   KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            row1.add(new KeyboardButton("Заказать"));
            row3.add(new KeyboardButton("Назад <--"));
            row2.add(new KeyboardButton("Вернуться  в главное меню↩️"));


            rows.add(row1);
            rows.add(row2);

        }
        if(type==666)
        {   KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            KeyboardRow row4=new KeyboardRow();
            KeyboardRow row5=new KeyboardRow();
            KeyboardRow row6=new KeyboardRow();
            row1.add(new KeyboardButton("1"));
            row1.add(new KeyboardButton("2"));
            row1.add(new KeyboardButton("3"));
            row2.add(new KeyboardButton("4"));
            row2.add(new KeyboardButton("5"));
            row2.add(new KeyboardButton("6"));
            row3.add(new KeyboardButton("7"));
            row3.add(new KeyboardButton("8"));
            row3.add(new KeyboardButton("9"));
            row4.add(new KeyboardButton("10"));
            row4.add(new KeyboardButton("11"));
            row4.add(new KeyboardButton("12"));
            row5.add(new KeyboardButton("13"));
            row5.add(new KeyboardButton("14"));
            row5.add(new KeyboardButton("15"));
            row6.add(new KeyboardButton("/start"));




            rows.add(row1);
            rows.add(row2);

            rows.add(row3);
            rows.add(row4);

            rows.add(row5);
            rows.add(row6);

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
