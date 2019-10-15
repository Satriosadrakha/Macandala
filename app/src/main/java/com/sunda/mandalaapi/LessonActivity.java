package com.sunda.mandalaapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.sunda.mandalaapi.DatabaseHelper.DatabaseHelper;
import com.sunda.mandalaapi.Model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LessonActivity extends AppCompatActivity {
    private static final String TAG = LessonActivity.class.getSimpleName();
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";

    private DatabaseHelper db;
    private ProgressDialog pDialog;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lesson );

        Intent intent = getIntent();

        int lessonID = intent.getIntExtra(mLearnFragment.LESSON_ID,0);

        // Progress dialog
        pDialog = new ProgressDialog( this );
        pDialog.setCancelable( false );

        db = new DatabaseHelper(getApplicationContext());

//        Toast.makeText(getApplicationContext(),String.valueOf(lessonID),Toast.LENGTH_SHORT).show();

        TextView title1 = findViewById( R.id.title1 );
        TextView title2 = findViewById( R.id.title2 );
        TextView subTitle1 = findViewById( R.id.subTitle1 );
        TextView subTitle2 = findViewById( R.id.subTitle2 );
        if(lessonID==0){
            subTitle1.setText( "Mengalihaksara\nAksara Sunda -> Aksara Latin\nSatu suku kata" );
            subTitle2.setText( "Mengalihaksara\nAksara Sunda -> Aksara Latin\nDua suku kata" );
            title = "Pelajaran Dasar 1";
        } else if (lessonID==1){
            subTitle1.setText( "Menerjemahkan\nBahasa Sunda -> Bahasa Indonesia" );
            subTitle2.setText( "Menerjemahkan\nBahasa Indonesia -> Bahasa Sunda" );
            title = "Pelajaran Dasar 2";
        } else if (lessonID==2){
            subTitle1.setText( "Menerjemahkan\nBahasa Indonesia <-> Bahasa Sunda" );
            subTitle2.setText( "Menerjemahkan\nAksara Sunda <-> Aksara Latin" );
            title = "Pelajaran mengenai hewan";
        } else if (lessonID==3){
            subTitle1.setText( "Menerjemahkan\nBahasa Indonesia <-> Bahasa Sunda" );
            subTitle2.setText( "Menerjemahkan\nAksara Sunda <-> Aksara Latin" );
            title = "Pelajaran mengenai warna";
        }
        title1.setText( "Pelajaran 1");
        title2.setText( "Pelajaran 2");

        LinearLayout tips = findViewById( R.id.tips );
        tips.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonActivity.this, LearningTipsActivity.class);
                intent.putExtra( EXTRA_CATEGORY_ID, lessonID);
                startActivity( intent );
            }
        } );

        LinearLayout buttonStartQuiz1 = findViewById( R.id.lesson1 );
        buttonStartQuiz1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int category_id = (lessonID+1)*2-1;
                getQuiz(category_id);
            }
        } );

        LinearLayout buttonStartQuiz2 = findViewById( R.id.lesson2 );
        buttonStartQuiz2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int category_id = (lessonID+1)*2;
                getQuiz(category_id);
//                Intent intent = new Intent(LessonActivity.this, StartQuizActivity.class);
//                intent.putExtra( EXTRA_CATEGORY_ID, (lessonID+1)*2);
//                intent.putExtra( EXTRA_CATEGORY_NAME, String.valueOf( getResources().getStringArray(R.array.category)[(lessonID+1)*2-1]));
//                //StartQuizActivity.REQUEST_CODE_QUIZ = 2;
//                startActivity( intent );
            }
        } );
    }

    private void getQuiz(final Integer category_id){
        String tag_string_req = "req_login";

        pDialog.setMessage("Sedang Download ...");
        showDialog();

        JSONObject obj = new JSONObject();
        try {
            obj.put("category_id", category_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        JsonObjectRequest strReq = new JsonObjectRequest( Request.Method.POST,
//                AppConfig.URL_QUIZ, obj, new Response.Listener<JSONObject>() {
//        JsonArrayRequest strReq = new JsonArrayRequest( AppConfig.URL_QUIZ,new Response.Listener<JSONArray>(){

      StringRequest strReq = new StringRequest( Request.Method.POST,
              AppConfig.URL_QUIZ, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//            public void onResponse(JSONObject response) {
                Log.d(TAG, "Response: " + response );
//                Log.d(TAG, "Response 1: " + response );

                hideDialog();

                try {
                    JSONObject jsonObj = new JSONObject( String.valueOf( response ) );
                    JSONArray ja_data = jsonObj.getJSONArray( "quiz");
//                    JSONArray result = ja_data.getJSONArray("quiz");
                    boolean error = ja_data.getBoolean(0);

                    int length = ja_data.length();

//                    Log.d("JSONResponse", response.toString(4));
//                    boolean error = response.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        for(int i=0; i<length; i++){
                            JSONObject jObj = ja_data.getJSONObject(i);
//                        ArrayList<>
                            int category_id = jObj.getInt("category_id");
                            int question_id = jObj.getInt("question_id");
                            String question = jObj.getString("question");
                            String option1 = jObj.getString("option1");
                            String option2 = jObj.getString("option2");
                            String option3 = jObj.getString("option3");
                            String option4 = jObj.getString("option4");
                            int answer = jObj.getInt("answer");

                            Question questions = new Question( question, option1, option2, option3, option4, answer, category_id  );
                            db.addQuestion(questions);
                        }
//                        JSONObject quiz = ja_data.getJSONObject("quiz");
//                        int category_id = quiz.getInt("category_id");
//                        int question_id = quiz.getInt("question_id");
//                        String question = quiz.getString("question");
//                        String option1 = quiz.getString("option1");
//                        String option2 = quiz.getString("option2");
//                        String option3 = quiz.getString("option3");
//                        String option4 = quiz.getString("option4");
//                        int answer = quiz.getInt("answer");

//                        // Inserting row in question table
//                            Question questions = new Question( question, option1, option2, option3, option4, answer, category_id  );
//                            db.addQuestion(questions);

                        // Launch main activity
                        Intent intent = new Intent(LessonActivity.this, StartQuizActivity.class);
                        intent.putExtra( EXTRA_CATEGORY_ID, category_id);
                        intent.putExtra( EXTRA_CATEGORY_NAME, String.valueOf( getResources().getStringArray(R.array.category)[category_id-1]));
                        startActivity( intent );

                        finish();
                    } else {
                        // Error in login. Get the error message
                                String errorMsg = ja_data.getString(1);
                                Toast.makeText(getApplicationContext(),
                                        errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Lesson Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("category_id", String.valueOf( category_id ) );
//                params.put("email", "HHKK");

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    private void showDialog(){
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog(){
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
