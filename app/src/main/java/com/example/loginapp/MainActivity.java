package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText name, password;
    private TextView attempts;
    private Button login;
    private String admin = "Admin";
    private String adminPsw = "12345";
    private boolean isValid = false;
    private int counter = 5;
    private String info = "Number of attempts remaining: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        attempts = findViewById(R.id.tvAttempts);
        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = name.getText().toString();
                String inputPassword = password.getText().toString();

                if(inputName.isEmpty() && inputPassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter in your credentials", Toast.LENGTH_SHORT).show();
                }else if (inputPassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter in your password", Toast.LENGTH_SHORT).show();
                }else if(inputName.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter in your username", Toast.LENGTH_SHORT).show();
                }else{
                    isValid = validate(inputName, inputPassword);
                    if(!isValid){
                        counter--;
                        attempts.setText(info + counter);
                        Toast.makeText(MainActivity.this, "Incorrect credentials", Toast.LENGTH_SHORT).show();
                        if(counter == 0){
                            login.setEnabled(false);
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LoginPage.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }

    public boolean validate(String name, String password){
        if(name.equals(admin) && password.equals(adminPsw)){
            return true;
        }else{
            return false;
        }
    }

}