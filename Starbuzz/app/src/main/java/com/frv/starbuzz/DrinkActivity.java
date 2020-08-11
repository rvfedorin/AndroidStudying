package com.frv.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINKNO = "drinkNO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        int drinkNo = (int) getIntent().getExtras().get(EXTRA_DRINKNO);
            Drink drink = Drink.drinks[drinkNo];
            ImageView photo = findViewById(R.id.photo);
            photo.setImageResource(drink.getImageResourceId());
            photo.setContentDescription(drink.getName());

            TextView name = findViewById(R.id.name);
            name.setText(drink.getName());

            TextView description = findViewById(R.id.description);
            description.setText(drink.getDescription());
    }
}