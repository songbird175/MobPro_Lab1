package celmafmary.mobprolab1;

/**
 * Created by celina on 10/6/16.
 */
public class Ingredient {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    private Ingredient(String name){
        this.name = name;

    }
}
