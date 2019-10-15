package com.sunda.mandalaapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    Button StartButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

//        TextView textView = (TextView) findViewById(R.id.textView);
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/yourfont.ttf");
//        textView.setTypeface(typeface);

        StartButton = (Button) findViewById(R.id.startButton);
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
            }
        });


    }
}
