package com.bb.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    EditText username_input,useremail_input,password_input;
    Button register_button,tosigin_button;
    Spinner spinnerRole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username_input = findViewById(R.id.username_input2);
        useremail_input = findViewById(R.id.useremail_input);
        password_input = findViewById(R.id.password_input2);
        spinnerRole = findViewById(R.id.spinnerRole);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.usertype,R.layout.support_simple_spinner_dropdown_item);
        spinnerRole.setAdapter(adapter);

        register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBaseHelper myDB = new MyDataBaseHelper(RegisterActivity.this);
                myDB.addUser(username_input.getText().toString().trim(),
                        useremail_input.getText().toString().trim(),
                        spinnerRole.getSelectedItem().toString().trim(),
                        password_input.getText().toString().trim())
                ;
                finish();
            }
        });

        tosigin_button = findViewById(R.id.tosigin_button);
        tosigin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
