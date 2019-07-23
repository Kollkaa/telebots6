package org.telegramBot.zakaz1;

public enum TypeDoc {

    type_1_1_1("Приглашение воеводское"),
    type_1_1_2("Приглашение полугодовое"),
    type_1_1_3("Комплект Документов на карту побыту (залончник, умова, КРС)"),
    type_1_1_5("Карта побыту с внеском"),
    type_1_1_4("Карта побыту без внеска"),
    type_1_1_6("Мельдунок + песель"),
    type_1_1_7("Умовы найму"),
    type_1_1_8("Wstępne (powyżej 3 m)"),
    type_1_1_9("Сан-эпид с (orzeczeniem) и анализами"),
    type_1_1_10("Психотесты для водителей"),
    type_1_1_11("Orzeczenie для водителей"),
    type_1_9_12("Код 95 (литовский) с переводом на польский, психотестами и orzeczeniem(короткий)"),
    type_1_9_12_1("Код 95 (литовский) с переводом на польский, психотестами и orzeczeniem(длинный)"),
    type_1__1_13("Получение банковского кредита"),
    type_1_1_14("Выписка из банка"),
    type_1_1_15("Страховка авто/человек"),
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
