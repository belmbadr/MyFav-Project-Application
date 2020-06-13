package com.example.myfav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class UpdateMovie extends AppCompatActivity {

    DatabaseMovies db;

    EditText editTitle, editDirector, editRate;
    Button btnUpdate;
    ImageButton pickImg;

    byte[] poster = null;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie);
        //getSupportActionBar().hide();

        id = getIntent().getIntExtra("id",0);

        db = new DatabaseMovies(this);

        Movie movie = db.getMovieById(id);

        editTitle = (EditText) findViewById(R.id.editTitle);
        editDirector = (EditText) findViewById(R.id.editDirector);
        editRate = (EditText) findViewById(R.id.editRate);
        pickImg = (ImageButton) findViewById(R.id.pickImg);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        editTitle.setText(movie.getTitle());
        editDirector.setText(movie.getDirector());
        editRate.setText(String.valueOf(movie.getRate())); //(movie.getRate()+ "");

        Bitmap bitmap = BitmapFactory.decodeByteArray(movie.getPoster(), 0, movie.getPoster().length);
        pickImg.setImageBitmap(bitmap);
        poster = getBytes(bitmap);



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTitle.getText().toString();
                String director = editDirector.getText().toString();

                float rate;
                if (editRate.getText().toString().equals("")) {
                    rate = 0;
                } else {
                    rate = Float.parseFloat(editRate.getText().toString());
                }

                BitmapDrawable drawable = (BitmapDrawable) pickImg.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                poster = getBytes(bitmap);

                Movie newMovie = new Movie(id, title, director, rate, poster);

                db.updateMovie(newMovie);

                Toast.makeText(UpdateMovie.this,"Movie Updated Successfully", Toast.LENGTH_SHORT).show();
                finish();;

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.delete_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_delete :

                showAlert();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Confirmation")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteMovie(id);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }

    public void openGalleries(View view) {

        Intent intentImg = new Intent(Intent.ACTION_GET_CONTENT);
        intentImg.setType("image/*");
        startActivityForResult(intentImg, 100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100) {

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
