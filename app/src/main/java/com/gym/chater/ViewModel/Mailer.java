package com.gym.chater.ViewModel;

import com.google.firebase.database.Query;
import com.gym.chater.Data.Message;
import com.gym.chater.Repository.MailerDataManager;

import java.util.Date;

public class Mailer {
    private String senderUserID;
    private String receiverUserID;
    private Date date;

    private MailerDataManager manager;

    public Mailer(String senderUserID, String receiverUserID){
        this.manager = new MailerDataManager();
        this.senderUserID = senderUserID;
        this.receiverUserID = receiverUserID;
        date = new Date();
    }

    public void sendMessage(String message){
        date = new Date();
        String timeStamp = String.valueOf(date.getTime());
        Message messageData = new Message(message, false, senderUserID, receiverUserID, timeStamp, "");
        manager.addUsersChat(getChatID(), timeStamp, messageData);//Unique chat id combined 2 users ids.
    }
    public Query receiveMessages(int number) {
        Query lastQuery = manager.getReferenceDataBase(getChatID());//.orderByKey().limitToLast(number);
        return lastQuery;
    }
    private String getChatID(){
        return (senderUserID + receiverUserID);
    }
}