package com.gym.chater.Fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gym.chater.AdMob.BannerListener;
import com.gym.chater.AdMob.FullScreenListener;
import com.gym.chater.AdMob.RewardVideoListener;
import com.gym.chater.R;
import com.gym.chater.ViewModel.Login;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;

public class HomeFragment extends Fragment {
    private AdView banner;
    private InterstitialAd fullScreenAd;
    private RewardedVideoAd rewardVideoAd;
    private Button fullScreenButton;
    private Button rewardButton;
    private Context context;
    private MediaPlayer player;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(!Login.isLoged()) {
            Login.SingIn();
        }
        context = this.getContext();
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        player = MediaPlayer.create(context, R.raw.menu_sound);

        setmRewardedVideoAd(view);
        setFullScreenAdSetUp(view);
        setBanner(view);

        return view;
    }

    private void setmRewardedVideoAd(View view) {
        //set reward video.
        rewardVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        rewardVideoAd.setRewardedVideoAdListener(new RewardVideoListener(this.getContext()));
        rewardVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());

        rewardButton = view.findViewById(R.id.test_reward);
        rewardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rewardVideoAd.isLoaded()) {
                    player.start();
                    rewardVideoAd.show();
                } else {
                    Toast.makeText(context, context.getString(R.string.Add_not_loaded), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void setFullScreenAdSetUp(View view){
        //set up mobAd full screen ad id.
        MobileAds.initialize(this.getContext(), "ca-app-pub-3940256099942544~3347511713");

        fullScreenAd = new InterstitialAd(context);
        fullScreenAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        fullScreenAd.loadAd(new AdRequest.Builder().build());
        fullScreenAd.setAdListener(new FullScreenListener(context));

        fullScreenButton = view.findViewById(R.id.test_ad);
        fullScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullScreenAd.isLoaded()) {
                    player.start();
                    fullScreenAd.show();
                } else {
                    Toast.makeText(context, context.getString(R.string.Add_not_loaded), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void setBanner(View view){
        //get banner
        banner = view.findViewById(R.id.banner);
        banner.loadAd(new AdRequest.Builder().build());
        banner.setAdListener(new BannerListener(context));
    }
}
