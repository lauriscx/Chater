package com.gym.chater.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gym.chater.R;
import com.gym.chater.ViewModel.Login;

public class LogoutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(Login.isLoged()){
            Login.Logout();
            Login.SingIn();
        }
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
