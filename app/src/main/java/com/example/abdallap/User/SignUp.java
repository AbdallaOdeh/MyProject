package com.example.abdallap.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.abdallap.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class SignUp extends AppCompatActivity {
    EditText fullNameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText repasswordEditText;
    Button registerButton;
    TextView errorText;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fullNameEditText=findViewById(R.id.ptFullName);
        emailEditText=findViewById(R.id.ptEmail2);
        passwordEditText=findViewById(R.id.ptPassword2);
        repasswordEditText=findViewById(R.id.ptRePassword);
        registerButton=findViewById(R.id.btRegister);
        errorText=findViewById(R.id.tvError);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });
    }
    private void createNewAccount(){
        errorText.setVisibility(View.GONE);
        if(fullNameEditText.getText().toString().equals("")){
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("please enter your full name");
            return;
        }
        if(emailEditText.getText().toString().equals("")){
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("please enter your email");
            return;
        }
        if(passwordEditText.getText().toString().equals("")){
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("please enter your password");
            return;
        }
        if(repasswordEditText.getText().toString().equals("")){
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("please confirm your password");
            return;
        }
        if(!repasswordEditText.getText().toString().equals(passwordEditText.getText().toString())){
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("password doesn't match");
            return;
        }
        final FirebaseAuth mAuth=FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(fullNameEditText.getText().toString())
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                startActivity(new Intent(SignUp.this,MainActivity.class));
                                            }
                                        }
                                    });
                        } else {

                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            errorText.setVisibility(View.VISIBLE);
                            errorText.setText(task.getException().getMessage());

                        }
                    }
                });
    }
}