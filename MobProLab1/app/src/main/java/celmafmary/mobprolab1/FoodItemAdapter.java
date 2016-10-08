package celmafmary.mobprolab1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by mafaldaborges on 10/6/16.
 */
public class FoodItemAdapter extends ArrayAdapter{

    private ArrayList<FoodItem> dishes;
    private Context context;

    public FoodItemAdapter(Context context, ArrayList<FoodItem> dishes){
        super(context, 0, dishes);
        this.dishes = dishes;
        this.context = context;

    }
}
