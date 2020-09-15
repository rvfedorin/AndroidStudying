package com.frv.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.CompletableFuture;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINKNO = "drinkNO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkNo = (int) getIntent().getExtras().get(EXTRA_DRINKNO);

        try {
            StarbuzzDatabaseHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query("DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkNo)},
                    null,null,null);

            if(cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int imageId = cursor.getInt(2);
                boolean isFavorite = cursor.getInt(3) == 1;

                TextView name = findViewById(R.id.name);
                name.setText(nameText);
                TextView descr = findViewById(R.id.description);
                descr.setText(descriptionText);
                ImageView imageView = findViewById(R.id.photo);
                imageView.setImageResource(imageId);
                imageView.setContentDescription(nameText);
                CheckBox favorite = findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);

            }

            cursor.close();
            db.close();

        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Databese unaviable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onFavoriteClicked(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                setInDB();
            }
        }).start();
    }

    private void setInDB() {
        int id = (int) getIntent().getExtras().get(EXTRA_DRINKNO);
        CheckBox checkBox = findViewById(R.id.favorite);
        try {
            SQLiteOpenHelper bdHelper = new StarbuzzDatabaseHelper(this);
            SQLiteDatabase db = bdHelper.getWritableDatabase();

            ContentValues changes = new ContentValues();
            changes.put("FAVORITE", checkBox.isChecked());

            db.update("DRINK",
                    changes,
                    "_id = ?",
                    new String[]{String.valueOf(id)});


            db.close();

        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "DB unaviable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
} // class DrinkActivity