package celmafmary.mobprolab1;

import java.util.ArrayList;

/**
 * Created by celina on 10/6/16.
 */
public class FoodItem {
    private String name;
    private ArrayList<String> ingredients;

    private FoodItem(String name, ArrayList<String> ingredients){
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}
