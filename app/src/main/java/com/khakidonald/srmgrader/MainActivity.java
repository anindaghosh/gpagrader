package com.khakidonald.srmgrader;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    List<Subject> subList;
    RecyclerView recyclerView;
    FloatingActionButton buttonC;
    Button buttonR;
    List <String> creds, grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_sgpa_calculate);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle(R.string.sgpaText);
        setSupportActionBar(toolbar);

        DrawerUtil.getDrawer(this, toolbar, 1);

//        String[] singleChoiceItems = getResources().getStringArray(R.array.dialog_choices);
//        int itemSelected = -1;
//        new MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog)
//                .setTitle("Choose your regulation")
//                .setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
//                        selectedItem = singleChoiceItems[selectedIndex];
//                    }
//                })
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        MainActivity.mBundle.putString("regulation", selectedItem);
//                    }
//                })
//                .show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subList = new ArrayList<>();

        for(int i = 0; i<15; i++)
        {
            subList.add(new Subject());
        }

        subAdapter adapter = new subAdapter(this, subList);
        recyclerView.setAdapter(adapter);

        buttonC = findViewById(R.id.fab);
        buttonR = findViewById(R.id.buttonR);

        buttonC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                creds = new ArrayList<>();
                grades = new ArrayList<>();
                int flag = 0;
                int count = 0;

                for (Subject s:subAdapter.subList) {
                    creds.add(s.getCredits());
                    grades.add(s.getGrade());
                }
                for (int i = 0 ; i < creds.size() ; i++) {
                    if((creds.get(i).equals("Select Credits") && !(grades.get(i).equals("Select Grade"))) || (!(creds.get(i).equals("Select Credits")) && grades.get(i).equals("Select Grade"))) {
                        flag++;
                    }
                    if(creds.get(i).equals("Select Credits") && grades.get(i).equals("Select Grade")) {
                        count++;
                    }
                }

                if(count == subAdapter.subList.size()) {
                    Snackbar.make(view, "No entries", Snackbar.LENGTH_LONG).setAnchorView(buttonC).show();
                }

                else if(flag!=0) {
                    Snackbar.make(view, "Missing entries", Snackbar.LENGTH_LONG).setAnchorView(buttonC).show();
                }

                else {
                    Intent intent = new Intent(MainActivity.this, sgpaResult.class);
                    intent.putStringArrayListExtra("credits", (ArrayList<String>) creds);
                    intent.putStringArrayListExtra("grades", (ArrayList<String>) grades);
                    startActivity(intent);
                }
            }
        });

        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(Subject s : subAdapter.subList) {
                    s.setSpinnerCredPos(0);
                    s.setSpinnerGradPos(0);
                }
                recyclerView.setAdapter(adapter);
            }
        });

    }

//    private void saveMap(Map<String, Object> inputMap) {
//        SharedPreferences pSharedPref = getApplicationContext().getSharedPreferences("MyVariables",
//                Context.MODE_PRIVATE);
//        if (pSharedPref != null) {
//            JSONObject jsonObject = new JSONObject(inputMap);
//            String jsonString = jsonObject.toString();
//            SharedPreferences.Editor editor = pSharedPref.edit();
//            editor.remove(mapKey).apply();
//            editor.putString(mapKey, jsonString);
//            editor.commit();
//        }
//    }
}
