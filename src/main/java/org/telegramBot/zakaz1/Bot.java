package org.telegramBot.zakaz1;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.InputFile;
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
//    public String uploadFile(String file_name, String file_id, String chat_id) throws IOException {
//        GetFile getFile = new GetFile();
//        getFile.setFileId(file_id);
//
//        org.telegram.telegrambots.meta.api.objects.File file = null;
//        try {
//            file = execute(getFile);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        InputStream fileUrl = null;
//        try {
//            fileUrl = new URL(file.getFileUrl(getBotToken())).openStream();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        File localFile = new File("src/main/resources/"+chat_id+"/"+file_name);
//        String path="src/main/resources/"+chat_id+"/"+file_name;
//        try {
//            FileUtils.copyInputStreamToFile(fileUrl, localFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Uploaded!");
//        return path;
//    }
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
                users.put(update.getMessage().getChatId().toString(), new User(update.getMessage().getChatId().toString(),false));
                user = users.get(update.getMessage().getChatId().toString());
            } else {
                user = users.get(update.getMessage().getChatId().toString());
            }
            if (update.getMessage().hasDocument()) {
                Document doc =update.getMessage().getDocument();
                System.out.println(doc.toString());

//                System.out.println("Document");
//                if (user.isAdmin_support()) {
//                    try {
//                        users.get(update.getMessage().getChatId()).AddDocument(uploadFile(update.getMessage().getDocument().getFileName()
//                                , update.getMessage().getDocument().getFileId()
//                                , update.getMessage().getChatId().toString()));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                try {
//                    sendApiMethod(send_Message_With_Remake("Это ещё не всё?.если ты закончил нажмите на [Отправить]"
//                            , 8, user.getChat_id()));
//
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }

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
                        sendApiMethod(send_Message_With_Remake("Доступные документы для просмотра информации:"+"\n"+" 1.Приглашение воеводское" + "\n" + "2.Полугодовое приглашение" + "\n" + "3.Комплект документов на карту побыту" + "\n" + "4.Карта побыту рабочая(с внеском)" + "\n" + "5.Карта побыту рабочая(без внеска)" + "\n" + "6.Мельдунок" + "\n" + "7.Умовы найму" + "\n" + "8.Wstepne" + "\n" + "9.Cан-эпид" + "\n" + "10.Психотесты для водителей" + "\n" + "11.Orzeczenie для водителей" + "\n" + "12.Код 95" + "\n" + "13.Получение банковского кредита" + "\n" + "14.Выписка из банка" + "\n" + "1.Страховка авто/человек" + "\n" + "Команды для управления ботом:" + "\n" + "/show - статистика посетителей "
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
                        user.setAdmin(true);
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
                            execute(new SendPhoto().setPhoto(new File("/src/main/java/org/telegramBot/zakaz1/photos/photo_2019-07-23_15-43-03.jpg")).setChatId(update.getMessage().getChatId()));


                            sendApiMethod(send_Message_With_Remake("\uD83D\uDC68\uD83C\uDFFC\u200D\uD83D\uDCBCGarant DOC - любие документы в Варшаве и Польше !\n" +
                                            "Гарантия низкой цены, быстрого результата и высокого качества выполненной работы. \n" +
                                            "\n" +
                                            "У НАС \uD83D\uDC4D\uD83C\uDFFC:\n" +
                                            "\uD83D\uDD18 только легальные документы !\n" +
                                            "\uD83D\uDD18 90% позитивный результатов ! \n" +
                                            "\uD83D\uDD18 всегда делаем в сроки ! \n" +
                                            "\uD83D\uDD18 бесплатная консультация !"
                                    , 1, update.getMessage().getChatId().toString()));

                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Помощь❗️":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage().setChatId(user.getChat_id()).setText(" ⁃ Как пользоваться ботом ?\n" +
                                    "Здравствуйте, все очень просто !\n" +
                                    " 1. выберите и нажмите на пункт Меню\n" +
                                    " 2. нажмите на документ который вам надо \n" +
                                    " 3. ознакомтесь и нажмите кнопку ЗАКАЗАТЬ\n" +
                                    "4. отправьте нужные данные для оформления документа\n" +
                                    "5.После прохождения этой процедуры, с вами свяжеться наш администратор"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Меню\uD83D\uDCC4":
                        user.setAdmin_support(false);

                        try {
                            sendApiMethod(send_Message_With_Remake("Доступные документы для просмотра информации:"+"\n"+"1.Приглашение воеводское" + "\n" + "2.Полугодовое приглашение" + "\n" + "3.Комплект документов на карту побыту" + "\n" + "4.Карта побыту рабочая(с внеском)" + "\n" + "5.Карта побыту рабочая(без внеска)" + "\n" + "6.Мельдунок" + "\n" + "7.Умовы найму" + "\n" + "8.Wstepne" + "\n" + "9.Cан-эпид" + "\n" + "10.Психотесты для водителей" + "\n" + "11.Orzeczenie для водителей" + "\n" + "12.Код 95" + "\n" + "13.Получение банковского кредита" + "\n" + "14.Выписка из банка" + "\n" + "15.Страховка авто/человек" + "\n" +
                                    "Выберете необходимый пункт ⬇️ ", 666, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "Вернуться  в главное меню↩️":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете необходимый пункт ⬇️ ", 1, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Назад↩️":
                        user.setAdmin_support(false);

                        try {
                            sendApiMethod(send_Message_With_Remake("Доступные документы для просмотра информации:"+"\n"+"1.Приглашение воеводское" + "\n" + "2.Полугодовое приглашение" + "\n" + "3.Комплект документов на карту побыту" + "\n" + "4.Карта побыту рабочая(с внеском)" + "\n" + "5.Карта побыту рабочая(без внеска)" + "\n" + "6.Мельдунок" + "\n" + "7.Умовы найму" + "\n" + "8.Wstepne" + "\n" + "9.Cан-эпид" + "\n" + "10.Психотесты для водителей" + "\n" + "11.Orzeczenie для водителей" + "\n" + "12.Код 95" + "\n" + "13.Получение банковского кредита" + "\n" + "14.Выписка из банка" + "\n" + "15.Страховка авто/человек" + "\n" +
                                    "Выберете необходимый пункт ⬇️ ", 666, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;



                    case "1.Украина, Росиия, Белларусь":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("\uD83D\uDCB5Цена: 1000 zł (Украина, Росиия, Белларусь )", 33, update.getMessage().getChatId().toString()));
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

                    case "1.Короткий":
                        user.setType_doc(TypeDoc.type_1_9_12);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Любая страна.\n Цена 1300 зл.\n Срок до 10 дн.", 777, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "2.Длинный":
                        user.setType_doc(TypeDoc.type_1_9_12_1);
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Любая страна.\n Цена 2800 зл.\n Срок до 20 дн.", 777, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1":
                        user.setType_doc(TypeDoc.type_1_1_1);

                        try {
                            execute(new SendPhoto().setPhoto(new File("/src/main/java/org/telegramBot/zakaz1/photos/photo_2019-07-23_14-14-20.jpg")).setChatId(update.getMessage().getChatId()));
                            sendApiMethod(send_Message_With_Remake("Приглашение воеводское и полугодовое\n" +
                                    "\n" +
                                    "\uD83D\uDCDDПриглашение воеводское на год\n" +
                                    "⏳Срок изготовления 30-45 дней",5,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("Cписок необходимых документов: \n" +
                                    "- все заполненные страницы паспорта "));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "2":
                        user.setType_doc(TypeDoc.type_1_1_2);
                        try {
                            execute(new SendPhoto().setPhoto(new File("/src/main/java/org/telegramBot/zakaz1/photos/photo_2019-07-23_14-14-20.jpg")).setChatId(update.getMessage().getChatId()));
                            sendApiMethod(send_Message_With_Remake("\uD83D\uDCDDПриглашение полугодовые\n" +
                                    "⏳Срок изготовления 8-10 дней\n" +
                                    "\uD83D\uDCB5Цена: 350 zł (Украина, Росиия, Белларусь )",33,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("Cписок необходимых документов: \n" +
                                    "- все заполненные страницы паспорта "));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "3":
                        user.setType_doc(TypeDoc.type_1_1_3);
                        try {
                            execute(new SendPhoto().setPhoto(new File("/src/main/java/org/telegramBot/zakaz1/photos/photo_2019-07-23_15-07-52.jpg")).setChatId(update.getMessage().getChatId()));

                            sendApiMethod(send_Message_With_Remake("Комплект Документов на карту побыту\n" +
                                    " \uD83D\uDDC2(залончник, умова, КРС)\n" +
                                    "\n" +
                                    "⏳Время ожидания 1 час\n" +
                                    "\uD83D\uDCB5Цена: 500 zl ",33,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("Cписок необходимых документов: По указанию менеджера \n"
                                   ));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "4":
                        user.setType_doc(TypeDoc.type_1_1_4);
                        try {
                            execute(new SendPhoto().setPhoto(new File("/src/main/java/org/telegramBot/zakaz1/photos/photo_2019-07-23_15-14-02.jpg")).setChatId(update.getMessage().getChatId()));
                            sendApiMethod(send_Message_With_Remake("Карта побыту рабочая / студенческая\n" +
                                    "любая страна + полный пакет документов (оплата внеска входит)\n" +
                                    "\n" +
                                    "⏳Время ожидания 30-45 дней\n" +
                                    "\uD83D\uDCB5Цена: 2800 zl  ",33,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("Cписок необходимых документов: По индивидуальной записи\n"
                            ));

                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "5":
                        user.setType_doc(TypeDoc.type_1_1_5);

                        try {
                            execute(new SendPhoto().setPhoto(new File("/src/main/java/org/telegramBot/zakaz1/photos/photo_2019-07-23_15-14-02.jpg")).setChatId(update.getMessage().getChatId()));
                            sendApiMethod(send_Message_With_Remake("Карта побыту рабочая / студенческая\n" +
                                    "любая страна + полный пакет документов (оплата внеска входит)\n" +
                                    "\n" +
                                    "⏳Время ожидания 30-45 дней\n" +
                                    "\uD83D\uDCB5Цена: 1900 zl  ",33,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("  первая страница паспорта \n" +
                                    "-  либо виза, либо карта"
                            ));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "6":
                        user.setType_doc(TypeDoc.type_1_1_6);
                        try {
                            execute(new SendPhoto().setPhoto(new File("/src/main/java/org/telegramBot/zakaz1/photos/photo_2019-07-23_15-18-24.jpg")).setChatId(update.getMessage().getChatId()));
                            sendApiMethod(send_Message_With_Remake("Мельдунок (1 мес) + ПЕСЕЛЬ\n" +
                                    "\n" +
                                    "⏳Время ожидания 1 час\n" +
                                    "\uD83D\uDCB5Цена: 200 zl  \n" +
                                    "каждый след. месяц + 100 zl" , 33,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("  первая страница паспорта \n" +
                                    "-  либо виза, либо карта"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "7":
                        user.setType_doc(TypeDoc.type_1_1_7);
                        try {
                            execute(new SendPhoto().setPhoto(new File("/src/main/java/org/telegramBot/zakaz1/photos/photo_2019-07-23_15-20-38.jpg")).setChatId(update.getMessage().getChatId()));
                            sendApiMethod(send_Message_With_Remake("Умовы найму\n" +
                                    "\n" +
                                    "⏳Время ожидания 1 день\n" +
                                    "\uD83D\uDCB5Цена: 250 zl  " , 33,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("Cписок необходимых документов: \n" +
                                    "- фото первой страницы паспорта \n" +
                                    "- с какого числа"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "8":
                        user.setType_doc(TypeDoc.type_1_1_8);

                        try {
                            execute(new SendPhoto().setPhoto(new File("/src/main/java/org/telegramBot/zakaz1/photos/photo_2019-07-23_15-24-45.jpg")).setChatId(update.getMessage().getChatId()));
                            sendApiMethod(send_Message_With_Remake("Wstępne (powyżej 3 m) \n" +
                                    "\n" +
                                    "⏳Время ожидания 1 день\n" +
                                    "\uD83D\uDCB5Цена: 100 zl   " , 33,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("Cписок необходимых документов: \n" +
                                    "- фото паспорта \n" +
                                    "- место жительства в Варшаве\n" +
                                    "При необходиомости: \n" +
                                    "- название фирмы \n" +
                                    "- адрес фирмы -\n" +
                                    "- должность\n" +
                                    "- с какого числа"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "9":
                        user.setType_doc(TypeDoc.type_1_1_9);
                        try {
                            execute(new SendPhoto().setPhoto(new File("/src/main/java/org/telegramBot/zakaz1/photos/photo_2019-07-23_15-27-38.jpg")).setChatId(update.getMessage().getChatId()));
                            sendApiMethod(send_Message_With_Remake("медкнижка SANEPID \n" +
                                    "+ к ней orzeczenie lekarskie \n" +
                                    "+ анализы \n" +
                                    "\n" +
                                    "⏳Время ожидания 1 день\n" +
                                    "\uD83D\uDCB5Цена: 120 zl " , 33,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("Cписок необходимых документов: \n" +
                                    "- фото паспорта \n" +
                                    "- место жительства в Варшаве\n" +
                                    "При необходиомости: \n" +
                                    "- название фирмы \n" +
                                    "- адрес фирмы -\n" +
                                    "- должность\n" +
                                    "- с какого числа"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "10":
                        user.setType_doc(TypeDoc.type_1_1_10);
                        try {
                            execute(new SendPhoto().setPhoto(new File("/src/main/java/org/telegramBot/zakaz1/photos/photo_2019-07-23_15-29-35.jpg")).setChatId(update.getMessage().getChatId()));
                            sendApiMethod(send_Message_With_Remake("Психотесты для водителей\n" +
                                    "\n" +
                                    "⏳Время ожидания 1 день\n" +
                                    "\uD83D\uDCB5Цена: 120 zl  " , 33,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("Cписок необходимых документов: \n" +
                                    "- фото паспорта \n" +
                                    "- место жительства в Варшаве."));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "11":
                        user.setType_doc(TypeDoc.type_1_1_11);
                        try {
                            execute(new SendPhoto().setPhoto(new File("/src/main/java/org/telegramBot/zakaz1/photos/photo_2019-07-23_15-32-58.jpg")).setChatId(update.getMessage().getChatId()));
                            sendApiMethod(send_Message_With_Remake("Orzeczenie для водителей\n" +
                                    "\n" +
                                    "⏳Время ожидания 1 день\n" +
                                    "\uD83D\uDCB5Цена: 120 zl  " , 33,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("Cписок необходимых документов: \n" +
                                    "- фото паспорта \n" +
                                    "- место жительства в Варшаве."));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "12":
                        try {
                            sendApiMethod(send_Message_With_Remake("Код95\n" +
                                    "\n" +
                                    "⏳Время ожидания до 10 дней\n" +
                                    "\uD83D\uDCB5Цена: 1300 zl  " , 7,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("Cписок необходимых документов:\n" +
                                    "- фото паспорта\n" +
                                    "- место жительства в Варшаве\n" +
                                    "- фото прав(две стороны)"));

                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "13":
                        user.setType_doc(TypeDoc.type_1__1_13);
                        try {
                            execute(new SendPhoto().setPhoto(new File("/src/main/java/org/telegramBot/zakaz1/photos/photo_2019-07-23_15-36-14.jpg")).setChatId(update.getMessage().getChatId()));
                            sendApiMethod(send_Message_With_Remake("Помощь в получение банковского кредита\n" +
                                    "\n" +
                                    "⏳Время ожидания (по ситуации)\n" +
                                    "\uD83D\uDCB5Цена: индивидуально " , 33,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("Список необходимых документов:\n" +
                                    "-  номер паспорта"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "14":
                        user.setType_doc(TypeDoc.type_1_1_14);
                        try {
                            execute(new SendPhoto().setPhoto(new File("/src/main/java/org/telegramBot/zakaz1/photos/photo_2019-07-23_15-39-10.jpg")).setChatId(update.getMessage().getChatId()));
                            sendApiMethod(send_Message_With_Remake("Выписка из банка\n" +
                                    "\n" +
                                    "⏳Время ожидания 1 день\n" +
                                    "\uD83D\uDCB5Цена: 150 zl  " , 33,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("Cписок необходимых документов: по указанию менеджера. "));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "15":
                        user.setType_doc(TypeDoc.type_1_1_15);
                        try {

                            sendApiMethod(send_Message_With_Remake("Страховка авто/человек"+"\n" +
                                    "\n" +
                                    "⏳Время ожидания 1 день\n" +
                                    "\uD83D\uDCB5Цена: ндивидуально  " , 33,update.getMessage().getChatId().toString()));
                            execute(new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText("Cписок необходимых документов:  \n" +
                                    "- фото паспорта \n" +
                                    "- место жительства В Варшаве \n" +
                                    "- дата с какого по какое \n" +
                                    "- фото техпаспорта (если машина)"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "К выбору докуметов" :

                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Доступные документы для просмотра информации:"+"\n"+"1.Приглашение воеводское" + "\n" + "2.Полугодовое приглашение" + "\n" + "3.Комплект документов на карту побыту" + "\n" + "4.Карта побыту рабочая(с внеском)" + "\n" + "5.Карта побыту рабочая(без внеска)" + "\n" + "6.Мельдунок" + "\n" + "7.Умовы найму" + "\n" + "8.Wstepne" + "\n" + "9.Cан-эпид" + "\n" + "10.Психотесты для водителей" + "\n" + "11.Orzeczenie для водителей" + "\n" + "12.Код 95" + "\n" + "13.Получение банковского кредита" + "\n" + "14.Выписка из банка" + "\n" + "15.Страховка авто/человек" + "\n" +
                            "Выберете необходимый пункт ⬇️ ", 666, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "Сотрудничество\uD83D\uDC64":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете пункт для сотрудничиства\uD83D\uDD3D", 111, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Навигатор Польшы\uD83C\uDDF5\uD83C\uDDF1":
                        user.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете город\uD83D\uDD3D", 222, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Варшава":
                        try {
                            sendApiMethod(send_Message_With_Remake("⁃ Объявления https://t.me/warsaw_chats\n" +
                                    " ⁃ Работа https://t.me/Warsawwork\n" +
                                    " ⁃ Рынок  https://t.me/warsaw_shop\n" +
                                    " ⁃ Жилье https://t.me/warsaw_1\n" +
                                    " - Знакомства и вечеринки https://t.me/warsawchat\n" +
                                    " ⁃ Каталог услуг | Варшава https://t.me/warsaw_poland\n" +
                                    "\n" +
                                    "Варшава объявления \uD83D\uDECE | Польша \uD83C\uDDF5\uD83C\uDDF1\n" +
                                    "\uD83C\uDFF0Группа для объявлений |Варшава\uD83C\uDDF5\uD83C\uDDF1\n" +
                                    "Здесь каждый может опубликовать товар или услугу.",333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();

                        }
                        break;
                    case "Вроцлав":
                        try {
                            sendApiMethod(send_Message_With_Remake("https://t.me/Wroclaw_poland",333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();

                        }
                        break;
                    case "Краков":
                        try {
                            sendApiMethod(send_Message_With_Remake("https://t.me/Krakow_poland",333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();

                        }
                        break;
                    case "Познань":
                        try {
                            sendApiMethod(send_Message_With_Remake("https://t.me/Poznan_poland",333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();

                        }
                        break;
                    case "Документы":
                        try {
                            sendApiMethod(send_Message_With_Remake("Продавец-консультант всего один \uD83D\uDC68\uD83C\uDFFC\u200D\uD83D\uDCBC\n" +
                                    "\uD83D\uDCF2Контакт: @garant_doc",333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();

                        }
                        break;

                    case "Реклама":
                        try {
                            sendApiMethod(send_Message_With_Remake("Контакт для сотрудничества: @frikok"+"\n"+"https://teletype.in/@lassis/HkDmvNhCN",333,update.getMessage().getChatId().toString()));
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
            KeyboardRow row4=new KeyboardRow();


            row1.add(new KeyboardButton("Меню\uD83D\uDCC4"));
            row2.add(new KeyboardButton("Помощь❗️"));
            row3.add(new KeyboardButton("Навигатор Польшы\uD83C\uDDF5\uD83C\uDDF1"));
            row4.add(new KeyboardButton("Сотрудничество\uD83D\uDC64"));

            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);
            //
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
            row6.add(new KeyboardButton("Вернуться  в главное меню↩️"));




            rows.add(row1);
            rows.add(row2);

            rows.add(row3);
            rows.add(row4);

            rows.add(row5);
            rows.add(row6);

        }
        if(type==0000)
        {   KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            row1.add(new KeyboardButton("Заказать"));
            row3.add(new KeyboardButton("К выбору докуметов"));
            row2.add(new KeyboardButton("Вернуться  в главное меню↩️"));


            rows.add(row1);
            rows.add(row2);

        }
        if (type == 111){
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            row1.add(new KeyboardButton("Реклама"));
            row2.add(new KeyboardButton("Документы"));
            row3.add(new KeyboardButton("Вернуться  в главное меню↩️"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);

        }
        if(type == 222){
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            KeyboardRow row4=new KeyboardRow();
            KeyboardRow row5=new KeyboardRow();
            row1.add(new KeyboardButton("Варшава"));
            row2.add(new KeyboardButton("Вроцлав"));
            row3.add(new KeyboardButton("Познань"));
            row4.add(new KeyboardButton("Краков"));
            row5.add(new KeyboardButton("Вернуться  в главное меню↩️"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);
            rows.add(row5);


        }



        keyboard.setKeyboard(rows);

        return new SendMessage().setChatId(chat_id).setText(text).setReplyMarkup(keyboard);
    }



    @Override
    public String getBotUsername() {
        return "@warsaww_bot";
    }

    @Override
    public String getBotToken() {
        return "827804459:AAEhCYbx6DhbZDsoUroynFmqf2f57yDqzaw";
    }
}
