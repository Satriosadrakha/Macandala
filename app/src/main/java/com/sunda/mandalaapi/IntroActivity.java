package com.sunda.mandalaapi;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class IntroActivity extends AppCompatActivity {
    Button[] btnWord = new Button[7];
    Button[] btnNGL1 = new Button[9];
    Button[] btnNGL2 = new Button[9];
    Button[] btnNGL3 = new Button[5];
    Button[] btnRRK1 = new Button[6];
    Button[] btnRRK2 = new Button[7];

    LinearLayout linear, ngalagena1, ngalagena2, ngalagena3, rarangken1, rarangken2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setTitle( "Apa itu Aksara Sunda?" );
        setContentView( R.layout.activity_intro );

        //Set buttons in linear layout
        linear = (LinearLayout) findViewById(R.id.linear);
        for (int i = 0; i < btnWord.length; i++) {
            btnWord[i] = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            );
            btnWord[i].setLayoutParams(params);
            params.setMargins(3, 5, 3, 5);
            btnWord[i].setBackgroundResource( R.drawable.button_bg );
            btnWord[i].setTag(i);
            btnWord[i].setText( String.valueOf( getResources().getStringArray(R.array.swara)[i] ) );
            int finalI = i;
            btnWord[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object tag = v.getTag();
//                    Toast.makeText(getApplicationContext(), "clicked button", Toast.LENGTH_SHORT).show();
                    final MediaPlayer mp;
                    if(finalI==1){
                        mp = (MediaPlayer) MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(String.valueOf( "e_" ),"raw", getPackageName()));
                    } else{
                        mp = (MediaPlayer) MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(String.valueOf( getResources().getStringArray(R.array.swara)[finalI] ),"raw", getPackageName()));
                    }
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
            linear.addView(btnWord[i]);
        }

        ngalagena1 = (LinearLayout) findViewById(R.id.ngalagena1);
        for (int i = 0; i < btnNGL1.length; i++) {
            btnNGL1[i] = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            );
            btnNGL1[i].setLayoutParams(params);
            params.setMargins(3, 5, 3, 5);
            btnNGL1[i].setBackgroundResource( R.drawable.button_bg );
            btnNGL1[i].setTag(i);
            int finalI = i;
            btnNGL1[i].setText( String.valueOf( getResources().getStringArray(R.array.ngalagena)[finalI] ) );
            btnNGL1[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object tag = v.getTag();
//                    Toast.makeText(getApplicationContext(), "clicked button", Toast.LENGTH_SHORT).show();
                    final MediaPlayer mp = (MediaPlayer) MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(String.valueOf( getResources().getStringArray(R.array.ngalagena)[finalI] ),"raw", getPackageName()));
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
            ngalagena1.addView(btnNGL1[i]);
        }

        ngalagena2 = (LinearLayout) findViewById(R.id.ngalagena2);
        for (int i = 0; i < btnNGL2.length; i++) {
            btnNGL2[i] = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            );
            btnNGL2[i].setLayoutParams(params);
            params.setMargins(3, 5, 3, 5);
            btnNGL2[i].setBackgroundResource( R.drawable.button_bg );
            btnNGL2[i].setTag(i);
            int finalI = i+btnNGL2.length;
            btnNGL2[i].setText( String.valueOf( getResources().getStringArray(R.array.ngalagena)[finalI] ) );
            btnNGL2[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object tag = v.getTag();
//                    Toast.makeText(getApplicationContext(), "clicked button", Toast.LENGTH_SHORT).show();
                    final MediaPlayer mp = (MediaPlayer) MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(String.valueOf( getResources().getStringArray(R.array.ngalagena)[finalI] ),"raw", getPackageName()));
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
            ngalagena2.addView(btnNGL2[i]);
        }

        ngalagena3 = (LinearLayout) findViewById(R.id.ngalagena3);
        for (int i = 0; i < btnNGL3.length; i++) {
            btnNGL3[i] = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            );
            btnNGL3[i].setLayoutParams(params);
            params.setMargins(3, 5, 3, 5);
            btnNGL3[i].setBackgroundResource( R.drawable.button_bg );
            btnNGL3[i].setTag(i);
            int finalI = i + btnNGL2.length + btnNGL1.length;
            btnNGL3[i].setText( String.valueOf( getResources().getStringArray(R.array.ngalagena)[finalI] ) );
            btnNGL3[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object tag = v.getTag();
//                    Toast.makeText(getApplicationContext(), "clicked button", Toast.LENGTH_SHORT).show();
                    final MediaPlayer mp = (MediaPlayer) MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(String.valueOf( getResources().getStringArray(R.array.ngalagena)[finalI] ),"raw", getPackageName()));
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override public void onCompletion(MediaPlayer mp) {
                            mp.reset();
                            mp.release();
                            mp=null;
                        }
                    });
                    mp.start();
