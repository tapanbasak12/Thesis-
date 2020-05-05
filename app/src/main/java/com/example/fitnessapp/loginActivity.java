package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Signup;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = (EditText) findViewById(R.id.etname);
        Password = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.btLogin);
        Signup= findViewById(R.id.signuptextview);



        mDatabase = FirebaseDatabase.getInstance().getReference("users");


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginActivity.this, RegisterActivity.class));
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void Validate(String usrName, String usrPsssword) {

        mDatabase.child(usrName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String dbpassword = dataSnapshot.child("userPassword").getValue(String.class);


                if ( usrPsssword.equals(dbpassword) ) {

                    Toast.makeText(loginActivity.this, "Matched" + usrName, Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(loginActivity.this, MainActivity.class);
                    intent.putExtra("username", usrName);
                    startActivity(intent);
                }

                else{
                    Toast.makeText(loginActivity.this, "WONG PASS" , Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}


