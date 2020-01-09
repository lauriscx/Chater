package com.gym.chater.AdMob;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.gym.chater.R;
import com.gym.chater.Repository.KarmaPoints;
import com.gym.chater.Settings;

public class RewardVideoListener implements RewardedVideoAdListener {
    private Context context;
    private MediaPlayer player;

    public RewardVideoListener(Context context){
        this.context = context;
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        KarmaPoints.addkarmaPoints(rewardItem.getAmount());
        if(Settings.isSoundEffects()) {
            MediaPlayer.create(context, R.raw.applause);
        }
        Toast.makeText(context, context.getText(R.string.You_get_karma_points) + " +" + rewardItem.getAmount() + " \uD83D\uDE02", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }
}
