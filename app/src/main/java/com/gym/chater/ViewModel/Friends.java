package com.gym.chater.ViewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.gym.chater.Data.Session;
import com.gym.chater.Data.User;
import com.gym.chater.R;
import com.gym.chater.Repository.RealTimeDataBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Friends {
    static private String data_base_table = "friends";
    static private String data_base_user_table = "users";
    static private List<User> friends;
    static private User userTemplate;

    private static final String TAG = "Friends";

    static {
        RealTimeDataBase.addDataBaseTable(data_base_table);
        friends = new ArrayList<User>();
    }

    static private Query getFriendData(String UID) {
        return RealTimeDataBase.getDataBaseTable(data_base_user_table, UID);
    }


    static public void addFriend(String UID){
        Date date = new Date();
        String timeStamp = String.valueOf(date.getTime());
        RealTimeDataBase.getDataBaseTable(data_base_table, Session.user.getUserID()).child(timeStamp).setValue(UID);
    }
    static public Query receiveFriendsList() {
        Query lastQuery =  RealTimeDataBase.getDataBaseTable(data_base_table, Session.user.getUserID());//.orderByKey().limitToLast(number);
        return lastQuery;
    }

    static public List<User> getFriends(final RecyclerView recycler, final Context context) {

        receiveFriendsList().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messagenode: dataSnapshot.getChildren()) {
                    try {
                        friends.clear();
                        String UID = messagenode.getValue().toString();

                        getFriendData(UID).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                try {
                                    userTemplate = new User();
                                    userTemplate.setName(dataSnapshot.child("name").getValue().toString());
                                    userTemplate.setEmail(dataSnapshot.child("email").getValue().toString());
                                    userTemplate.setProfileImage(dataSnapshot.child("profileImage").getValue().toString());
                                    userTemplate.setUserID(dataSnapshot.child("userID").getValue().toString());
                                    friends.add(userTemplate);

                                    recycler.scrollToPosition(0);

                                } catch (Exception e) {
                                    Toast.makeText(context, context.getString(R.string.Failed_data_base_request), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {}
                        });
                    } catch (Exception e) {
                        Toast.makeText(context, context.getString(R.string.Failed_data_base_request), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        return friends;
    }
}
