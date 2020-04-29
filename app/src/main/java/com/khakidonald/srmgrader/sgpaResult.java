package com.khakidonald.srmgrader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class sgpaResult<total_credits> extends AppCompatActivity {

    int total_credits = 0;
    float sgpa = 0;
    HashMap<String, Integer> gradVal = new HashMap<>();
    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_sgpa_result);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle(R.string.sgpaText);
        setSupportActionBar(toolbar);

        DrawerUtil.getDrawer(this, toolbar, -1);

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//
//        AdView adView = (AdView)this.findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

        ArrayList<String> credits = getIntent().getStringArrayListExtra("credits");
        ArrayList<String> grades = getIntent().getStringArrayListExtra("grades");

        gradVal.put("O", 10);
        gradVal.put("A+", 9);
        gradVal.put("A", 8);
        gradVal.put("B+", 7);
        gradVal.put("B", 6);
        gradVal.put("C", 5);
        gradVal.put("P", 4);
        gradVal.put("F", 0);
        gradVal.put("AB", 0);
        gradVal.put("I", 0);

        for (int i = 0; i < credits.size(); i++) {

            if (!credits.get(i).equals("Select Credits") && !grades.get(i).equals("Select Grade")) {
                if (gradVal.get(grades.get(i))!=0 || grades.get(i).equals("F")) {
                    int creditVal = Integer.parseInt(credits.get(i));
                    sgpa += creditVal * gradVal.get(grades.get(i));
                    total_credits += creditVal;
                }
            }
        }

        sgpa = sgpa / total_credits;
        System.out.println("SGPA: "+ sgpa);

        TextView totCredits, sgpaT;
        sgpaT = findViewById(R.id.textView14);
        totCredits = findViewById(R.id.textView12);

        sgpaT.setText(df.format(sgpa));
        if(sgpa>0) totCredits.setText(Integer.toString(total_credits));
        else totCredits.setText("0");
    }
}