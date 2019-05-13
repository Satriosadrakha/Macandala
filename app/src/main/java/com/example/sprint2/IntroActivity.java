package com.example.sprint2;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_intro );

        //GridView?
        //https://www.tutorialspoint.com/android/android_grid_view.htm
        //https://www.androidhive.info/2012/02/android-gridview-layout-tutorial/
        //Reduce PNG size
        //https://tinypng.com/
        //MediaPlayer mp;
        Button one = (Button) this.findViewById(R.id.button2);
        one.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                final MediaPlayer mp = (MediaPlayer) MediaPlayer.create(getApplicationContext(), getResources().getIdentifier("a","raw", getPackageName()));
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override public void onCompletion(MediaPlayer mp) {
                        mp.reset();
                        mp.release();
                        mp=null;
                    }
                });
                mp.start();
            }
        });
    }
}
