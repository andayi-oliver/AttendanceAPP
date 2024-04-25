package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    EditText username;
    EditText password;
    EditText email;
    Button loginButton;
    TextView registertv;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        email= findViewById(R.id.loginemail);
        password = findViewById(R.id.password);
        auth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.loginButton);
        registertv = findViewById(R.id.signupTextRegister);

        registertv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Registration.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = email.getText().toString().trim();
                String inputPassword = password.getText().toString().trim();

                if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(inputEmail, inputPassword)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = auth.getCurrentUser();
                                    Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, Home.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthInvalidUserException e) {
                                        Toast.makeText(LoginActivity.this, "No account found with this email, please register!", Toast.LENGTH_SHORT).show();
                                    } catch (FirebaseAuthInvalidCredentialsException e) {
                                        Toast.makeText(LoginActivity.this, "Invalid email or password, please try again!", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        Toast.makeText(LoginActivity.this, "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
    }
}
