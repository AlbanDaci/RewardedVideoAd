package com.tumblr.albandaci.rewardedvideoad;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.io.IOException;


public class Menu extends AppCompatActivity implements RewardedVideoAdListener{

    Button button;
    Button button2;
    private RewardedVideoAd mAd;
    private String URL1 = "http://download.audioislam.com/audio/quran/recitation/al-afaasee/surah_al_fatihah.mp3";
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        mediaPlayer = new MediaPlayer();

        mAd = MobileAds.getRewardedVideoAdInstance(this);
        mAd.setRewardedVideoAdListener(this);
        loadAd();

        button.setEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                if (mAd.isLoaded()) {
                    mAd.show();
                }
            }
        });

        button2.setEnabled(false);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                try {
                    mediaPlayer.setDataSource(URL1);
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer1) {
                            mediaPlayer1.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadAd(){
        if(!mAd.isLoaded()) {
            mAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
        }
    }

    // Required to reward the user.
    @Override
    public void onRewarded(RewardItem reward) {
        button2.setEnabled(true);
    }

    // The following listener methods are optional.
    @Override
    public void onRewardedVideoAdLeftApplication() {
    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {

    }

    @Override
    public void onRewardedVideoAdLoaded() {
        button.setEnabled(true);
        Toast.makeText(this, "Click the Play button in the toolbar and watch the Ad untill the end so you can listen to the audio", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }
}
