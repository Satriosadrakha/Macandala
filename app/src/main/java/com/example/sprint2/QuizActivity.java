package com.example.sprint2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sprint2.DatabaseHelper.DatabaseHelper;
import com.example.sprint2.Model.Question;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCategory;

    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private int score;
    private boolean answered;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_quiz );

        textViewQuestion = findViewById( R.id.text_view_question );
        textViewScore = findViewById( R.id.text_view_score );
        textViewQuestionCount = findViewById( R.id.text_view_question_count);
        textViewCategory = findViewById( R.id.text_view_category);

        rbGroup = findViewById( R.id.radio_group );
        rb1 = findViewById( R.id.radio_button1 );
        rb2 = findViewById( R.id.radio_button2 );
        rb3 = findViewById( R.id.radio_button3 );
        rb4 = findViewById( R.id.radio_button4 );
        buttonConfirmNext = findViewById( R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();

        Intent intent = getIntent();
        int categoryID = intent.getIntExtra( StartQuizActivity.EXTRA_CATEGORY_ID,0 );
        String categoryName = intent.getStringExtra( StartQuizActivity.EXTRA_CATEGORY_NAME );

        textViewCategory.setText( "Category: " + categoryName );

        if(savedInstanceState == null){
            DatabaseHelper dbHelper = DatabaseHelper.getInstance( this );
            questionList = dbHelper.getQuestions(categoryID);
            questionCountTotal = questionList.size();
            Collections.shuffle( questionList );
        } else{
            //questionList = savedInstanceState.getParcelableArrayList( KEY_QUESTION_LIST )
        }

//        DatabaseHelper dbHelper = new DatabaseHelper( this );
//        questionList = dbHelper.getAllQuestions();
//        questionCountTotal = questionList.size();
//        Collections.shuffle( questionList );

        showNextQuestion();

        buttonConfirmNext.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        checkedAnswer();
                    } else{
                        Toast.makeText( QuizActivity.this, "Please select an answer!", Toast.LENGTH_SHORT ).show();
                    }
                } else{
                    showNextQuestion();
                }

            }
        } );

    }

    private void showNextQuestion(){
        rb1.setTextColor( textColorDefaultRb );
        rb2.setTextColor( textColorDefaultRb );
        rb3.setTextColor( textColorDefaultRb );
        rb4.setTextColor( textColorDefaultRb );
        rbGroup.clearCheck();

        if(questionCounter < questionCountTotal){
            currentQuestion = questionList.get( questionCounter );

            textViewQuestion.setText( currentQuestion.getQuestion() );
            rb1.setText( currentQuestion.getOption1() );
            rb2.setText( currentQuestion.getOption2() );
            rb3.setText( currentQuestion.getOption3() );
            rb4.setText( currentQuestion.getOption4() );

            questionCounter++;
            textViewQuestionCount.setText( "Question: " + questionCounter + "/" +questionCountTotal );
            answered = false;
            buttonConfirmNext.setText( "Confirm" );
        } else{
            finishQuiz();
        }
    }

    private void checkedAnswer(){
        answered = true;

        RadioButton rbSelected = findViewById( rbGroup.getCheckedRadioButtonId() );
        int answerNr = rbGroup.indexOfChild( rbSelected ) + 1;

        if (answerNr == currentQuestion.getAnswerNr()){
            score++;
            textViewScore.setText( "Score: " + score );
        }

        showSolution();
    }

    private void showSolution(){
        rb1.setTextColor( Color.RED );
        rb2.setTextColor( Color.RED );
        rb3.setTextColor( Color.RED );
        rb4.setTextColor( Color.RED );

        switch (currentQuestion.getAnswerNr()){
            case 1:
                rb1.setTextColor( Color.GREEN );
                textViewQuestion.setText( "Answer 1 is correct" );
                break;
            case 2:
                rb2.setTextColor( Color.GREEN );
                textViewQuestion.setText( "Answer 2 is correct" );
                break;
            case 3:
                rb3.setTextColor( Color.GREEN );
                textViewQuestion.setText( "Answer 3 is correct" );
                break;
            case 4:
                rb4.setTextColor( Color.GREEN );
                textViewQuestion.setText( "Answer 4 is correct" );
                break;
        }

        if(questionCounter < questionCountTotal){
            buttonConfirmNext.setText( "Next" );
        } else{
            buttonConfirmNext.setText( "Finish" );
        }
    }

    private void finishQuiz(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra( EXTRA_SCORE, score );
        setResult( RESULT_OK, resultIntent );
        finish();
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            finishQuiz();
        } else{
            Toast.makeText( this, "Press back again to finish", Toast.LENGTH_SHORT ).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
