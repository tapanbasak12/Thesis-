package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity{


    private EditText Username;
    private EditText Password;
    private Button Register;
    private TextView Login;
    private Spinner spinner;
    private DatabaseReference mDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");


        Username = findViewById(R.id.etRegisterUsername);
        Password= findViewById(R.id.etRegisterPassword);
        spinner = findViewById(R.id.spinner);

        Register= findViewById(R.id.btnRegister);
        Login= findViewById(R.id.btnGoToLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, loginActivity.class));
            }
        });




        Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addUser();
                //startActivity(new Intent(RegisterActivity.this, SensorClass.class));

            }
        });
    }

    private void addUser() {
        String name= Username.getText().toString().trim();
        String password= Password.getText().toString().trim();
        String usertype= spinner.getSelectedItem().toString();

        String id= mDatabase.push().getKey();

        User user= new User(id, name, password, usertype);

        mDatabase.child(name).setValue(user);

        Toast.makeText(this, "User Created", Toast.LENGTH_LONG).show();
    }


}





