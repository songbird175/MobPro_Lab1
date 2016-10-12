package celmafmary.mobprolab1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
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

        //gives INGREDIENTS button function -- creates listener that waits for a click and expands list
        View.OnClickListener delete_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<FoodItem> currentOrder = ((MainActivity) context).getCurrentOrder(); //get current order and store as list currentDishes
                currentOrder.remove(position); //add selected dish
                ((MainActivity) context).setCurrentOrder(currentOrder); //set current order to include latest dish
                notifyDataSetChanged();
            }
        };
        Button delete_btn = (Button) convertView.findViewById(R.id.cust_del_dish_btn);
        delete_btn.setOnClickListener(delete_listener); //sets up add button

        return convertView;
    }
}
//cust_del_dish_btn