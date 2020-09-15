package com.frv.bitsandpizzas;

public class Pizza {
    private String name;
    private int resource_img_id;

    public static final Pizza[] pizzas = {
            new Pizza("Diavolo", R.drawable.diavolo),
            new Pizza("Funghi", R.drawable.funghi)
    };

    public Pizza(String name, int resource_img_id) {
        this.name = name;
        this.resource_img_id = resource_img_id;
    }

    public String getName() {
        return name;
    }

    public int getResource_img_id() {
        return resource_img_id;
    }
}
