package com.bb.bookapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class BookFragment extends Fragment {
    TextView title_input , author_input , pages_input;
    String id , title , author , pages;
    MyDataBaseHelper myDB;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_book,container,false);

        title_input = view.findViewById(R.id.Book_name);
        author_input = view.findViewById(R.id.Book_author);
        pages_input = view.findViewById(R.id.Book_nbpages);


        Bundle bundle = getArguments();

        title = String.valueOf(bundle.getString("Title"));
        title_input.setText(title);

        author = String.valueOf(bundle.getString("Author"));
        author_input.setText(author);

        pages = String.valueOf(bundle.getString("Pages"));
        pages_input.setText(pages);

        return  view;
    }




    void getAndSetInentData(){
        if(getActivity().getIntent().hasExtra("Id") && getActivity().getIntent().hasExtra("Title") && getActivity().getIntent().hasExtra("Author") && getActivity().getIntent().hasExtra("Pages")  )
        {
            // Getting Data from Intent
            id = getActivity().getIntent().getStringExtra("Id");
            title = getActivity().getIntent().getStringExtra("Title");
            author = getActivity().getIntent().getStringExtra("Author");
            pages = getActivity().getIntent().getStringExtra("Pages");

            // Setting Intent Data.
            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);

        }else{
            Toast.makeText(getActivity(),"No Data",Toast.LENGTH_SHORT).show();
        }
    }

}
