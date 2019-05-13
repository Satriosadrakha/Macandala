package com.example.sprint2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.sprint2.DatabaseHelper.DatabaseHelper;
import com.example.sprint2.Model.Character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AksaraLessonActivity extends AppCompatActivity {
    DatabaseHelper db;

    // Array of strings for ListView Title
    String[] listviewTitle;// = new String[]{
//            "A", "I", "U", "é",
//            "O", "E", "EU", "BA",
//    };

    int[] listviewImage;// = new int[]{
//            R.drawable.a, R.drawable.i, R.drawable.u, R.drawable.e_,
//            R.drawable.o, R.drawable.e, R.drawable.eu, R.drawable.ba,
//    };

    String[] listviewShortDescription;// = new String[]{
//            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
//            "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_aksara_lesson );

        db = new DatabaseHelper(this);

        int lessonId = getIntent().getIntExtra("LESSON_ID", 0);
        Log.d("LessonID", String.valueOf( lessonId ) );



//        Word word1 = new Word("Ada", "a");
//        long word1_id = db.createWord(word1);
//        Character a = new Character("a");
//        long character1_id = db.createCharacter(1, a, new long[] { word1_id });
//        Log.d("Char", String.valueOf( db.getCharacter(1) ));
        //String[] character = new String[3];
        List<Character> character = new ArrayList<>(  );

        List<Character> chara = new ArrayList<Character>();
        chara = db.getAllCharacters();
        for(Character characterchara : chara) {
            Log.d("List", characterchara.getSunda());
        }

        Log.d("Char", db.getCharacterSunda(1) );

        int listnum = 3;
        int start;
        switch (lessonId) {
            case 0:
                start = 1;
                listnum = 7;
                listviewTitle = new String[listnum];
                listviewImage = new int[listnum];
                listviewShortDescription = new String[listnum];
                for (int i=1; i<=7; i++){
                    listviewTitle[i-start] = db.getCharacter( i ).getSunda();
                    listviewImage[i-start] = getResources().getIdentifier( listviewTitle[i-start], "drawable", getPackageName());
                    if (i == 2){
                        listviewImage[i-start] = getResources().getIdentifier( "e_", "drawable", getPackageName());
                    }
                    listviewShortDescription[i-start] = String.valueOf( getResources().getStringArray(R.array.shortdesc)[i-1] );
                }
                break;
            case 1:
                start = 8;
                listviewTitle = new String[listnum];
                listviewImage = new int[listnum];
                listviewShortDescription = new String[listnum];
                for (int i=8; i<=10; i++){
                    listviewTitle[i-start] = db.getCharacter( i ).getSunda();
                    listviewImage[i-start] = getResources().getIdentifier( listviewTitle[i-start], "drawable", getPackageName());
                    listviewShortDescription[i-start] = String.valueOf( getResources().getStringArray(R.array.shortdesc)[i-1] );
                    Log.d("Desc", listviewShortDescription[i-8]);
                    Log.d("Char", listviewTitle[i-8]);
                }
                break;
            case 2:
                start = 11;
                listviewTitle = new String[listnum];
                listviewImage = new int[listnum];
                listviewShortDescription = new String[listnum];
                for (int i=11; i<=13; i++){
                    listviewTitle[i-start] = db.getCharacter( i ).getSunda();
                    listviewImage[i-start] = getResources().getIdentifier( listviewTitle[i-start], "drawable", getPackageName());
                    listviewShortDescription[i-start] = String.valueOf( getResources().getStringArray(R.array.shortdesc)[i-1] );
                }
                break;
            case 3:
                start = 14;
                listviewTitle = new String[listnum];
                listviewImage = new int[listnum];
                listviewShortDescription = new String[listnum];
                for (int i=14; i<=16; i++){
                    listviewTitle[i-start] = db.getCharacter( i ).getSunda();
                    listviewImage[i-start] = getResources().getIdentifier( listviewTitle[i-start], "drawable", getPackageName());
                    listviewShortDescription[i-start] = String.valueOf( getResources().getStringArray(R.array.shortdesc)[i-1] );
                }
                break;
            case 4:
                start = 17;
                listviewTitle = new String[listnum];
                listviewImage = new int[listnum];
                listviewShortDescription = new String[listnum];
                for (int i=17; i<=19; i++){
                    listviewTitle[i-start] = db.getCharacter( i ).getSunda();
                    listviewImage[i-start] = getResources().getIdentifier( listviewTitle[i-start], "drawable", getPackageName());
                    listviewShortDescription[i-start] = String.valueOf( getResources().getStringArray(R.array.shortdesc)[i-1] );
                }
                break;
            case 5:
                start = 20;
                listviewTitle = new String[listnum];
                listviewImage = new int[listnum];
                listviewShortDescription = new String[listnum];
                for (int i=20; i<=22; i++){
                    listviewTitle[i-start] = db.getCharacter( i ).getSunda();
                    listviewImage[i-start] = getResources().getIdentifier( listviewTitle[i-start], "drawable", getPackageName());
                    listviewShortDescription[i-start] = String.valueOf( getResources().getStringArray(R.array.shortdesc)[i-1] );
                }
                break;
            case 6:
                start = 23;
                listviewTitle = new String[listnum];
                listviewImage = new int[listnum];
                listviewShortDescription = new String[listnum];
                for (int i=23; i<=25; i++){
                    listviewTitle[i-start] = db.getCharacter( i ).getSunda();
                    listviewImage[i-start] = getResources().getIdentifier( listviewTitle[i-start], "drawable", getPackageName());
                    listviewShortDescription[i-start] = String.valueOf( getResources().getStringArray(R.array.shortdesc)[i-1] );
                }
                break;
            case 7:
                listnum = 5;
                start = 26;
                listviewTitle = new String[listnum];
                listviewImage = new int[listnum];
                listviewShortDescription = new String[listnum];
                for (int i=26; i<=30; i++){
                    listviewTitle[i-start] = db.getCharacter( i ).getSunda();
                    listviewImage[i-start] = getResources().getIdentifier( listviewTitle[i-start], "drawable", getPackageName());
                    listviewShortDescription[i-start] = String.valueOf( getResources().getStringArray(R.array.shortdesc)[i-1] );
                }
                break;
        }

        TextView aksaraLessontext1 = (TextView)findViewById(R.id.textView1);
        TextView aksaraLessontext2 = (TextView)findViewById(R.id.textView2);

        //long currentTimeStamp = System.currentTimeMillis();
        //lastMsg.setText(getResources().getStringArray(R.string.arrayLesson1));
        String[] text1 = getResources().getStringArray(R.array.aksaraLesson1);
        String[] text2 = getResources().getStringArray(R.array.aksaraLesson2);

        aksaraLessontext1.setText(text1[lessonId]);
        aksaraLessontext2.setText(text2[lessonId]);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < listnum; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_description", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_description"};
        int[] to = {R.id.listrow_image, R.id.listrow_sunda, R.id.listrow_contoh};

//        ImageButton sound = (ImageButton) this.findViewById(R.id.listrow_sound);
//        sound.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v) {
//                final MediaPlayer mp = (MediaPlayer) MediaPlayer.create(getApplicationContext(), getResources().getIdentifier("a","raw", getPackageName()));
//                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override public void onCompletion(MediaPlayer mp) {
//                        mp.reset();
//                        mp.release();
//                        mp=null;
//                    }
//                });
//                mp.start();
//            }
//        });

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.list_row, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_row);
        androidListView.setAdapter(simpleAdapter);
    }
}
