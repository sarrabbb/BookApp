package com.bb.bookapp;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;



public class BookListFragment extends Fragment {
    View v ;
    MyDataBaseHelper myDB;
    ArrayList<String> book_id, book_title, book_author, book_pages;
    private  RecyclerView recyclerView;
    Button ViewBtn;
    TextView book_id_txt , book_title_txt , book_author_txt , book_pages_txt;
    String book_id_txt2;
    String book_title_txt2;
    String book_author_txt2;
    String book_pages_txt2;

    public BookListFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile,container,false);
        recyclerView = v.findViewById(R.id.recyleview);

        myDB = new MyDataBaseHelper(getActivity());
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();
        storeDataInArrays();

        FragmentAdapter customAdapter = new FragmentAdapter(getActivity(),getContext(), book_id,book_title,book_author,book_pages);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        book_id_txt = v.findViewById(R.id.book_id_txt);
        book_title_txt = v.findViewById(R.id.book_title_txt);
        book_author_txt = v.findViewById(R.id.book_author_txt);
        book_pages_txt = v.findViewById(R.id.book_pages_txt);

        return v;
    }




    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    void storeDataInArrays (){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(getActivity(),"no data",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
        }
    }
}
