package com.example.sailingrecommendations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    /**
     * onCreate*
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_start);
            final Button btn_start = findViewById(R.id.st_btn_appStart);
            btn_start.setOnClickListener(btnClick);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
}
