package com.sunda.mandalaapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LearningTipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setTitle( "Tips Belajar" );
        setContentView( R.layout.activity_learning_tips );

        TextView title = (TextView)findViewById( R.id.title );
        TextView content = (TextView)findViewById( R.id.content);

        Intent intent = getIntent();

        title.setText(
                String.valueOf( getResources().getStringArray(R.array.learningTipsTitle)[
                intent.getIntExtra(LessonActivity.EXTRA_CATEGORY_ID,0)])
        );

        content.setText(
                String.valueOf( getResources().getStringArray(R.array.learningTipsContent)[
                        intent.getIntExtra(LessonActivity.EXTRA_CATEGORY_ID,0)])
        );
    }
}
