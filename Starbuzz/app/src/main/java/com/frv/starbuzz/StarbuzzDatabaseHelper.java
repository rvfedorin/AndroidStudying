package com.frv.starbuzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "starbuzz";
    private static final int DB_VER = 2;

    public StarbuzzDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDb(sqLiteDatabase, 0, DB_VER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        updateMyDb(sqLiteDatabase, i, i1);

    }

    private void updateMyDb(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        final String TABLE_NAME = "DRINK";

        if (oldVer < 1) {
            String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "NAME TEXT, " +
                    "DESCRIPTION TEXT, " +
                    "IMAGE_RESOURCE_ID INTEGER);";

            sqLiteDatabase.execSQL(createTableQuery);

            for (Drink drink : Drink.drinks) {
                ContentValues drinksValues = new ContentValues();
                drinksValues.put("NAME", drink.getName());
                drinksValues.put("DESCRIPTION", drink.getDescription());
                drinksValues.put("IMAGE_RESOURCE_ID", drink.getImageResourceId());
                sqLiteDatabase.insert(TABLE_NAME, null, drinksValues);
            }
        }
        if (oldVer < 2) {
            sqLiteDatabase.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN FAVORITE NUMERIC;");
        }
    }
} // class StarbuzzDatabaseHelper
