package celmafmary.mobprolab1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

    public View getView(final int position, View convertView, ViewGroup parent) {
        FoodItem foodItem = (FoodItem) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cust_order_item, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.dish);
        tvName.setText(foodItem.getName());

        return convertView;
    }
}