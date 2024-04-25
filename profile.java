package com.example.login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.util.Calendar;

public class Profile extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    private static final int REQUEST_IMAGE_PICK = 1;
    private static final String PREF_PROFILE_IMAGE_URI = "profile_image_uri";
    private static final String PREF_NAME = "name";
    private static final String PREF_DATE = "date";
    private static final String PREF_YEAR = "year";
    private static final String PREF_PROGRAMME = "programme";

    private ImageView profilePicture;
    private EditText username, dateOfBirth, yearOfStudy, programme;
    private Button saveButton;

    private boolean isEditMode = false; // State variable to track edit mode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePicture = findViewById(R.id.profile_picture);
        username = findViewById(R.id.username);
        dateOfBirth = findViewById(R.id.date_of_birth);
        yearOfStudy = findViewById(R.id.year_of_study_edit_text);
        programme = findViewById(R.id.programme_edit_text);

        saveButton = findViewById(R.id.save_button);

        Button choosePictureButton = findViewById(R.id.picture_button);

        choosePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(Profile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateOfBirth.setText(dayOfMonth + "/" + (month + 1 + "/" + year));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEditMode) {
                    // If not in edit mode, switch to edit mode
                    enableEditMode();
                } else {
                    // If in edit mode, save the profile
                    saveProfile();
                    disableEditMode();
                }
            }
        });

        loadProfile();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                Glide.with(this)
                        .load(bitmap)
                        .apply(RequestOptions.circleCropTransform())
                        .into(profilePicture);

                saveImageToSharedPreferences(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveProfile() {
        // Get entered details
        String name = username.getText().toString();
        String date = dateOfBirth.getText().toString();
        String year = yearOfStudy.getText().toString();
        String programme = this.programme.getText().toString();

        // Save the entered details to SharedPreferences
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_NAME, name);
        editor.putString(PREF_DATE, date);
        editor.putString(PREF_YEAR, year);
        editor.putString(PREF_PROGRAMME, programme);
        editor.apply();

        // Update the UI with the entered details
        updateProfileDetails(name, date, year, programme);

        // Display a Toast message indicating the profile has been saved
        Toast.makeText(this, "Profile saved!", Toast.LENGTH_SHORT).show();
    }

    private void saveImageToSharedPreferences(Bitmap bitmap) {
        // Convert Bitmap to Uri
        Uri imageUri = getImageUriFromBitmap(bitmap);

        // Save the image URI to SharedPreferences
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_PROFILE_IMAGE_URI, imageUri.toString());
        editor.apply();
    }

    private Uri getImageUriFromBitmap(Bitmap bitmap) {
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Profile Image", null);
        return Uri.parse(path);
    }

    private void loadProfile() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String name = preferences.getString(PREF_NAME, "");
        String date = preferences.getString(PREF_DATE, "");
        String year = preferences.getString(PREF_YEAR, "");
        String programme = preferences.getString(PREF_PROGRAMME, "");

        // Set the loaded profile details
        username.setText(name);
        dateOfBirth.setText(date);
        yearOfStudy.setText(year);
        this.programme.setText(programme);

        // Load and set profile image
        String imageUriString = preferences.getString(PREF_PROFILE_IMAGE_URI, null);
        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            // Load the image using Glide and apply circular transformation
            Glide.with(this)
                    .load(imageUri)
                    .apply(RequestOptions.circleCropTransform())
                    .into(profilePicture);
        }
    }

    private void updateProfileDetails(String name, String date, String year, String programme) {
        // Set the loaded profile details
        username.setText(name);
        dateOfBirth.setText(date);
        yearOfStudy.setText(year);
        this.programme.setText(programme);
    }

    private void enableEditMode() {
        isEditMode = true;
        username.setEnabled(true);
        dateOfBirth.setEnabled(true);
        yearOfStudy.setEnabled(true);
        programme.setEnabled(true);
        saveButton.setText("Save");
    }

    private void disableEditMode() {
        isEditMode = false;
        username.setEnabled(false);
        dateOfBirth.setEnabled(false);
        yearOfStudy.setEnabled(false);
        programme.setEnabled(false);
        saveButton.setText("Edit");
    }
}
