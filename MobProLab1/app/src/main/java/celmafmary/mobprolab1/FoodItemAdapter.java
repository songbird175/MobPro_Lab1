package celmafmary.mobprolab1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by mafaldaborges on 10/6/16.
 */
public class FoodItemAdapter extends ArrayAdapter{

    private ArrayList<Ingredient> ingredients;
    private Context context;

    public FoodItemAdapter(Context context, ArrayList<Ingredient> ingredients){
        super(context, 0, ingredients);
        this.ingredients = ingredients;
        this.context = context;

    }

}
