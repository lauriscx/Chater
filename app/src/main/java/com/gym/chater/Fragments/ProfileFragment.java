package com.gym.chater.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gym.chater.R;
import com.gym.chater.ViewModel.Login;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {
    private ImageView Image;
    private TextView Name;
    private TextView Email;
    private TextView Phone;
    private TextView Provider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Image   = view.findViewById(R.id.Profile_Image);
        Name    = view.findViewById(R.id.Profile_Person);
        Email   = view.findViewById(R.id.Profile_Email);
        Phone   = view.findViewById(R.id.Profile_Phone);
        Provider = view.findViewById(R.id.Profile_Provider);
        if(!Login.isLoged()) {
            Login.SingIn();
        }
        Picasso.get().load(Login.getFireBaseUser().getPhotoUrl()).into(Image);
        Name.setText(Login.getFireBaseUser().getDisplayName());
        Email.setText(Login.getFireBaseUser().getEmail());
        Phone.setText(Login.getFireBaseUser().getPhoneNumber());
        Provider.setText(Login.getFireBaseUser().getProviderId());



        return view;
    }

}
