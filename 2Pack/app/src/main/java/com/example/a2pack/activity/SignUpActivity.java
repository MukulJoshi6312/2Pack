package com.example.a2pack.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.a2pack.R;
import com.example.a2pack.helperClasses.UserHelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout text_name, text_email, text_password, text_number,text_city,text_address;
    private Button registrationBtn;
    private ImageView backImage;
    ProgressBar progressBar;
    private  FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressBar = findViewById(R.id.progress_circular);
        mAuth = FirebaseAuth.getInstance();

        text_name = findViewById(R.id.textName);
        text_email = findViewById(R.id.textEmail);
        text_password = findViewById(R.id.textPassword);
        text_number = findViewById(R.id.phoneNumber);
        text_city = findViewById(R.id.cityName);
        text_address = findViewById(R.id.addressName);
        registrationBtn = findViewById(R.id.registration_button);
        backImage = findViewById(R.id.back_image);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));

            }
        });

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String userName = Objects.requireNonNull(text_name.getEditText()).getText().toString().trim();
                final String userEmail = Objects.requireNonNull(text_email.getEditText()).getText().toString().trim();
                final String password = Objects.requireNonNull(text_password.getEditText()).getText().toString().trim();
                final String phoneNumber = Objects.requireNonNull(text_number.getEditText()).getText().toString().trim();
                final String userCity = Objects.requireNonNull(text_city.getEditText()).getText().toString().trim();
                final String userAddress = Objects.requireNonNull(text_address.getEditText()).getText().toString().trim();

                if (userName.isEmpty()) {
                    text_name.setError("Name is Required!");
                    text_name.requestFocus();
                    return;
                }

                if (userEmail.isEmpty()) {
                    text_email.setError("Email is Required!");
                    text_email.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                    text_email.setError("please provide valid email!");
                    text_email.requestFocus();
                    return;
                }

                if (password.isEmpty()){
                    text_password.setError("Password is Required!");
                    text_password.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    text_password.setError("Min password should be 6 character!");
                    text_password.requestFocus();
                    return;
                }

                if (phoneNumber.isEmpty()) {
                    text_number.setError("number is Required");
                    text_number.requestFocus();
                    return;
                }
                if (phoneNumber.length() < 10) {
                    text_number.setError("please provide valid number");
                    text_number.requestFocus();
                    return;
                }

                if (userCity.isEmpty()) {
                    text_city.setError("Name is Required!");
                    text_city.requestFocus();
                    return;
                }

                if (userAddress.isEmpty()) {
                    text_address.setError("Name is Required!");
                    text_address.requestFocus();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(userEmail,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull  Task<AuthResult> task) {

                                if (task.isSuccessful()){
                                    UserHelperClass userHelperClass = new UserHelperClass(userName,userEmail,password,phoneNumber,userCity,userAddress);
                                    FirebaseDatabase.getInstance().getReference("user")
                                            .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                            .setValue(userHelperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull  Task<Void> task) {

                                            if (task.isSuccessful()){
                                                Toast.makeText(SignUpActivity.this, "Registration is successfully done", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                                                startActivity(intent);
                                            }
                                            else {
                                                Toast.makeText(SignUpActivity.this, "Error!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }

                                        }
                                    });
                                }
                                else {
                                    Toast.makeText(SignUpActivity.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }

                            }
                        });
            }
        });

    }
}