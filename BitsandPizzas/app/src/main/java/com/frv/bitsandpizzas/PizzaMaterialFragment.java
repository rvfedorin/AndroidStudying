package com.frv.bitsandpizzas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class PizzaMaterialFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        RecyclerView pizzasRecycler = (RecyclerView) inflater.inflate(
                R.layout.fragment_pizza_material, container, false);

        String[] pizzasNames = new String[Pizza.pizzas.length];
        int[] pizzasImages = new int[pizzasNames.length];
        for (int i = 0; i < pizzasNames.length; i++)
        {
            pizzasNames[i] = Pizza.pizzas[i].getName();
            pizzasImages[i] = Pizza.pizzas[i].getResource_img_id();
        }

        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzasNames, pizzasImages);
        pizzasRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        pizzasRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PizzaDetailActivity.class);
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZANO, position);
                getActivity().startActivity(intent);
            }
        });
        return pizzasRecycler;
    }
}