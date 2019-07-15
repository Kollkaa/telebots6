package org.telegramBot.zakaz1;

public enum TypeDoc {
    type_1_1("Приглашения"),
    type_1_2("Карта побыту"),
    type_1_3("Мельдунок + песель"),
    type_1_4("Умовы найму"),
    type_1_5("Wstępne (powyżej 3 m)"),
    type_1_6("Сан-эпид с (orzeczeniem) и анализами"),
    type_1_7("Психотесты для водителей"),
    type_1_8("Orzeczenie для водителей"),
    type_1_9("Код 95 (литовский) с переводом на польский, психотестами и orzeczeniem"),
    type_1_10("Получение банковского кредита"),
    type_1_11("Выписка из банка"),
    type_1_12("Страховка авто/человек"),
    type_1_13("Анализы на сальмонеллу (analiza na nosicielstwo)"),
    type_1_14("Получение номеров PESEL и NIP"),
    type_1_15("Водительское удостоверение Украины любой категории с записью в базе ГАИ"),
    type_1_16("Заполнение налоговых деклараций PIT"),
    type_1_17("Справка о несудимости"),
    type_1_18("БХП");

    private String title;

    TypeDoc(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }




}
