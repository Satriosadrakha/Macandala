package com.sunda.mandalaapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setTitle( "Tentang Kami" );
        setContentView( R.layout.activity_about );
    }
}
