package com.sunda.mandalaapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.sunda.mandalaapi.DatabaseHelper.DatabaseHelper;
import com.sunda.mandalaapi.Model.Category;

import java.util.List;

public class StartQuizActivity extends AppCompatActivity {
    //private static final int REQUEST_CODE_QUIZ = 3;
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";

    public static final String SHARED_PREFS = "sharedPrefs";

    public static final String[] KEY_HIGHSCORE = {"keyHighscore1", "keyHighscore2", "keyHighscore3", "keyHighscore4", "keyHighscore5", "keyHighscore6", "keyHighscore7","keyHighscore8"};
//    public static final String KEY_HIGHSCORE1 = "keyHighscore1";
//    public static final String KEY_HIGHSCORE2 = "keyHighscore2";
//    public static final String KEY_HIGHSCORE3 = "keyHighscore3";
    private TextView textViewHighScore;
    private Spinner spinnerCategory;

    Intent cat;
    int categoryID;
    String categoryName;
    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_start_quiz );

        cat = getIntent();
        categoryID = (int) cat.getIntExtra(LessonActivity.EXTRA_CATEGORY_ID, 0);
        categoryName = cat.getStringExtra(LessonActivity.EXTRA_CATEGORY_NAME);

        TextView textView = (TextView) findViewById(R.id.start_quiz_title);
        //Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/SundaneseUnicode-2.0.ttf");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Sundari_unicode.otf");
        textView.setTypeface(typeface);

        textViewHighScore = findViewById( R.id.text_view_highscore );
        spinnerCategory = findViewById( R.id.spinner_category );
        loadCategories();
        loadHighscore(categoryID);

        Button buttonStartQuiz = findViewById( R.id.button_start_quiz );
        buttonStartQuiz.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        } );
    }

    private void startQuiz(){
//        Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
//
//        int categoryID = (int) selectedCategory.getId();
//        String categoryName = selectedCategory.getNama();

//        Intent cat = getIntent();
//        int categoryID = (int) cat.getIntExtra(LessonActivity.EXTRA_CATEGORY_ID, 0);
//        String categoryName = cat.getStringExtra(LessonActivity.EXTRA_CATEGORY_NAME);

//        cat = getIntent();
//        categoryID = (int) cat.getIntExtra(LessonActivity.EXTRA_CATEGORY_ID, 0);
//        categoryName = cat.getStringExtra(LessonActivity.EXTRA_CATEGORY_NAME);

        Intent intent = new Intent(StartQuizActivity.this, QuizActivity.class);
        intent.putExtra( EXTRA_CATEGORY_ID, categoryID);
        intent.putExtra( EXTRA_CATEGORY_NAME, categoryName );
        startActivityForResult( intent, categoryID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

//        if(requestCode == REQUEST_CODE_QUIZ){
//            if(resultCode == RESULT_OK){
//                int score = data.getIntExtra( QuizActivity.EXTRA_SCORE, 0 );
//                if(score > highscore){
//                    updateHighscore(requestCode, score);
//                }
//            }
//        }
        if(resultCode == RESULT_OK){
            int score = data.getIntExtra( QuizActivity.EXTRA_SCORE, 0 );
            if(score > highscore){
                updateHighscore(requestCode, score);
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

    private void loadHighscore(int categoryID){
        SharedPreferences prefs = getSharedPreferences( SHARED_PREFS, MODE_PRIVATE );
//        String KEY_HIGHSCORE = KEY_HIGHSCORE[0];
//        for(int i = 0; i<8; i++){
//
//        }
//        switch(categoryID) {
//            case 1:
//                KEY_HIGHSCORE = KEY_HIGHSCORE1;
//                break;
//            case 2:
//                KEY_HIGHSCORE = KEY_HIGHSCORE2;
//                break;
//            case 3:
//                KEY_HIGHSCORE = KEY_HIGHSCORE3;
//                break;
//            default:
//                // code block
//        }
        highscore = prefs.getInt( KEY_HIGHSCORE[categoryID-1], 0 );
        textViewHighScore.setText( "Skor tertinggi: " + highscore );
    }

    private void updateHighscore(int requestCode, int highscoreNew){
        highscore = highscoreNew;
        textViewHighScore.setText( "Skor tertinggi: " + highscore );

        SharedPreferences prefs = getSharedPreferences( SHARED_PREFS, MODE_PRIVATE );
        SharedPreferences.Editor editor = prefs.edit();
//        String KEY_HIGHSCORE = KEY_HIGHSCORE1;
//        switch(requestCode) {
//            case 1:
//                KEY_HIGHSCORE = KEY_HIGHSCORE1;
//                break;
//            case 2:
//                KEY_HIGHSCORE = KEY_HIGHSCORE2;
//                break;
//            case 3:
//                KEY_HIGHSCORE = KEY_HIGHSCORE3;
//                break;
//            default:
//                // code block
//        }
        editor.putInt( KEY_HIGHSCORE[requestCode-1], highscore );
        editor.apply();
    }
}
