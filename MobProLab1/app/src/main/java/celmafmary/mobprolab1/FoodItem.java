package celmafmary.mobprolab1;

import java.util.ArrayList;

/**
 * Created by celina on 10/6/16.
 */
public class FoodItem {
    private String name;
    private ArrayList<Ingredient> ingredients;

    public FoodItem(String name, ArrayList<Ingredient> ingredients){
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
