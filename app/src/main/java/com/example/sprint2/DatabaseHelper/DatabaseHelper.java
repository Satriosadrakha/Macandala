package com.example.sprint2.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sprint2.Model.Category;
import com.example.sprint2.Model.Character;
import com.example.sprint2.Model.Word;
import com.example.sprint2.Model.Question;
import com.example.sprint2.QuizContract.*;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "mandala.db";

    private static DatabaseHelper instance;

    private SQLiteDatabase db;

    // Table Names
    private static final String TABLE_CHARACTER = "characters";
    private static final String TABLE_WORD = "words";
    private static final String TABLE_WORD_CHARACTER = "wordcharacters";
    //private static final String TABLE_CATEGORY = "categories";
    private static final String TABLE_LESSON = "lessons";
    //private static final String TABLE_TODO_TAG = "todo_tags";

    // Common column names
    private static final String KEY_ID = "id";
    //private static final String KEY_CREATED_AT = "created_at";

    // characters Table - column names
    private static final String KEY_SUNDA = "sunda";
    private static final String KEY_AKSARA = "character_aksara";
    private static final String KEY_VOCAL = "character_vocal";

    // words Table - column names
    private static final String KEY_WORD_BAHASA = "word_bahasa";
    private static final String KEY_WORD_SUNDA = "word_sunda";

    // wordcharacters Table - column names
    private static final String KEY_ORDER = "wordcharacter_order";
    private static final String KEY_WORD_ID = "word_id";
    private static final String KEY_CHARACTER_ID = "character_id";

    // categories Table - column names
    //private static final String KEY_CATEGORY_NAME = "name";

    // lessons Table - column names
    private static final String KEY_CATEGORY_ID = "category_id";

    // Table Create Statements

    // Characters table create statement
    //private static final String CREATE_TABLE_CHARACTER = "CREATE TABLE words (id, sunda)";

    private static final String CREATE_TABLE_CHARACTER = "CREATE TABLE "
            + TABLE_CHARACTER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SUNDA
            + " TEXT" + ")";
