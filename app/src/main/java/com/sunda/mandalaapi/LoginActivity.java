package com.sunda.mandalaapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sunda.mandalaapi.DatabaseHelper.DatabaseHelper;
import com.sunda.mandalaapi.Helper.SessionManager;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private EditText inputEmail, inputPassword;
//    private FirebaseAuth auth;
//    private ProgressBar progressBar;
    private Button btnLinkToRegister, btnLogin, btnReset;
    private ProgressDialog pDialog;
    private SessionManager session;
    private DatabaseHelper db;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        //Get Firebase auth instance
//        auth = FirebaseAuth.getInstance();

//        if (auth.getCurrentUser() != null) {
//            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
//            finish();
//        }

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        inputEmail = (EditText) findViewById( R.id.email );
        inputPassword = (EditText) findViewById( R.id.password );
//        progressBar = (ProgressBar) findViewById( R.id.progressBar );
        btnLinkToRegister = (Button) findViewById( R.id.btnLinkToRegister );
        btnLogin = (Button) findViewById( R.id.btn_login );
        btnReset = (Button) findViewById( R.id.btn_reset_password );

        //Get Firebase auth instance
//        auth = FirebaseAuth.getInstance();

        // Progress dialog
        pDialog = new ProgressDialog( this );
        pDialog.setCancelable( false );

        // SQLite database handler
        db = new DatabaseHelper( getApplicationContext() );

        // Session manager
        session = new SessionManager( getApplicationContext() );

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent( LoginActivity.this, MenuActivity.class );
            startActivity( intent );
            finish();
        }

        // Link to Register Screen
        btnLinkToRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                startActivity( new Intent( getApplicationContext(), RegisterActivity.class ) );
            }
        } );

//        btnReset.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity( new Intent( LoginActivity.this, ResetPasswordActivity.class ) );
//            }
//        } );

        // Login button Click Event
        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
//                final String password = inputPassword.getText().toString();

                // Check for empty data in the form
//                if (!email.isEmpty() && !password.isEmpty()) {
//                    // login user
//                    checkLogin(email, password);
//                } else {
//                    // Prompt user to enter credentials
//                    Toast.makeText(getApplicationContext(),
//                            "Please enter the credentials!", Toast.LENGTH_LONG)
//                            .show();
//                }

                if (TextUtils.isEmpty( email )) {
                    Toast.makeText( getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT ).show();
                    return;
                }

                if (TextUtils.isEmpty( password )) {
                    Toast.makeText( getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT ).show();
                    return;
                }

//                progressBar.setVisibility( View.VISIBLE );

                checkLogin( email, password );
                //authenticate user
//                auth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                // If sign in fails, display a message to the user. If sign in succeeds
//                                // the auth state listener will be notified and logic to handle the
//                                // signed in user can be handled in the listener.
//                                progressBar.setVisibility(View.GONE);
//                                if (!task.isSuccessful()) {
//                                    // there was an error
//                                    if (password.length() < 6) {
//                                        inputPassword.setError(getString(R.string.minimum_password));
//                                    } else {
//                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
//                                    }
//                                } else {
//                                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            }
//                        });
            }
        } );
    }

    /**
     * function to verify login details in mysql db
     * */
    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Sedang Login ...");
        showDialog();

        StringRequest strReq = new StringRequest( Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    Log.d("JSONResponse", jObj.toString(4));
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session
                        session.setLogin(true);

                        // Now store the user in SQLite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
//                        String progress = user.getString("progress");
                        String created_at = user.getString("created_at");

                        // Inserting row in users table
                        db.addUser(name, email, uid, created_at);

                        // Launch main activity
                        Intent intent = new Intent(LoginActivity.this,
                                MenuActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
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
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

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

    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()){
            moveTaskToBack(true);
            finish();
        } else{
            Toast.makeText( this, "Tekan kembali untuk keluar dari aplikasi", Toast.LENGTH_SHORT ).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}
