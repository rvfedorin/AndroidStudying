package com.frv.beeradviser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class FindBeerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }

    public void onClickFindBeer(View view) {
        TextView brands = (TextView) findViewById(R.id.brands);
        Spinner color = (Spinner) findViewById(R.id.color);
        String currentColor = String.valueOf(color.getSelectedItem());

        BeerExpert beerExpert = new BeerExpert();
        String recommendations = "";
        for(String s: beerExpert.getBrands(currentColor)) {
            recommendations += s + "\n";
        }

        brands.setText(recommendations);
    }
}