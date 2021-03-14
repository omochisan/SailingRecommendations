package com.example.sailingrecommendations;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class EndActivity extends AppCompatActivity {
    private static int TIMEOUT_MESSAGE =1;
    private static int interval =1;
    private String put_word = "";
    private String put_text = "";
    private int i =0;

    /**
     * onCreate*
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_end);

            // animation をつける
            FrameLayout frame_cheering = (FrameLayout) findViewById(R.id.en_frame_lines);
            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
            frame_cheering.startAnimation(hyperspaceJumpAnimation);
            hyperspaceJumpAnimation.setAnimationListener(textAnime);

            Button btn_back = findViewById(R.id.en_btn_back);
            btn_back.setOnClickListener(btnClick);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Animation.AnimationListener textAnime = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // ハンドラ実行
            handler.sendEmptyMessage(TIMEOUT_MESSAGE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    // 文字列を一文字ずつ出力するハンドラ
    private Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            TextView cheering = findViewById(R.id.en_cheering);
            // 文字列を配列dataに１文字ずつセット
            char data[] = getResources().getString(R.string.Cheering_iowa).toCharArray();

            if(i < data.length){
                if (msg.what == TIMEOUT_MESSAGE) {
                    if(String.valueOf(data[i]).equals("*")){
                        handler.sendEmptyMessageDelayed(TIMEOUT_MESSAGE, interval * 700);
                    }else {
                        put_word = String.valueOf(data[i]);
                        put_text = put_text + put_word;

                        cheering.setText(put_text);
                        handler.sendEmptyMessageDelayed(TIMEOUT_MESSAGE, interval * 25);
                    }
                    i++;
                }else{
                    super.dispatchMessage(msg);
                }
            }
        }
    };
}
