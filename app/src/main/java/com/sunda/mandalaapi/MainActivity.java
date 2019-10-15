package com.sunda.mandalaapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sunda.mandalaapi.DatabaseHelper.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button StartButton;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartButton = (Button) findViewById(R.id.startButton);
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

////////////////////////////////////        db = new DatabaseHelper(this);

        // Creating words
//        Word word1 = new Word("Ada", "Aya");
//        Word word2 = new Word("Siapa","Saha");
//        Word word3 = new Word("Rasanya","Asa");

        // Inserting tags in db
//        long word1_id = db.createWord(word1);
//        long word2_id = db.createWord(word2);
//        long word3_id = db.createWord(word3);

//        Log.d("Word Count", "Word Count: " + db.getAllWords().size());

////////////////////////////////////        String swara[] = getResources().getStringArray(R.array.swara);
////////////////////////////////////        String ngalagena[] = getResources().getStringArray(R.array.ngalagena);
        /*for (int i = 0; i < arr.length; i++) {
            Toast.makeText(getBaseContext(),arr[i], Toast.LENGTH_LONG).show();
        }*/
        // Creating ToDos
        //Character suaraa = new Character(swara[0]);
////////////////////////////////////        for (int i=0; i<7; i++){
////////////////////////////////////            db.createCharacter(new Character(swara[i]));
            //db.createCharacter(new Character(swara[i],swara[i],swara[i]));
            //Log.e("Character_Count", "Character count: " + db.getCharacterCount());
            //Log.d("SwaraChar", db.getCharacter( 1 ).getSunda() );
////////////////////////////////////        }
////////////////////////////////////        for (int i=0; i<23; i++){
////////////////////////////////////            db.createCharacter(new Character(ngalagena[i]));
            //Log.e("Character_Count", "Character count: " + db.getCharacterCount());
////////////////////////////////////        }
//        db.createCharacter(new Character(swara[0]));
//        Character suaraE = new Character(swara[1]);
//        Character suarai = new Character(swara[2]);
//        Character suarao = new Character(swara[3]);
//        Character suarau = new Character(swara[4]);
//        Character suarae = new Character(swara[5]);
//        Character suaraeu = new Character(swara[6]);
//
//        Character ka = new Character("ka");
//        Character ga = new Character("ga");
//        Character nga = new Character("nga");
//        Character ca = new Character("ca");
//        Character ja = new Character("ja");
//        Character nya = new Character("nya");
//        Character ta = new Character("ta");
//        Character da = new Character("da");
//        Character na = new Character("na");
//        Character pa = new Character("pa");
//        Character ba = new Character("ba");
//        Character ma = new Character("ma");
//        Character ya = new Character("ya");
//        Character ra = new Character("ra");
//        Character la = new Character("la");
//        Character wa = new Character("wa");
//        Character sa = new Character("sa");
//        Character ha = new Character("ha");
//        Character fa = new Character("fa");
//        Character qa = new Character("qa");
//        Character va = new Character("va");
//        Character xa = new Character("xa");
//        Character za = new Character("za");
//        Character kha = new Character("kha");
//        Character sya = new Character("sya");

        // Inserting characters in db
        // Inserting characters under "Aya" Tag
//        long character1_id = db.createCharacter(1, suaraa, new long[] { word1_id });
//        long character2_id = db.createCharacter(2, ya, new long[] { word1_id });

        // Inserting characters under "Saha" Tag
//        long character3_id = db.createCharacter(1, sa, new long[] { word2_id });
//        long character4_id = db.createCharacter(2, ha, new long[] { word2_id });

        // Inserting todos under "Asa" Tag
//        long character5_id = db.createCharacter(1, suaraa, new long[] { word3_id });
//        long character6_id = db.createCharacter(2, sa, new long[] { word3_id });

        //Log.e("Character Count", "Character count: " + db.getCharacterCount());

        // "Post new Article" - assigning this under "Important" Word
        // Now this will have - "Androidhive" and "Important" Tags
        //db.createTodoTag(todo10_id, tag2_id);

        // Getting all word names
//        Log.d("Get Words", "Getting All Words");
//        List<Word> allWords = db.getAllWords();
//        for (Word word : allWords) {
//            Log.d("Word", word.getSunda());
//        }

        // Getting all Todos
        /*
        Log.d("Get Todos", "Getting All ToDos");

        List<Todo> allToDos = db.getAllToDos();
        for (Todo todo : allToDos) {
            Log.d("ToDo", todo.getNote());
        }
        */

        // Getting characters under "Aya" word
//        Log.d("Character", "Get characters under single Word");
//
//        List<Character> wordAya = db.getAllCharactersByWord(word3.getSunda());
//        for (Character character : wordAya) {
//            Log.d("Character Aya", character.getSunda());
//        }

        /*
        // Deleting a ToDo
        Log.d("Delete ToDo", "Deleting a Todo");
        Log.d("Tag Count", "Tag Count Before Deleting: " + db.getToDoCount());

        db.deleteToDo(todo8_id);

        Log.d("Tag Count", "Tag Count After Deleting: " + db.getToDoCount());

        // Deleting all Todos under "Shopping" tag
        Log.d("Tag Count",
                "Tag Count Before Deleting 'Shopping' Todos: "
                        + db.getToDoCount());

        db.deleteTag(tag1, true);

        Log.d("Tag Count",
                "Tag Count After Deleting 'Shopping' Todos: "
                        + db.getToDoCount());

        // Updating tag name
        tag3.setTagName("Movies to watch");
        db.updateTag(tag3);
        */

        // Don't forget to close database connection
////////////////////////////////////        db.closeDB();

//        Button start = (Button) findViewById(R.id.start);
//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getBaseContext(), LessonPlaceholderActivity.class);
//                //intent.putExtra("CATEGORY_ID", 1);
//                startActivity(intent);
////                view.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()){
            moveTaskToBack(true);
            finish();
        } else{
            Toast.makeText( this, "Tekan kembali untuk selesai", Toast.LENGTH_SHORT ).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
