package com.gym.chater.Repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RealTimeDataBase {
    private static FirebaseDatabase database;
    private static Map<String, DatabaseReference> databaseReference;

    static {
        database = FirebaseDatabase.getInstance();
        databaseReference = new HashMap<String, DatabaseReference>();
    }
    public static void addDataBaseTable(String name){
        databaseReference.put(name, database.getReference(name));
    }
    public static DatabaseReference getDataBaseTable(String nodeName, String nodeChild){
        /*put all data in child structure which name is fieldname*/
        return databaseReference.get(nodeName).child(nodeChild);
    }
    public static DatabaseReference getDataBaseTable(String nodeName){
        /*put all data in child structure which name is fieldname*/
        return databaseReference.get(nodeName);
    }
}
