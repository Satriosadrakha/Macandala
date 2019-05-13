package com.example.sprint2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LessonPlaceholderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lesson_placeholder );

        //int categoryId = getIntent().getIntExtra("CATEGORY_ID", 0);


        Button intro = (Button) findViewById(R.id.intro);
        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), IntroActivity.class);
                startActivity(intent);
            }
        });

        final Intent intent = new Intent(getBaseContext(), AksaraLessonActivity.class);

        Button swara = (Button) findViewById(R.id.swara);
        swara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 0);
                startActivity(intent);
            }
        });
        Button button1 = (Button) findViewById(R.id.kaganga1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 1);
                startActivity(intent);
            }
        });
        Button button2 = (Button) findViewById(R.id.kaganga2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 2);
                startActivity(intent);
            }
        });
        Button button3 = (Button) findViewById(R.id.kaganga3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 3);
                startActivity(intent);
            }
        });
        Button button4 = (Button) findViewById(R.id.kaganga4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 4);
                startActivity(intent);
            }
        });
        Button button5 = (Button) findViewById(R.id.kaganga5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 5);
                startActivity(intent);
            }
        });
        Button button6 = (Button) findViewById(R.id.kaganga6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 6);
                startActivity(intent);
            }
        });
        Button button7 = (Button) findViewById(R.id.kaganga7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("LESSON_ID", 7);
                startActivity(intent);
            }
        });
    }
}
