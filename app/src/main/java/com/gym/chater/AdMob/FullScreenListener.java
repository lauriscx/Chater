package com.gym.chater.AdMob;

import android.content.Context;
import android.widget.Toast;

import com.gym.chater.R;
import com.gym.chater.Repository.KarmaPoints;

public class FullScreenListener extends com.google.android.gms.ads.AdListener {
    private Context context;

    public FullScreenListener(Context context){
        this.context = context;
    }


    @Override
    public void onAdLoaded() {
        // Code to be executed when an ad finishes loading.
    }

    @Override
    public void onAdFailedToLoad(int errorCode) {
        // Code to be executed when an ad request fails.
    }

    @Override
    public void onAdOpened() {
        // Code to be executed when the ad is displayed.
    }

    @Override
    public void onAdClicked() {
        KarmaPoints.addkarmaPoints(1);
        Toast.makeText(context, context.getText(R.string.You_get_karma_points) + " +1 \uD83D\uDE11", Toast.LENGTH_LONG).show();
        // Code to be executed when the user clicks on an ad.
    }

    @Override
    public void onAdLeftApplication() {
        // Code to be executed when the user has left the app.
    }

    @Override
    public void onAdClosed() {
        // Code to be executed when the interstitial ad is closed.
    }
}