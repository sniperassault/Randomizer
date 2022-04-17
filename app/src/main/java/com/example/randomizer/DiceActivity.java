package com.example.randomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.squareup.seismic.ShakeDetector;
import java.util.Random;

public class DiceActivity extends AppCompatActivity implements ShakeDetector.Listener {
    private ImageView imageViewDice;
    private Random rng = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_dice);
        final MediaPlayer sound=MediaPlayer.create(this,R.raw.sound);
        final MediaPlayer end=MediaPlayer.create(this,R.raw.end);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector shakeDetector = new ShakeDetector(this);
        shakeDetector.start(sensorManager);
        imageViewDice = findViewById(R.id.image_view_dice);
        imageViewDice.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Animation anim= AnimationUtils.loadAnimation(DiceActivity.this,R.anim.shake);
                final Animation.AnimationListener animationListener=new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        sound.start();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        rollDice();
                        end.start();

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };
                    anim.setAnimationListener(animationListener);
                    imageViewDice.startAnimation(anim);

                return true;
            }
        });
    }

    private void rollDice() {
        int randomNumber = rng.nextInt(6) + 1;

        switch (randomNumber) {
            case 1:
                imageViewDice.setImageResource(R.drawable.dice1);
                break;
            case 2:
                imageViewDice.setImageResource(R.drawable.dice2);
                break;
            case 3:
                imageViewDice.setImageResource(R.drawable.dice3);
                break;
            case 4:
                imageViewDice.setImageResource(R.drawable.dice4);
                break;
            case 5:
                imageViewDice.setImageResource(R.drawable.dice5);
                break;
            case 6:
                imageViewDice.setImageResource(R.drawable.dice6);
                break;
        }
    }

    @Override
    public void hearShake() {
        final MediaPlayer sound=MediaPlayer.create(this,R.raw.sound);
        final MediaPlayer end=MediaPlayer.create(this,R.raw.end);
        final Animation anim= AnimationUtils.loadAnimation(DiceActivity.this,R.anim.shake);
        final Animation.AnimationListener animationListener=new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                sound.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                rollDice();
                end.start();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        anim.setAnimationListener(animationListener);
        imageViewDice.startAnimation(anim);

    }
}