//                    Object tag = v.getTag();
//                    Toast.makeText(getApplicationContext(), "Suara belum tersedia", Toast.LENGTH_SHORT).show();
                }
            });
            ngalagena3.addView(btnNGL3[i]);
        }

        rarangken1 = (LinearLayout) findViewById(R.id.rarangken1);
        for (int i = 0; i < btnRRK1.length; i++) {
            btnRRK1[i] = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            );
            btnRRK1[i].setLayoutParams(params);
            params.setMargins(3, 5, 3, 5);
            btnRRK1[i].setBackgroundResource( R.drawable.button_bg );
            btnRRK1[i].setTag(i);
            int finalI = i;
            btnRRK1[i].setText( String.valueOf( getResources().getStringArray(R.array.rarangken)[finalI] ) );
            btnRRK1[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object tag = v.getTag();
//                    Toast.makeText(getApplicationContext(), "clicked button", Toast.LENGTH_SHORT).show();
                    final MediaPlayer mp;// = (MediaPlayer) MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(String.valueOf( getResources().getStringArray(R.array.rarangken)[finalI] ),"raw", getPackageName()));
                    if(finalI == 2){
                        mp = (MediaPlayer) MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(String.valueOf( "ke_" ),"raw", getPackageName()));
                    } else{
                        mp = (MediaPlayer) MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(String.valueOf( getResources().getStringArray(R.array.rarangken)[finalI] ),"raw", getPackageName()));
                    }
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override public void onCompletion(MediaPlayer mp) {
                            mp.reset();
                            mp.release();
                            mp=null;
                        }
                    });
                    mp.start();
//                    Object tag = v.getTag();
//                    Toast.makeText(getApplicationContext(), "Suara belum tersedia", Toast.LENGTH_SHORT).show();
                }
            });
            rarangken1.addView(btnRRK1[i]);
        }

        rarangken2 = (LinearLayout) findViewById(R.id.rarangken2);
        for (int i = 0; i < btnRRK2.length ; i++) {
            btnRRK2[i] = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            );
            btnRRK2[i].setLayoutParams(params);
            params.setMargins(3, 5, 3, 5);
            btnRRK2[i].setBackgroundResource( R.drawable.button_bg );
            btnRRK2[i].setTag(i);
            int finalI = i + btnRRK1.length;
            btnRRK2[i].setText( String.valueOf( getResources().getStringArray(R.array.rarangken)[finalI] ) );
            btnRRK2[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object tag = v.getTag();
//                    Toast.makeText(getApplicationContext(), "clicked button", Toast.LENGTH_SHORT).show();
                    if (finalI == 12){
                        Toast.makeText(getApplicationContext(), "Suara belum tersedia", Toast.LENGTH_SHORT).show();
                    } else{
                        final MediaPlayer mp;// = (MediaPlayer) MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(String.valueOf( getResources().getStringArray(R.array.rarangken)[finalI] ),"raw", getPackageName()));
                        mp = (MediaPlayer) MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(String.valueOf( getResources().getStringArray(R.array.rarangken)[finalI] ),"raw", getPackageName()));
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override public void onCompletion(MediaPlayer mp) {
                                mp.reset();
                                mp.release();
                                mp=null;
                            }
                        });
                        mp.start();
                    }
//                    Object tag = v.getTag();
//                    Toast.makeText(getApplicationContext(), "Suara belum tersedia", Toast.LENGTH_SHORT).show();
                }
            });
            rarangken2.addView(btnRRK2[i]);
        }
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
