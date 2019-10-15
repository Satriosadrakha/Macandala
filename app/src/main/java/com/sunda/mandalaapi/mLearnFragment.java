package com.sunda.mandalaapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.sunda.mandalaapi.DatabaseHelper.DatabaseHelper;
import com.sunda.mandalaapi.Helper.SessionManager;
import com.sunda.mandalaapi.Model.Question;

import java.util.HashMap;

public class mLearnFragment extends Fragment {
    private String mText;
    private int mColor;

    private View mContent;
    private ScrollView mScrollView;

    private DatabaseHelper db;
    private SessionManager session;


    public static final String LESSON_ID = "lessonID";

    public static Fragment newInstance(String text, int color) {
        Fragment frag = new mLearnFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_m_learn, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ImageView profileImage;
        super.onViewCreated(view, savedInstanceState);

        // SqLite database handler
        db = new DatabaseHelper(getActivity().getApplicationContext());

//        HashMap<String, String> user = db.getUserDetails();

//        int progress = user.get("progress");

        // initialize views
        mContent = view.findViewById(R.id.mLearnFragment);
        mScrollView = (ScrollView) view.findViewById(R.id.sView);

        view.setBackgroundResource(R.drawable.mandalabackground);

        final View aboutMandala = view.findViewById(R.id.aboutMandala);
        aboutMandala.setOnClickListener((View v) -> {
            Intent intent = new Intent(getActivity(), AboutActivity.class);
            startActivity(intent);
        });

        final View aksara = view.findViewById(R.id.aksara);
        aksara.setOnClickListener((View v)->{
            Intent intent = new Intent(getActivity(), LessonPlaceholderActivity.class);
            startActivity(intent);
        });

        Intent intent = new Intent(getActivity(), LessonActivity.class);

        final View basic1 = view.findViewById(R.id.basic_1);
        basic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(LESSON_ID, 0);
                startActivity(intent);
            }
        });

        final View basic2 = view.findViewById(R.id.basic_2);
        basic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(LESSON_ID, 1);
                //Toast.makeText(getApplicationContext(),String.valueOf(lessonID),Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        final View hewan = view.findViewById(R.id.hewan);
        hewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(LESSON_ID, 2);
                startActivity(intent);
            }
        });

        final View warna = view.findViewById(R.id.warna);
        warna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(LESSON_ID, 3);
                startActivity(intent);
            }
        });

        // session manager
        session = new SessionManager(getActivity().getApplicationContext());

        // Logout button click event

        Button btnLogout = (Button) getActivity().findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        Toast.makeText(getActivity().getApplicationContext(),"Log out telah berhasil", Toast.LENGTH_LONG).show();

        // Launching the login activity
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}