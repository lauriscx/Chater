package com.gym.chater.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.emoji.widget.EmojiTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.gym.chater.Data.Message;
import com.gym.chater.Data.Session;
import com.gym.chater.Data.User;
import com.gym.chater.R;
import com.gym.chater.ViewModel.Login;
import com.gym.chater.ViewModel.Mailer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private static final String TAG = "ChatAdapter";

    private final Context context;
    private User connectedUser;
    private List<Message> messages;
    private Mailer mailer;
    private final RecyclerView recycler;
    private User friend;

    public ChatAdapter(final Context context, Mailer mailer, final RecyclerView recycler, User friend) {
        this.context = context;
        this.mailer = mailer;
        this.recycler = recycler;
        this.friend = friend;
        this.messages = new ArrayList<Message>();

        mailer.receiveMessages(10).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messagenode: dataSnapshot.getChildren()) {
                    try {
                        String emoji = messagenode.child("emoji").getValue().toString();
                        String message = messagenode.child("message").getValue().toString();
                        String readed = messagenode.child("readed").getValue().toString();
                        String receiver = messagenode.child("receiver").getValue().toString();
                        String sender = messagenode.child("sender").getValue().toString();
                        String time = messagenode.getKey();//get time

                        Message mes = new Message();
                        mes.setEmoji(emoji);
                        mes.setMessage(message);
                        mes.setReaded(Boolean.parseBoolean(readed));
                        mes.setReceiver(receiver);
                        mes.setSender(sender);
                        mes.setTime(time);
                        messages.add(mes);
                    } catch (Exception e) {
                        Toast.makeText(context, context.getString(R.string.Failed_data_base_request), Toast.LENGTH_SHORT).show();
                    }
                }
                recycler.scrollToPosition(getItemCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_chat_filler, parent, false);//get item(friend) view.
        ViewHolder holder = new ViewHolder(view);//use our created class and pass view. In class we get view items ids and fill it with data. Filled views fut in layout fragment_friends.
        connectedUser = Login.getUserConnected();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//Then view is loading to layout.
        if(messages.get(position).getSender().equals(Session.user.getUserID())){
            holder.message_receiver.setText(messages.get(position).getMessage());
            Picasso.get().load(Session.user.getProfileImage()).into(holder.image_receiver);
            holder.image_receiver.setVisibility(holder.itemView.VISIBLE);//true
            holder.message_receiver.setVisibility(holder.itemView.VISIBLE);

            holder.image_sender.setVisibility(holder.itemView.INVISIBLE);//false
            holder.message_sender.setVisibility(holder.itemView.INVISIBLE);
        } else {
            holder.image_receiver.setVisibility(holder.itemView.INVISIBLE);//false
            holder.message_receiver.setVisibility(holder.itemView.INVISIBLE);

            holder.message_sender.setText(messages.get(position).getMessage());
            Picasso.get().load(friend.getProfileImage()).into(holder.image_sender);
            holder.image_sender.setVisibility(holder.itemView.VISIBLE);//true
            holder.message_sender.setVisibility(holder.itemView.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {//declare view holder type and hold data for layout
        ImageView image_receiver;
        ImageView image_sender;
        EmojiTextView message_receiver;
        EmojiTextView message_sender;

        RelativeLayout chatLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_receiver = itemView.findViewById(R.id.image_receiver);
            image_sender = itemView.findViewById(R.id.image_sender);
            message_sender = itemView.findViewById(R.id.message_sender);
            message_receiver = itemView.findViewById(R.id.message_receiver);

            chatLayout = itemView.findViewById(R.id.chat_room_layout);

        }
    }
}
