package com.example.abdallap.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.abdallap.Admin.AddProductActivity;
import com.example.abdallap.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {  
    EditText emailEditText;
    EditText passwordEditText;
    Button loginButton;
    TextView newAccountButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        emailEditText=findViewById(R.id.ptEmail);
        passwordEditText=findViewById(R.id.ptPassword);
        loginButton=findViewById(R.id.btLogIn2);
        newAccountButton=findViewById(R.id.tvSignUp);
        newAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, SignUp.class));
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FirebaseAuth mAuth=FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                        .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    startActivity(new Intent(LogIn.this, MainActivity.class));

                                } else {

                                    Log.d("error","error");

                                }
                            }
                        });
            }
        });
    }
}