//    private static final String CREATE_TABLE_CHARACTER = "CREATE TABLE "
//            + TABLE_CHARACTER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SUNDA
//            + " TEXT," + KEY_AKSARA + " TEXT," + KEY_VOCAL + " TEXT" + ")";

    // Words table create statement
    private static final String CREATE_TABLE_WORD = "CREATE TABLE "
            + TABLE_WORD + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_WORD_BAHASA
            + " TEXT," + KEY_WORD_SUNDA + " TEXT" + ")";

    // WordCharacters table create statement
    private static final String CREATE_TABLE_WORD_CHARACTER = "CREATE TABLE "
            + TABLE_WORD_CHARACTER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ORDER
            + " INTEGER," + KEY_WORD_ID + " INTEGER," + KEY_CHARACTER_ID + " INTEGER" + ")";

    // Categories table create statement
    // static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY
    //       + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CATEGORY_NAME + " TEXT" + ")";

    // Lessons table create statement
    private static final String CREATE_TABLE_LESSON = "CREATE TABLE " + TABLE_LESSON
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CATEGORY_ID + " INTEGER" + ")";

    // todo_tag table create statement
    /*private static final String CREATE_TABLE_TODO_TAG = "CREATE TABLE "
            + TABLE_TODO_TAG + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TODO_ID + " INTEGER," + KEY_TAG_ID + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";
    */

    final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
            CategoriesTable.TABLE_NAME  + "(" +
            CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CategoriesTable.COLUMN_NAME + " TEXT" +
            ")";

    final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
            QuestionTable.TABLE_NAME + " ( " +
            QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuestionTable.COLUMN_QUESTION + " TEXT," +
            QuestionTable.COLUMN_OPTION1 + " TEXT," +
            QuestionTable.COLUMN_OPTION2 + " TEXT," +
            QuestionTable.COLUMN_OPTION3 + " TEXT," +
            QuestionTable.COLUMN_OPTION4 + " TEXT," +
            QuestionTable.COLUMN_ANSWER_NR + " INTEGER," +
            QuestionTable.COLUMN_CATEGORY_ID + " INTEGER," +
            "FOREIGN KEY(" + QuestionTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
            CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    public static synchronized DatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHelper( context.getApplicationContext() );
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CHARACTER);
        db.execSQL(CREATE_TABLE_WORD);
        db.execSQL(CREATE_TABLE_WORD_CHARACTER);

        //db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_LESSON);

        this.db = db;

        db.execSQL( SQL_CREATE_CATEGORIES_TABLE );
        db.execSQL( SQL_CREATE_QUESTION_TABLE );
        fillCategoriesTable();
        fillQuestionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARACTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORD_CHARACTER);

        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LESSON);

        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure( db );
        db.setForeignKeyConstraintsEnabled( true );
    }

    private void fillCategoriesTable(){
//        Category c3 = new Category( "Hewan" );
//        addCategory( c3 );

        addCategory( new Category( "Basic11" ) );
        addCategory( new Category( "Basic12" ) );
        addCategory( new Category( "Basic21" ) );
        addCategory( new Category( "Basic22" ) );
        addCategory( new Category( "Hewan1" ) );
        addCategory( new Category( "Hewan2" ) );
        addCategory( new Category( "Warna1" ) );
        addCategory( new Category( "Warna2" ) );
        addCategory( new Category( "Angka1" ) );
        addCategory( new Category( "Angka2" ) );
    }

    private void addCategory(Category category){
        ContentValues cv = new ContentValues(  );
        cv.put( CategoriesTable.COLUMN_NAME, category.getNama() );
        db.insert( CategoriesTable.TABLE_NAME, null, cv );
    }

    private void fillQuestionTable(){
        addQuestion( new Question(
                "BASIC 1 LESSON 1: A is correct",
                "A", "B", "C", "D",
                1,
                1  ) );

        addQuestion( new Question(
                "BASIC 1 LESSON 1: C is correct",
                "A", "B", "C", "D",
                3,
                1  ) );

        addQuestion( new Question(
                "BASIC 1 LESSON 2: B is correct",
                "A", "B", "C", "D",
                2,
                2  ) );

        addQuestion( new Question(
                "BASIC 1 LESSON 2: D is correct",
                "A", "B", "C", "D",
                4,
                2  ) );

        addQuestion( new Question(
                "BASIC 2 LESSON 1: A is correct",
                "A", "B", "C", "D",
                1,
                3  ) );

        addQuestion( new Question(
                "BASIC 2 LESSON 2: A is correct",
                "A", "B", "C", "D",
                1,
                4  ) );

//        Question q1 = new Question( "BASIC 1: A is correct",
//                "A", "B", "C",
//                1, Category.BASIC1  );
//        addQuestion( q1 );
//        Question q2 = new Question( "Non Existing: B is correct",
//                "A", "B", "C",
//                2, 4  );
//        addQuestion( q2 );
//        Question q3 = new Question( "BASIC 2: B is correct",
//                "A", "B", "C",
//                2, Category.BASIC2  );
//        addQuestion( q3 );
//        Question q4 = new Question( "HEWAN: C is correct",
//                "A", "B", "C",
//                3, Category.HEWAN  );
//        addQuestion( q4 );
//        Question q5 = new Question( "Should be BASIC 1: A is correct",
//                "A", "B", "C",
//                1, 1  );
//        addQuestion( q5 );
    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues(  );
        cv.put( QuestionTable.COLUMN_QUESTION, question.getQuestion() );
        cv.put( QuestionTable.COLUMN_OPTION1, question.getOption1() );
        cv.put( QuestionTable.COLUMN_OPTION2, question.getOption2() );
        cv.put( QuestionTable.COLUMN_OPTION3, question.getOption3() );
        cv.put( QuestionTable.COLUMN_OPTION4, question.getOption4() );
        cv.put( QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr() );
        cv.put( QuestionTable.COLUMN_CATEGORY_ID, question.getCategoryID() );
        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories(){
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery( "SELECT * FROM " + CategoriesTable.TABLE_NAME, null );

        if(c.moveToFirst()){
            do{
                Category category = new Category(  );
                category.setId( c.getInt( c.getColumnIndex( CategoriesTable._ID ) ) );
                category.setNama( c.getString( c.getColumnIndex( CategoriesTable.COLUMN_NAME ) ) );
                categoryList.add(category);
            } while(c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    public List<Question> getAllQuestions(){
        List<Question> questionList = new ArrayList<>(  );
        db = getReadableDatabase();
        Cursor c = db.rawQuery( "SELECT * FROM " + QuestionTable.TABLE_NAME, null );

        if(c.moveToFirst()){
            do{
                Question question = new Question(  );
                question.setId( c.getInt( c.getColumnIndex( QuestionTable._ID ) ) );
                question.setQuestion( c.getString( c.getColumnIndex( QuestionTable.COLUMN_QUESTION ) ) );
                question.setOption1( c.getString( c.getColumnIndex( QuestionTable.COLUMN_OPTION1 ) ) );
                question.setOption2( c.getString( c.getColumnIndex( QuestionTable.COLUMN_OPTION2 ) ) );
                question.setOption3( c.getString( c.getColumnIndex( QuestionTable.COLUMN_OPTION3 ) ) );
                question.setOption4( c.getString( c.getColumnIndex( QuestionTable.COLUMN_OPTION4 ) ) );
                question.setAnswerNr( c.getInt( c.getColumnIndex( QuestionTable.COLUMN_ANSWER_NR ) ) );
                question.setCategoryID( c.getInt( c.getColumnIndex( QuestionTable.COLUMN_CATEGORY_ID) ) );
                questionList.add(question);
            } while(c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID){
        ArrayList<Question> questionList = new ArrayList<>(  );
        db = getReadableDatabase();

        String selection = QuestionTable.COLUMN_CATEGORY_ID + " = ? ";
        String[] selectionArgs = new String[]{
                String.valueOf( categoryID )
        };

        Cursor c = db.query(
                QuestionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if(c.moveToFirst()){
            do{
                Question question = new Question(  );
                question.setId( c.getInt( c.getColumnIndex( QuestionTable._ID ) ) );
                question.setQuestion( c.getString( c.getColumnIndex( QuestionTable.COLUMN_QUESTION ) ) );
                question.setOption1( c.getString( c.getColumnIndex( QuestionTable.COLUMN_OPTION1 ) ) );
                question.setOption2( c.getString( c.getColumnIndex( QuestionTable.COLUMN_OPTION2 ) ) );
                question.setOption3( c.getString( c.getColumnIndex( QuestionTable.COLUMN_OPTION3 ) ) );
                question.setOption4( c.getString( c.getColumnIndex( QuestionTable.COLUMN_OPTION4 ) ) );
                question.setAnswerNr( c.getInt( c.getColumnIndex( QuestionTable.COLUMN_ANSWER_NR ) ) );
                question.setCategoryID( c.getInt( c.getColumnIndex( QuestionTable.COLUMN_CATEGORY_ID) ) );
                questionList.add(question);
            } while(c.moveToNext());
        }
        c.close();
        return questionList;
    }

    // --------------------------------todo table method --------------------------------------------//

    /* Creating a todo */
    public long createCharacter(Character character) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SUNDA, character.getSunda());
        //values.put(KEY_AKSARA, character.getAksara());
        //values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long character_id = db.insert(TABLE_CHARACTER, null, values);

        return character_id;
    }

    /* Creating a todo */
    public long createCharacter(int wordcharacter_order, Character character, long[] word_ids) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SUNDA, character.getSunda());
        //values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long character_id = db.insert(TABLE_CHARACTER, null, values);

        // assigning tags to todo
        for (long word_id : word_ids) {
            createWordCharacter(wordcharacter_order, character_id, word_id);
        }

        return character_id;
    }

    /* get single todo */
    public Character getCharacter(long character_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_CHARACTER + " WHERE "
                + KEY_ID + " = " + character_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Character td = new Character();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setSunda(c.getString(c.getColumnIndex(KEY_SUNDA)));
        //td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return td;
    }

    public String getCharacterSunda(long character_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_CHARACTER + " WHERE "
                + KEY_ID + " = " + character_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Character td = new Character();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setSunda(c.getString(c.getColumnIndex(KEY_SUNDA)));
        //td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return td.getSunda();
    }

    /* getting all todos */
    public List<Character> getAllCharacters() {
        List<Character> characters = new ArrayList<Character>();
        String selectQuery = "SELECT  * FROM " + TABLE_CHARACTER;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Character td = new Character();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setSunda((c.getString(c.getColumnIndex(KEY_SUNDA))));
                //td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                // adding to todo list
                characters.add(td);
            } while (c.moveToNext());
        }

        return characters;
    }

    /* getting all todos under single tag */

    public List<Character> getAllCharactersByWord(String sunda) {
        List<Character> characters = new ArrayList<Character>();

        String selectQuery = "SELECT * FROM " + TABLE_CHARACTER + " tc, "
                + TABLE_WORD + " tw, " + TABLE_WORD_CHARACTER + " twc WHERE tw."
                + KEY_WORD_SUNDA + " = '" + sunda + "'" + " AND tw." + KEY_ID
                + " = " + "twc." + KEY_WORD_ID + " AND tc." + KEY_ID + " = "
                + "twc." + KEY_CHARACTER_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Character td = new Character();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setSunda((c.getString(c.getColumnIndex(KEY_SUNDA))));
                //td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                // adding to todo list
                characters.add(td);
            } while (c.moveToNext());
        }

        return characters;
    }

    /**
     * getting character count
     */
    public int getCharacterCount() {
        String countQuery = "SELECT * FROM " + TABLE_CHARACTER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /* Updating a todo */
    public int updateCharacter(Character character) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SUNDA, character.getSunda());
        //values.put(KEY_STATUS, todo.getStatus());

        // updating row
        return db.update(TABLE_CHARACTER, values, KEY_ID + " = ?",
                new String[] { String.valueOf(character.getId()) });
    }

    /* Deleting a todo */
    public void deleteCharacter(long character_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CHARACTER, KEY_ID + " = ?",
                new String[] { String.valueOf(character_id) });
    }

    // NOW, to TAG / Second Table

    /* Creating tag */

    public long createWord(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD_BAHASA, word.getBahasa());
        values.put(KEY_WORD_SUNDA, word.getSunda());
        //values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long word_id = db.insert(TABLE_WORD, null, values);

        return word_id;
    }

    /**
     * getting all tags
     * */
    public List<Word> getAllWords() {
        List<Word> words = new ArrayList<Word>();
        String selectQuery = "SELECT * FROM " + TABLE_WORD;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Word t = new Word();
                t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                t.setBahasa(c.getString(c.getColumnIndex(KEY_WORD_BAHASA)));
                t.setSunda(c.getString(c.getColumnIndex(KEY_WORD_SUNDA)));

                // adding to tags list
                words.add(t);
            } while (c.moveToNext());
        }
        return words;
    }

    /* Updating a tag */
    public int updateWord(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD_BAHASA, word.getBahasa());

        // updating row
        return db.update(TABLE_WORD, values, KEY_ID + " = ?",
                new String[] { String.valueOf(word.getId()) });
    }

    /* Deleting a tag */
    public void deleteWord(Word word, boolean should_delete_all_word_characters) {
        SQLiteDatabase db = this.getWritableDatabase();

        // before deleting word
        // check if characters under this word should also be deleted
        if (should_delete_all_word_characters) {
            // get all characters under this word
            List<Character> allWordCharacters = getAllCharactersByWord(word.getBahasa());

            // delete all characters
            for (Character character: allWordCharacters) {
                // delete character
                deleteCharacter(character.getId());
            }
        }

        // now delete the word
        db.delete(TABLE_WORD, KEY_ID + " = ?",
                new String[] { String.valueOf(word.getId()) });
    }

    //Assign a Word to Character

    public long createWordCharacter(int wordcharacter_order, long character_id, long word_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ORDER, wordcharacter_order);
        values.put(KEY_CHARACTER_ID, character_id);
        values.put(KEY_WORD_ID, word_id);
        //values.put(KEY_CREATED_AT, getDateTime());

        long id = db.insert(TABLE_WORD_CHARACTER, null, values);

        return id;
    }

    //Remove word assigned to a character
    public int updateBahasaWord(long id, long word_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD_ID, word_id);

        // updating row
        return db.update(TABLE_CHARACTER, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

//    public long createCategory(Category category) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_CATEGORY_NAME, category.getNama());
//        //values.put(KEY_CREATED_AT, getDateTime());
//
//        // insert row
//        long category_id = db.insert(TABLE_CATEGORY, null, values);
//
//        return category_id;
//    }

    /**
     * getting all tags
     * */
//    public List<Category> getAllCategories() {
//        List<Category> categories = new ArrayList<Category>();
//        String selectQuery = "SELECT * FROM " + TABLE_CATEGORY;
//
//        Log.e(LOG, selectQuery);
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (c.moveToFirst()) {
//            do {
//                Category t = new Category();
//                t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
//                t.setNama(c.getString(c.getColumnIndex(KEY_CATEGORY_NAME)));
//
//                // adding to tags list
//                categories.add(t);
//            } while (c.moveToNext());
//        }
//        return categories;
//    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }




}
