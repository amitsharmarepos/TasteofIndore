package com.samyotech.testofindia.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.samyotech.testofindia.R;
import com.samyotech.testofindia.utils.DialogUtility;

import java.util.HashMap;
import java.util.Locale;


public class ThirdActivity extends AppCompatActivity {
    TextView tvShow, vidi;
    LinearLayout lay2;
    ImageView soundS, soundV;
    String part1, part2, part3, Ans;
    private TextToSpeech textToSpeech;
    private AdView mAdView;
    Boolean isFav = false;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        MobileAds.initialize(this, "ca-app-pub-8782135555820344~1276709110");
        tvShow = (TextView) findViewById(R.id.showtv);
        vidi = (TextView) findViewById(R.id.vidi);
        lay2 = (LinearLayout) findViewById(R.id.lay2);
        soundS = (ImageView) findViewById(R.id.soundS);
        soundV = (ImageView) findViewById(R.id.soundV);

        mAdView = (AdView) findViewById(R.id.ad_view);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));

        AdRequest adRequestI = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequestI);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });
        Intent in = getIntent();
        Ans = in.getStringExtra("ans");
        if (Ans.contains("*")) {
            String[] items = Ans.split("[*]", 2);

            part1 = items[0];
            part2 = items[1];
            part3 = part2.replaceAll("[*]", "");

            tvShow.setText(part1);
            vidi.setText(part3);
            soundS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isFav) {

                        isFav = false;

                        soundS.setImageResource(R.drawable.sound1);

                    } else {
                        isFav = true;
                        soundS.setImageResource(R.drawable.sound);

                    }

                    DialogUtility.showProgressDialog(ThirdActivity.this, false, "Please wait...");
                    loadSpeakingLanguages(part1);
                    DialogUtility.pauseProgressDialog();
                    textToSpeech.setLanguage(Locale.forLanguageTag("hin"));
                    //textToSpeech.setSpeechRate(0.09f);

                }
            });
            soundV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogUtility.showProgressDialog(ThirdActivity.this, false, "Please wait...");
                    loadSpeakingLanguages(part3);
                    DialogUtility.pauseProgressDialog();
                    textToSpeech.setLanguage(Locale.forLanguageTag("hin"));
                    //textToSpeech.setSpeechRate(0.09f);
                    if (isFav) {
                        isFav = false;
                        soundV.setImageResource(R.drawable.sound1);

                    } else {
                        isFav = true;
                        soundV.setImageResource(R.drawable.sound);

                    }
                }
            });

        } else {
            tvShow.setText(Ans);
            lay2.setVisibility(View.INVISIBLE);
            soundS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isFav) {

                        isFav = false;

                        soundS.setImageResource(R.drawable.sound1);

                    } else {
                        isFav = true;
                        soundS.setImageResource(R.drawable.sound);

                    }
                    DialogUtility.showProgressDialog(ThirdActivity.this, false, "Please wait...");
                    loadSpeakingLanguages(Ans);
                    DialogUtility.pauseProgressDialog();
                    textToSpeech.setLanguage(Locale.forLanguageTag("hin"));
                    //textToSpeech.setSpeechRate(0.09f);
                }
            });

        }


    }

    private void loadSpeakingLanguages(String textToTranslate) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ttsGreater21(textToTranslate);
        } else {
            ttsUnder20(textToTranslate);
        }
    }

    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text) {
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, map);
        //textToSpeech.setSpeechRate(0.09f);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceId = this.hashCode() + "";
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
        //textToSpeech.setSpeechRate(0.09f);
    }


    @Override
    protected void onResume() {
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
//                    textToSpeech.setLanguage(Locale.getDefault());
                    textToSpeech.setLanguage(Locale.forLanguageTag("hin"));
                    //textToSpeech.setSpeechRate(0.09f);
                }
            }
        });

        super.onResume();
    }

    public void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            textToSpeech = null;
        }
        super.onPause();
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            Log.e("madd", "madd");
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();

        }
    }
}
