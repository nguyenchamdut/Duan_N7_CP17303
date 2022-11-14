package com.example.duan_n7_cp17303.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.duan_n7_cp17303.R;
public class ManHinhChaoActivity extends AppCompatActivity {
    private ImageView imgTen,imgNhan;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        imgTen = findViewById(R.id.img_ten);
        imgNhan = findViewById(R.id.img_nhan);
        layout = findViewById(R.id.id_linear);

        Animation animation = new AlphaAnimation(1,0);
        animation.setDuration(1000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        imgNhan.startAnimation(animation);


        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        imgTen.startAnimation(animation1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(ManHinhChaoActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        },5000);
    }
}