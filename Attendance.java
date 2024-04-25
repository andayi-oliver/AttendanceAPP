package com.example.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class Attendance extends AppCompatActivity {

    private CheckBox checkbox_class1, checkbox_class2, checkbox_class3, checkbox_class4,
            checkbox_class5, checkbox_class6, checkbox_class7, checkbox_class8,
            checkbox_class9, checkbox_class10, checkbox_class11, checkbox_class12,
            checkbox_class13, checkbox_class14, checkbox_class15;

    private Button mondayButton, tuedayButton, wednesdayButton, thursdayButton, fridayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        // Initialize checkboxes
        checkbox_class1 = findViewById(R.id.checkbox_class1);
        checkbox_class2 = findViewById(R.id.checkbox_class2);
        checkbox_class3 = findViewById(R.id.checkbox_class3);
        checkbox_class4 = findViewById(R.id.checkbox_class4);
        checkbox_class5 = findViewById(R.id.checkbox_class5);
        checkbox_class6 = findViewById(R.id.checkbox_class6);
        checkbox_class7 = findViewById(R.id.checkbox_class7);
        checkbox_class8 = findViewById(R.id.checkbox_class8);
        checkbox_class9 = findViewById(R.id.checkbox_class9);
        checkbox_class10 = findViewById(R.id.checkbox_class10);
        checkbox_class11 = findViewById(R.id.checkbox_class11);
        checkbox_class12 = findViewById(R.id.checkbox_class12);
        checkbox_class13 = findViewById(R.id.checkbox_class13);
        checkbox_class14 = findViewById(R.id.checkbox_class14);
        checkbox_class15 = findViewById(R.id.checkbox_class15);

        // Initialize buttons
        mondayButton = findViewById(R.id.mondayButton);
        tuedayButton = findViewById(R.id.tuedayButton);
        wednesdayButton = findViewById(R.id.wednesdayButton);
        thursdayButton = findViewById(R.id.thursdayButton);
        fridayButton = findViewById(R.id.fridayButton);

        // Disable buttons initially
        disableButton(mondayButton);
        disableButton(tuedayButton);
        disableButton(wednesdayButton);
        disableButton(thursdayButton);
        disableButton(fridayButton);

        // Set listeners for checkboxes
        setCheckBoxListener(checkbox_class1, mondayButton);
        setCheckBoxListener(checkbox_class2, mondayButton);
        setCheckBoxListener(checkbox_class3, mondayButton);
        setCheckBoxListener(checkbox_class4, tuedayButton);
        setCheckBoxListener(checkbox_class5, tuedayButton);
        setCheckBoxListener(checkbox_class6, tuedayButton);
        setCheckBoxListener(checkbox_class7, wednesdayButton);
        setCheckBoxListener(checkbox_class8, wednesdayButton);
        setCheckBoxListener(checkbox_class9, wednesdayButton);
        setCheckBoxListener(checkbox_class10, thursdayButton);
        setCheckBoxListener(checkbox_class11, thursdayButton);
        setCheckBoxListener(checkbox_class12, thursdayButton);
        setCheckBoxListener(checkbox_class13, fridayButton);
        setCheckBoxListener(checkbox_class14, fridayButton);
        setCheckBoxListener(checkbox_class15, fridayButton);
    }

    // Method to set a listener for a checkbox
    private void setCheckBoxListener(CheckBox checkBox, Button button) {
        checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                enableButton(button);
            } else {
                disableButton(button);
            }
        });
    }

    // Method to enable a button
    private void enableButton(Button button) {
        button.setEnabled(true);
        button.setAlpha(1f);
    }

    // Method to disable a button
    private void disableButton(Button button) {
        button.setEnabled(false);
        button.setAlpha(0.5f);
    }
}
