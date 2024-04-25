package com.example.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Units extends AppCompatActivity {

    String[] item = {"Computer Science", "Information Technology"};
    String[] item1 = {"Computer Security", "Computer Programming"};
    String[] item2 = {"Software Project", "Human Computer Interaction"};
    String[] item3 = {"Data Analysis", "Principle of programming languages"};

    private Button saveBtn;


    AutoCompleteTextView autoCompleteTextView;
    AutoCompleteTextView autoCompleteTextView1;
    AutoCompleteTextView autoCompleteTextView2;
    AutoCompleteTextView autoCompleteTextView3;

    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterItems1;
    ArrayAdapter<String> adapterItems2;
    ArrayAdapter<String> adapterItems3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);

        saveBtn = findViewById(R.id.saveBtn);

        autoCompleteTextView = findViewById(R.id.auto_complete_text);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,item);

        autoCompleteTextView1 = findViewById(R.id.auto_complete_text1);
        adapterItems1 = new ArrayAdapter<String>(this,R.layout.list_item,item1);

        autoCompleteTextView2 = findViewById(R.id.auto_complete_text2);
        adapterItems2 = new ArrayAdapter<String>(this,R.layout.list_item,item2);

        autoCompleteTextView3 = findViewById(R.id.auto_complete_text3);
        adapterItems3 = new ArrayAdapter<String>(this,R.layout.list_item,item3);

        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView1.setAdapter(adapterItems1);

        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView2.setAdapter(adapterItems2);

        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView3.setAdapter(adapterItems3);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Units.this,"You have selected:" + item, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item1 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Units.this,"You have selected" + item1, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item2 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Units.this,"You have selected:" + item2, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item3 = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Units.this,"You have selected:" + item3, Toast.LENGTH_SHORT).show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveChange();
            }
        });
    }

            private void saveChange() {
                if (isValidSelection()) {

                    Toast.makeText(Units.this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Display error message if any field is not selected
                    Toast.makeText(Units.this, "Please select all fields", Toast.LENGTH_SHORT).show();
                }
            }

            @SuppressLint("RestrictedApi")
            private boolean isValidSelection() {
                // Assuming autoCompleteTextView3 is properly initialized elsewhere in your code
                return autoCompleteTextView3 != null &&
                        !autoCompleteTextView3.getText().toString().isEmpty() &&
                        !autoCompleteTextView1.getText().toString().isEmpty() &&
                        !autoCompleteTextView2.getText().toString().isEmpty();
            }


    }
