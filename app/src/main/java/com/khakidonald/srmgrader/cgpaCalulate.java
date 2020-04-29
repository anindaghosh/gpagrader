package com.khakidonald.srmgrader;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class cgpaCalulate extends AppCompatActivity {

    List<Semester> semesterList;

    RecyclerView recyclerView;

    Button btnR;
    FloatingActionButton btnC;

    List <Integer> creds;
    List <Float> gpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_cgpa_calulate);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        DrawerUtil.getDrawer(this, toolbar, 2);

        recyclerView = findViewById(R.id.recyclerView);
        btnC = findViewById(R.id.fab);
        btnR = findViewById(R.id.button1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        semesterList = new ArrayList<>();

        for(int i = 1; i < 11 ; i ++) semesterList.add(new Semester("Semester "+i));

        cardAdapter adapter = new cardAdapter(this, semesterList);

        recyclerView.setAdapter(adapter);

        btnC.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                gpa = new ArrayList<>();
                creds = new ArrayList<>();
                int flag = 0;
                int flag1 = 0;
                int count = 0;

                for(Semester s:cardAdapter.semesterList) {
                    // System.out.println(s.getGpa() + " " + s.getCredits());
                    gpa.add(s.getGpa());
                    creds.add(s.getCredits());
                }

                System.out.println(gpa);
                System.out.println(creds);

                for(int i = 0 ; i < gpa.size() ; i++) {
                    if ((gpa.get(i) == 0 && creds.get(i) != 0) || (gpa.get(i) != 0 && creds.get(i) == 0)) {
                        flag++;
                        break;
                    }
                    if (gpa.get(i) == 0 && creds.get(i) == 0) {
                        count++;
                    }
                    if (gpa.get(i) > 10) {
                        flag1++;
                        break;
                    }
                }

                if(count == cardAdapter.semesterList.size()) {
                    Snackbar.make(view, "No entries", Snackbar.LENGTH_LONG).setAnchorView(btnC).show();
                }

                else if(flag!=0) {
                    Snackbar.make(view, "Missing entries", Snackbar.LENGTH_LONG).setAnchorView(btnC).show();
//                    for (Semester s : cardAdapter.semesterList) {
//                        s.setCredits(0);
//                        s.setGpa(0);
//                    }
                }

                else {
                    if(flag1!=0) {
                        Snackbar.make(view, "GPA cannot be greater than 10", Snackbar.LENGTH_LONG).setAnchorView(btnC).show();
//                        for (Semester s : cardAdapter.semesterList) {
//                            s.setCredits(0);
//                            s.setGpa(0);
//                        }
                    }
                    else {
                        Intent intent = new Intent(cgpaCalulate.this, cgpaResult.class);
                        startActivity(intent);
                    }
                }
            }
        });

        btnR.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                for(Semester s : cardAdapter.semesterList) {
                    s.setCredits(0);
                    s.setGpa(0);
                }
                recyclerView.setAdapter(adapter);

            }
        });

    }

}
