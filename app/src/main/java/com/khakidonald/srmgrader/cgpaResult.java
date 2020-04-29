package com.khakidonald.srmgrader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class cgpaResult extends AppCompatActivity {

    private int total_credits = 0;
    private float cgpa = 0;
    private static DecimalFormat df = new DecimalFormat("0.00");

    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_cgpa_result);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle(R.string.cgpaText);
        setSupportActionBar(toolbar);

        DrawerUtil.getDrawer(this, toolbar, -1);

        tv1 = findViewById(R.id.textView5);
        tv2 = findViewById(R.id.textView4);

        List<Float> sgpa = new ArrayList<>();
        List<Integer> credits = new ArrayList<>();

        for(int i = 0; i < cardAdapter.semesterList.size(); i++) {
            sgpa.add(cardAdapter.semesterList.get(i).getGpa());
            credits.add(cardAdapter.semesterList.get(i).getCredits());
            cgpa += cardAdapter.semesterList.get(i).getGpa() * cardAdapter.semesterList.get(i).getCredits();
            total_credits += cardAdapter.semesterList.get(i).getCredits();
        }

        cgpa /= total_credits;

        tv1.setText(df.format(cgpa));
        tv2.setText(String.valueOf(total_credits));
    }
}
