package com.gym.chater.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.gym.chater.Data.FragmentManager;
import com.gym.chater.Data.Message;
import com.gym.chater.Data.User;
import com.gym.chater.Fragments.ChatFragment;
import com.gym.chater.R;
import com.gym.chater.Repository.RealTimeDataBase;
import com.gym.chater.ViewModel.Friends;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    private static final String TAG = "UsersAdapter";
    static private List<User> users;
    private final Context context;
    private final RecyclerView recycler;
    static private String data_base_table = "users";

    public UsersAdapter(final Context context, final RecyclerView recycler) {
        this.recycler = recycler;
        users = new ArrayList<User>();
        Query lastQuery = RealTimeDataBase.getDataBaseTable(data_base_table);
        this.context = context;

        lastQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messagenode: dataSnapshot.getChildren()) {
                    try {
                        String email = messagenode.child("email").getValue().toString();
                        String name = messagenode.child("name").getValue().toString();
                        String profileImage = messagenode.child("profileImage").getValue().toString();
                        String userID = messagenode.child("userID").getValue().toString();

                        User user = new User();

                        user.setEmail(email);
                        user.setName(name);
                        user.setProfileImage(profileImage);
                        user.setUserID(userID);

                        users.add(user);

                    } catch (Exception e) {
                        Toast.makeText(context, context.getString(R.string.Failed_data_base_request), Toast.LENGTH_SHORT).show();
                    }
                }
                recycler.scrollToPosition(0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_users_filler, parent, false);//get item(friend) view.
        ViewHolder holder = new ViewHolder(view);//use our created class and pass view. In class we get view items ids and fill it with data. Filled views fut in layout fragment_friends.
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//Then view is loading to layout.
        Picasso.get().load(users.get(position).getProfileImage()).into(holder.image);
        holder.userName.setText(users.get(position).getName());
        holder.email.setText(users.get(position).getEmail());


        final User user = users.get(position);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friends.addFriend(user.getUserID());
                Toast.makeText(context, context.getString(R.string.User_added_to_friends) + " " + user.getEmail(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.userViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Not implement yet user profile watch in UsersAdapter.java function onBindViewHolder", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {//declare view holder type and hold data for layout
        CircleImageView image;
        TextView userName;
        TextView email;
        RelativeLayout userViewHolder;
        ImageButton add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.users_image);
            userName = itemView.findViewById(R.id.user_display_name);
            email = itemView.findViewById(R.id.user_email);
            userViewHolder = itemView.findViewById(R.id.users_view);
            add = itemView.findViewById(R.id.user_add);
        }
    }
}
