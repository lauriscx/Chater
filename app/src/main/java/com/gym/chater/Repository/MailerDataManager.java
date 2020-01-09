package com.gym.chater.Repository;

import com.google.firebase.database.DatabaseReference;
import com.gym.chater.Data.Message;

public class MailerDataManager {
    static private String data_base_table = "chats";

    public MailerDataManager(){
        RealTimeDataBase.addDataBaseTable(data_base_table);
    }
    public DatabaseReference getReferenceDataBase(String chatID){
        return RealTimeDataBase.getDataBaseTable(data_base_table, chatID);
    }
    public void addUsersChat(String chatID, String messageID, Message message){
        getReferenceDataBase(chatID).child(messageID).setValue(message);
    }
}