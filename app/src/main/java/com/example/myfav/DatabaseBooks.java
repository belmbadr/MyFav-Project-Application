package com.example.myfav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import java.util.ArrayList;

public class DatabaseBooks extends SQLiteOpenHelper {

    private  static final String DB_NAME = "Books.db";
    private static final int DB_VERSION = 2;

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_RATE = "rate";
    private static final String KEY_POSTER = "image";

    private static final String TABLE_BOOK = "books";

    public DatabaseBooks(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = " create table "+TABLE_BOOK+"("+KEY_ID+" integer primary key autoincrement, "
                + KEY_TITLE + " varchar(255) DEFAULT'',"
                + KEY_AUTHOR + " varchar(255) DEFAULT'',"
                + KEY_RATE + " float ,"
                + KEY_POSTER + " blob)";

        Log.d("create", create_table);
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String delete_query = "DROP table if exists " + TABLE_BOOK;
        db.execSQL(delete_query);

        onCreate(db);
    }

    public void addBook(Book book){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle());
        values.put(KEY_AUTHOR, book.getAuthor());
        values.put(KEY_RATE, book.getRate());
        values.put(KEY_POSTER, book.getPoster());

        db.insert(TABLE_BOOK, null, values);
    }

    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> books = new ArrayList<>();

        String select_query = "select * from "+TABLE_BOOK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()){

            do{

                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String title = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
                String author = cursor.getString(cursor.getColumnIndex(KEY_AUTHOR));
                float rate = cursor.getFloat(cursor.getColumnIndex(KEY_RATE));
                byte[] poster = cursor.getBlob(cursor.getColumnIndex(KEY_POSTER));

                Book book = new Book(id, title, author, rate, poster);

                books.add(book);

            }while (cursor.moveToNext());
        }

        return  books;
    }


    public Book getBookById(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        String select_query = "select * from "+TABLE_BOOK + " where id="+id;

        Cursor cursor = db.rawQuery(select_query, null);

        Book book = null;

        if (cursor.moveToFirst()){

            int id_book = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String title = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
            String author = cursor.getString(cursor.getColumnIndex(KEY_AUTHOR));
            float rate = cursor.getFloat(cursor.getColumnIndex(KEY_RATE));
            byte[] poster = cursor.getBlob(cursor.getColumnIndex(KEY_POSTER));

            book = new Book(id, title, author, rate, poster);

        }
        return book;
    }


    public void updateBook(Book book){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle());
        values.put(KEY_AUTHOR, book.getAuthor());
        values.put(KEY_RATE, book.getRate());
        values.put(KEY_POSTER, book.getPoster());

        db.update(TABLE_BOOK, values, "id=?", new String[]{String.valueOf(book.getId())});
    }

    public void deleteBook(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_BOOK, "id=?", new String[]{String.valueOf(id)});
    }
}
