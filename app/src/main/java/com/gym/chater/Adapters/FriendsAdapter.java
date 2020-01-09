package com.gym.chater.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gym.chater.Data.FragmentManager;
import com.gym.chater.Data.User;
import com.gym.chater.Fragments.ChatFragment;
import com.gym.chater.R;
import com.gym.chater.ViewModel.Friends;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {
    private static final String TAG = "FriendsAdapter";
    static private List<User> friends;
    private final RecyclerView recycler;
    private Context context;

    public FriendsAdapter(Context context, final RecyclerView recycler) {
        this.recycler = recycler;
        friends = Friends.getFriends(recycler, context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_friends_filler, parent, false);//get item(friend) view.
        ViewHolder holder = new ViewHolder(view);//use our created class and pass view. In class we get view items ids and fill it with data. Filled views fut in layout fragment_friends.
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//Then view is loading to layout.
        Picasso.get().load(friends.get(position).getProfileImage()).into(holder.image);
        holder.userName.setText(friends.get(position).getName());
        holder.email.setText(friends.get(position).getEmail());

        final User user = friends.get(position);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager.getManager().beginTransaction().replace(R.id.fragment_container, new ChatFragment(user, context)).commit();
            }
        });

        holder.userViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Not implement yet user profile watch in FriendsAdapter.java function onBindViewHolder", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {//declare view holder type and hold data for layout
        CircleImageView image;
        TextView userName;
        TextView email;
        RelativeLayout userViewHolder;
        ImageButton add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            userName = itemView.findViewById(R.id.display_name);
            email = itemView.findViewById(R.id.email);
            userViewHolder = itemView.findViewById(R.id.friend_view);
            add = itemView.findViewById(R.id.add);
        }
    }
}
