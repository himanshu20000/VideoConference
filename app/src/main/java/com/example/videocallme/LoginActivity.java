package com.example.videocallme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailBox,passwordBox;
    Button loginBtn,signUpBtn;

    FirebaseAuth auth;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please take a deep breathe while we loading !!");

        auth = FirebaseAuth.getInstance();
        emailBox = findViewById(R.id.emailBox);
        passwordBox = findViewById(R.id.passwordBox);
        loginBtn = findViewById(R.id.loginBtn);
       signUpBtn = findViewById(R.id.createAccBtn);

       if(auth.getCurrentUser()!= null)
       {
           startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
           finish();
       }

       loginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialog.show();
               String email,pass;

               email = emailBox.getText().toString();
               pass = passwordBox.getText().toString();
               auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       dialog.dismiss();
                       if (task.isSuccessful())
                       {
                           startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
                           Toast.makeText(LoginActivity.this, "Logged in Sucessfully", Toast.LENGTH_SHORT).show();
                       }

                       else {
                           Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                       }
                   }
               });
           }
       });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));

            }
        });

    }
}