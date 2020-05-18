package com.bb.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username_input,password_input;
    Button tosignup_button, Login_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_input = findViewById(R.id.username_input);
        password_input = findViewById(R.id.password_input);

        Login_button = findViewById(R.id.Login_button);
        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBaseHelper myDB = new MyDataBaseHelper(LoginActivity.this);
                String user = username_input.getText().toString().trim();
                String pwd = password_input.getText().toString().trim();
                boolean checkUserNamePwd =   myDB.verifyUser(user,pwd);
                System.out.println(pwd);
                System.out.println(user);
                if(checkUserNamePwd == true){
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,"Successfully Logged In", Toast.LENGTH_LONG).show();
                }else{
//                    Intent intent = new Intent(LoginActivity.this, AddActivity.class);
//                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,"Invalid Username/Password", Toast.LENGTH_LONG).show();
                }




            }
        });



        tosignup_button = findViewById(R.id.tosignup_button);
        tosignup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
