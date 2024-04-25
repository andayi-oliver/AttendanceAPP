package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class Registration extends AppCompatActivity {
    EditText etusername, etpassword, etemail, etphone;
    Button btnRegister;
    TextView tvlogin;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etusername = findViewById(R.id.username);
        etpassword = findViewById(R.id.password);
        etemail = findViewById(R.id.email);
        etphone = findViewById(R.id.phone);
        auth = FirebaseAuth.getInstance();

        btnRegister = findViewById(R.id.registerButton);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if fields are empty
                String username = etusername.getText().toString().trim();
                String password = etpassword.getText().toString().trim();
                String email = etemail.getText().toString().trim();
                String phone = etphone.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    etusername.setError("Username is required!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etpassword.setError("Password is required!");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    etemail.setError("Email is required!");
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    etphone.setError("Phone number is required!");
                    return;
                }

                // Register user with email and password
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Registration.this, task -> {
                            if (task.isSuccessful()) {
                                // Registration success, update UI with the signed-in user's information
                                Toast.makeText(Registration.this, "Registration successful!",
                                        Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Registration.this, Home.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // If registration fails, display a message to the user.
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthWeakPasswordException e) {
                                    etpassword.setError("Weak password!");
                                    etpassword.requestFocus();
                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                    etemail.setError("Invalid email!");
                                    etemail.requestFocus();
                                } catch (FirebaseAuthUserCollisionException e) {
                                    etemail.setError("Email already exists!");
                                    etemail.requestFocus();
                                } catch (Exception e) {
                                    Toast.makeText(Registration.this, "Registration failed: " + e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        tvlogin = findViewById(R.id.signupText);

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
