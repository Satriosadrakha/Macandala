package com.sunda.mandalaapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.sunda.mandalaapi.DatabaseHelper.DatabaseHelper;
import com.sunda.mandalaapi.Helper.SessionManager;
import com.sunda.mandalaapi.Model.Character;

import java.util.HashMap;

public class MenuActivity extends AppCompatActivity {
//    private TextView txtName;
//    private TextView txtEmail;
//    private Button btnLogout;

    private DatabaseHelper db;
    private SessionManager session;

    private long backPressedTime;
    private static final String SELECTED_ITEM = "arg_selected_item";

    private BottomNavigationView mBottomNav;
    private int mSelectedItem;


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.profilebutton,menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//            case R.id.openBrowser:
//                Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
//                startActivity(intent);
//                return true;
//        }
//        return false;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle( "Beranda" );
        setContentView(R.layout.activity_menu);

//        txtName = (TextView) findViewById(R.id.name);
//        txtEmail = (TextView) findViewById(R.id.email);
//        btnLogout = (Button) findViewById(R.id.btnLogout);

        // SqLite database handler
        db = new DatabaseHelper(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        Log.d("isName", "Name: " + name.toString());
        String email = user.get("email");
        Log.d("isEmail", "Email: " + email.toString());

        // Displaying the user details on the screen
//        TextView txtName = (TextView) findViewById(R.id.name);
//        TextView txtEmail = (TextView) findViewById(R.id.email);
//        txtName.setText(name);
//        txtEmail.setText(email);

        // Logout button click event
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                logoutUser();
//            }
//        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            db = new DatabaseHelper(this);
            String swara[] = getResources().getStringArray(R.array.swara);
            String ngalagena[] = getResources().getStringArray(R.array.ngalagena);

            for (int i=0; i<7; i++){
                db.createCharacter(new Character(swara[i]));
            }
            for (int i=0; i<23; i++){
                db.createCharacter(new Character(ngalagena[i]));
            }
            db.closeDB();

            // mark first time has ran.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }

        mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNav.setOnNavigationItemSelectedListener((@NonNull MenuItem item)-> {
            selectFragment(item);
            return true;
        });
        /*mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });*/
        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = mBottomNav.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = mBottomNav.getMenu().getItem(0);
        }
        selectFragment(selectedItem);
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mSelectedItem);
        super.onSaveInstanceState(outState);
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

    private void selectFragment(MenuItem item) {
        Fragment frag = null;
        // init corresponding fragment
        switch (item.getItemId()) {
            case R.id.menu_home:
                /*frag = MandalaFragment.newInstance(getString(R.string.text_home),
                        getColorFromRes(R.color.color_home));*/
                frag = new mLearnFragment();

                break;
            case R.id.menu_notifications:
                frag = mLearnFragment.newInstance(getString(R.string.text_notifications),
                        getColorFromRes(R.color.color_notifications));
                break;
            case R.id.menu_search:
                frag = mLearnFragment.newInstance(getString(R.string.text_search),
                        getColorFromRes(R.color.color_search));
                break;
        }
        // update selected item
        mSelectedItem = item.getItemId();

        // uncheck the other items.
        for (int i = 0; i< mBottomNav.getMenu().size(); i++) {
            MenuItem menuItem = mBottomNav.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

        updateToolbarText(item.getTitle());

        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container, frag, frag.getTag());
            ft.commit();
        }
    }

    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }

    private int getColorFromRes(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
    }

}
