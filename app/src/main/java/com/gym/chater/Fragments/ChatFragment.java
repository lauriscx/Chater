package com.gym.chater.Fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.emoji.widget.EmojiEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gym.chater.Adapters.ChatAdapter;
import com.gym.chater.Data.Session;
import com.gym.chater.Data.User;
import com.gym.chater.R;
import com.gym.chater.ViewModel.Login;
import com.gym.chater.ViewModel.Mailer;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatFragment extends Fragment {
    private RecyclerView recyclerView;
    private EmojiEditText messageText;
    private ImageButton sendbutton;
    private CircleImageView friendImage;
    private TextView friendName;

    private User friend;
    private Mailer mailer;

    private Context context;

    private final MediaPlayer player;

    public ChatFragment(User friend, Context context) {
        this.friend = friend;
        this.player = MediaPlayer.create(context, R.raw.send_message);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(!Login.isLoged()) {
            Login.SingIn();
        }
        mailer = new Mailer(Session.user.getUserID(), this.friend.getUserID());

        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerView = view.findViewById(R.id.chat_recycler);
        ChatAdapter adapter = new ChatAdapter(view.getContext(), mailer, recyclerView, friend);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        messageText = view.findViewById(R.id.send_message_text);
        sendbutton = view.findViewById(R.id.send_message_button);
        friendImage = view.findViewById(R.id.chat_user_image);
        friendName = view.findViewById(R.id.chat_user_name);

        Picasso.get().load(friend.getProfileImage()).into(friendImage);
        friendName.setText(friend.getName());

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
                mailer.sendMessage(messageText.getText().toString());
                messageText.setText("");
            }
        });

        return view;
    }
}
