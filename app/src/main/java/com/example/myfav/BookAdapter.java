package com.example.myfav;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    Context context;
    int resource;

    public BookAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        TextView bookTitle = (TextView) convertView.findViewById(R.id.bookTitle);
        TextView bookAuthor = (TextView) convertView.findViewById(R.id.bookAuthor);
        TextView bookRate = (TextView) convertView.findViewById(R.id.bookRate);
        ImageView bookPoster = (ImageView) convertView.findViewById(R.id.bookPoster);

        Book currentBook = getItem(position);

        bookTitle.setText(currentBook.getTitle());
        bookAuthor.setText(currentBook.getAuthor());

        if ( currentBook.getRate() == 0){
            bookRate.setText("");
        }else {
            bookRate.setText(String.valueOf(currentBook.getRate()));
        }

        Bitmap bitmap = BitmapFactory.decodeByteArray(currentBook.getPoster(),0,currentBook.getPoster().length);
        bookPoster.setImageBitmap(bitmap);

        return convertView;
    }
}
