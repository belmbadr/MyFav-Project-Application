package com.example.myfav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class myBooks extends AppCompatActivity {

    ListView booksList;
    Button btnAdd;

    DatabaseBooks db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);
        getSupportActionBar().hide();

        db = new DatabaseBooks(this);

        booksList = (ListView) findViewById(R.id.booksList);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(myBooks.this,AddBook.class);
                startActivity(intent);

            }
        });

        booksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Book selected_book = (Book) parent.getItemAtPosition(position);

                Intent intent = new Intent(myBooks.this, UpdateBook.class);

                intent.putExtra("id", selected_book.getId());

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Book> books = db.getAllBooks();

        BookAdapter bookAdapter = new BookAdapter(this, R.layout.row,books);

        booksList.setAdapter(bookAdapter);
    }
}
