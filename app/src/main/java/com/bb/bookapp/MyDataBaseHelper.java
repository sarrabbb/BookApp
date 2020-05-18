package com.bb.bookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


class MyDataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION =6;

    private static final String TABLE_NAME_1 = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PAGES = "book_pages";


    private static final String TABLE_NAME_2 = "my_users";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PWD = "pwd";


    private  static final String create_books = "CREATE TABLE " + TABLE_NAME_1 +
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_AUTHOR + " TEXT, " +
            COLUMN_PAGES + " INTEGER);";

    private  static final  String create_users = "CREATE TABLE " + TABLE_NAME_2 +
            " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERNAME + " TEXT, " +
            COLUMN_USER_EMAIL + " TEXT, " +
            COLUMN_PWD + " TEXT);";

    MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_books);
        db.execSQL(create_users);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        onCreate(db);
    }



//    USERS
    void addUser(String username, String email ,String pwd){
        SQLiteDatabase db2 = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME,username);
        values.put(COLUMN_USER_EMAIL,email);
        values.put(COLUMN_PWD,pwd);


        long result = db2.insert(TABLE_NAME_2,null, values);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Register Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllUsers(){
        String query = "SELECT * FROM " + TABLE_NAME_2;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    boolean  verifyUser( String username , String pwd ){
        SQLiteDatabase db = this.getReadableDatabase();

//        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_2 +" WHERE username=? AND pwd=?", new String[]{username,pwd});
        Cursor mCursor = db.rawQuery("Select * from " + TABLE_NAME_2 +" where username=? and pwd = ?", new
                String[]{username, pwd});

            if(mCursor.getCount() > 0)
            {
//                Toast.makeText(context, "Logged in", Toast.LENGTH_SHORT).show();
                return  true;
            }else{
                //        Toast.makeText(context, "Failed to sign in", Toast.LENGTH_SHORT).show();

                return  false;
            }
        }



//    books
    void addBook(String title, String author, int pages){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);
        long result = db.insert(TABLE_NAME_1,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }


    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME_1;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void  updateData(String row_id, String title , String author , String pages){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_AUTHOR,author);
        cv.put(COLUMN_PAGES,pages);

        long result = DB.update(TABLE_NAME_1, cv , "_id=?",new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_1, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME_1);
    }

}
