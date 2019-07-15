package org.telegramBot.zakaz1;

public class User {
    private String chat_id;
    private String  type_doc;
    private boolean admin_support;

    public User(String chat_id) {
        this.chat_id = chat_id;
    }

    public String  getType_doc() {
        return type_doc;
    }
    public void  setType_doc(TypeDoc type_doc) {
        this.type_doc = type_doc.getTitle();
    }

    public boolean isAdmin_support() {
        return admin_support;
    }

    public void setAdmin_support(boolean admin_support) {
        this.admin_support = admin_support;
    }
}
