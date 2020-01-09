package com.gym.chater.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gym.chater.R;
import com.gym.chater.Settings;
import com.gym.chater.ViewModel.Login;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class SettingsFragment extends Fragment {
    private ImageView Image;
    private Button LT;
    private Button EN;
    private Button SoundNutton;
    private static Locale language;
    private static Context contex;

    public SettingsFragment(Context contex){
        this.contex = contex;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Image   = view.findViewById(R.id.Profile_Image);
        LT    = view.findViewById(R.id.LT);
        EN   = view.findViewById(R.id.EN);
        SoundNutton = view.findViewById(R.id.SoundButton);
        if(!Login.isLoged()) {
            Login.SingIn();
        }
        Picasso.get().load(Login.getFireBaseUser().getPhotoUrl()).into(Image);

        LT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("lt");
            }
        });

        EN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("en");
            }
        });

        SoundNutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Settings.isSoundEffects()){
                    SoundNutton.setText(contex.getString(R.string.Off));
                    Settings.setSoundEffects(false);
                } else {
                    SoundNutton.setText(contex.getString(R.string.On));
                    Settings.setSoundEffects(true);
                }
            }
        });

        return view;
    }

    private static void setLanguage(String lang) {
        language = new Locale(lang);
        Locale.setDefault(language);
        Configuration config = new Configuration();
        config.locale = language;
        contex.getResources().updateConfiguration(config, contex.getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = contex.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("Language", lang);
        editor.apply();
    }

    public static void loadLanguage(Context contex) {
        SharedPreferences pref = contex.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        setLanguage(pref.getString("Language", ""));

    }

}
