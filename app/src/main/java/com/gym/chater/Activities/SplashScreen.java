package com.gym.chater.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gym.chater.Emoji.CompatEmoji;
import com.gym.chater.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Download emoji
        CompatEmoji.InitCompatEmoji(this);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(2000);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
