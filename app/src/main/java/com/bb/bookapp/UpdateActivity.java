package com.bb.bookapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText title_input , author_input , pages_input;
    Button update_button,delete_button;

    String id , title , author , pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        pages_input = findViewById(R.id.pages_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetInentData();

        ActionBar ab = getSupportActionBar();
        if (ab!= null){
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBaseHelper myDB = new MyDataBaseHelper(UpdateActivity.this);
                title = title_input.getText().toString().trim();
                author = author_input.getText().toString().trim();
                pages = pages_input.getText().toString().trim();
                myDB.updateData(id, title, author, pages);
                finish();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDiaglog();

            }
        });
    }

    void getAndSetInentData(){
        if(getIntent().hasExtra("Id") && getIntent().hasExtra("Title") && getIntent().hasExtra("Author") && getIntent().hasExtra("Pages")  )
        {
            // Getting Data from Intent
            id = getIntent().getStringExtra("Id");
            title = getIntent().getStringExtra("Title");
            author = getIntent().getStringExtra("Author");
            pages = getIntent().getStringExtra("Pages");

            // Setting Intent Data.
            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);

        }else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }


    void confirmDiaglog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + "?");
        builder.setMessage("Are you sure you want to delete this book" +title+ "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDataBaseHelper myDB = new MyDataBaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
//               to redirect to the main activity
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }
}
