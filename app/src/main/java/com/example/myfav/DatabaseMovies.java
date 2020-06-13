package com.example.myfav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseMovies extends SQLiteOpenHelper {

    private  static final String DB_NAME = "Movies.db";
    private static final int DB_VERSION = 2;

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DIRECTOR = "director";
    private static final String KEY_RATE = "rate";
    private static final String KEY_POSTER = "image";

    private static final String TABLE_MOVIE = "movies";

    public DatabaseMovies(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = " create table "+TABLE_MOVIE+"("+KEY_ID+" integer primary key autoincrement, "
                + KEY_TITLE + " varchar(255) DEFAULT'',"
                + KEY_DIRECTOR + " varchar(255) DEFAULT'',"
                + KEY_RATE + " float ,"
                + KEY_POSTER + " blob)";

        Log.d("create", create_table);
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String delete_query = "DROP table if exists " + TABLE_MOVIE;
        db.execSQL(delete_query);

        onCreate(db);
    }

    public void addMovie(Movie movie){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_DIRECTOR, movie.getDirector());
        values.put(KEY_RATE, movie.getRate());
        values.put(KEY_POSTER, movie.getPoster());

        db.insert(TABLE_MOVIE, null, values);
    }

    public ArrayList<Movie> getAllMovies(){
        ArrayList<Movie> movies = new ArrayList<>();

        String select_query = "select * from "+TABLE_MOVIE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()){

            do{

                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String title = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
                String director = cursor.getString(cursor.getColumnIndex(KEY_DIRECTOR));
                float rate = cursor.getFloat(cursor.getColumnIndex(KEY_RATE));
                byte[] poster = cursor.getBlob(cursor.getColumnIndex(KEY_POSTER));

                Movie movie = new Movie(id, title, director, rate, poster);

                movies.add(movie);

            }while (cursor.moveToNext());
        }


        return  movies;
    }



    public Movie getMovieById(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        String select_query = "select * from "+TABLE_MOVIE + " where id="+id;

        Cursor cursor = db.rawQuery(select_query, null);


        Movie movie = null;

        if (cursor.moveToFirst()){

            int id_movie = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String title = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
            String director = cursor.getString(cursor.getColumnIndex(KEY_DIRECTOR));
            float rate = cursor.getFloat(cursor.getColumnIndex(KEY_RATE));
            byte[] poster = cursor.getBlob(cursor.getColumnIndex(KEY_POSTER));

            movie = new Movie(id, title, director, rate, poster);

        }
        return movie;
    }


    public void updateMovie(Movie movie){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_DIRECTOR, movie.getDirector());
        values.put(KEY_RATE, movie.getRate());
        values.put(KEY_POSTER, movie.getPoster());

        db.update(TABLE_MOVIE, values, "id=?", new String[]{String.valueOf(movie.getId())});
    }

    public void deleteMovie(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_MOVIE, "id=?", new String[]{String.valueOf(id)});
    }
}
