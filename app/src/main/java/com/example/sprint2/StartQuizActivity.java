package com.example.sprint2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sprint2.DatabaseHelper.DatabaseHelper;
import com.example.sprint2.Model.Category;

import java.util.List;

public class StartQuizActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighScore;
    private Spinner spinnerCategory;

    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_start_quiz );

        textViewHighScore = findViewById( R.id.text_view_highscore );
        spinnerCategory = findViewById( R.id.spinner_category );
        loadCategories();
        loadHighscore();

        Button buttonStartQuiz = findViewById( R.id.button_start_quiz );
        buttonStartQuiz.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startQuiz();
            }
        } );
    }

    private void startQuiz(){
        Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
        int categoryID = (int) selectedCategory.getId();
        String categoryName = selectedCategory.getNama();
        Intent intent = new Intent(StartQuizActivity.this, QuizActivity.class);
        intent.putExtra( EXTRA_CATEGORY_ID, categoryID);
        intent.putExtra( EXTRA_CATEGORY_NAME, categoryName );
        startActivityForResult( intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if(requestCode == REQUEST_CODE_QUIZ){
            if(resultCode == RESULT_OK){
                int score = data.getIntExtra( QuizActivity.EXTRA_SCORE, 0 );
                if(score > highscore){
                    updateHighscore(score);
                }
            }
        }
    }

    private void loadCategories(){
        DatabaseHelper dbHelper = DatabaseHelper.getInstance( this );
        List<Category> categories = dbHelper.getAllCategories();

        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>( this,
                android.R.layout.simple_spinner_item, categories);
        adapterCategories.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinnerCategory.setAdapter( adapterCategories );
    }

    private void loadHighscore(){
        SharedPreferences prefs = getSharedPreferences( SHARED_PREFS, MODE_PRIVATE );
        highscore = prefs.getInt( KEY_HIGHSCORE, 0 );
        textViewHighScore.setText( "Highscore: " + highscore );
    }

    private void updateHighscore(int highscoreNew){
        highscore = highscoreNew;
        textViewHighScore.setText( "Highscore: " + highscore );

        SharedPreferences prefs = getSharedPreferences( SHARED_PREFS, MODE_PRIVATE );
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt( KEY_HIGHSCORE, highscore );
        editor.apply();
    }
}
