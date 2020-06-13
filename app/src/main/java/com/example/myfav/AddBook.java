package com.example.myfav;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AddBook extends AppCompatActivity {

    EditText editTitle, editAuthor, editRate;
    TextView txtRate;
    Button btnConfirm;
    ImageButton pickImg;

    byte[] poster = null;
    DatabaseBooks db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        getSupportActionBar().hide();
        db = new DatabaseBooks(this);

        editTitle = (EditText) findViewById(R.id.editTitle);
        //final String theTitle = editTitle.getText().toString();

        editAuthor = (EditText) findViewById(R.id.editAuthor);
        editRate = (EditText) findViewById(R.id.editRate);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        pickImg = (ImageButton) findViewById(R.id.pickImg);
        txtRate = (TextView)findViewById(R.id.txtRate);

        txtRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p1 = new Intent(AddBook.this,wbookRate.class);
                //p1.putExtra("text_label", theTitle);
                startActivity(p1);
            }
        });



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String author = editAuthor.getText().toString();

                float rate;
                if (editRate.getText().toString().equals("")) {
                    rate = 0;
                } else {
                    rate = Float.parseFloat(editRate.getText().toString());
                }

                BitmapDrawable drawable = (BitmapDrawable) pickImg.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                poster = getBytes(bitmap);


                Book book = new Book(title, author, rate, poster);

                db.addBook(book);

                Toast.makeText(AddBook.this, "Book Added Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void openGallerie(View view){
        Intent intentImg = new Intent(Intent.ACTION_GET_CONTENT);
        intentImg.setType("image/*");
        startActivityForResult(intentImg, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100){

            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                pickImg.setImageBitmap(decodeStream);

                poster = getBytes(decodeStream);

            } catch (Exception ex) {
                Log.e("ex", ex.getMessage());
            }
        }
    }


    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
}
