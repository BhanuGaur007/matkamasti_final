package com.example.matkamasthi;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    ImageView ivTOp, ivHeart, ivBottom;
    TextView tvTextview;

    CharSequence charSequence;
    int index;
    long delay = 200;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivTOp = findViewById(R.id.iv_top);
        ivBottom = findViewById(R.id.iv_bottom);
        ivHeart = findViewById(R.id.iv_logo);
        tvTextview = findViewById(R.id.tv_text);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // for animation top

        Animation animationtop = AnimationUtils.loadAnimation(this, R.anim.top_wave);
        ivTOp.setAnimation(animationtop);


        // for animation bottom
        Animation animationBottom = AnimationUtils.loadAnimation(this, R.anim.bottom_wave);
        ivBottom.setAnimation(animationBottom);

        // for heart wave
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(ivHeart,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        );
        objectAnimator.setDuration(400);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
        animatText("MATKA MASTHI");
    }


    // for Runnable method
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            tvTextview.setText(charSequence.subSequence(0,index++));
            if (index<=charSequence.length()){
                handler.postDelayed(runnable,delay);
            }
        }
    };
    private void animatText(CharSequence cs) {
        charSequence = cs;
        index = 0;
        tvTextview.setText("");
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, delay);


    }
}