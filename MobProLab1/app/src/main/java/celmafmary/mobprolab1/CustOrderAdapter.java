package celmafmary.mobprolab1;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by mary on 10/10/16.
 */
public class CustOrderAdapter extends ArrayAdapter<FoodItem> {

    private ArrayList<FoodItem> dishes;
    private Context context;

    public CustOrderAdapter(Context context, ArrayList<FoodItem> dishes) {
        super(context, 0, dishes);
        this.dishes = dishes;
        this.context = context;
    }

}