package celmafmary.mobprolab1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustFoodItemAdapter extends ArrayAdapter<FoodItem> {

    private ArrayList<FoodItem> dishes;
    private Context context;

    public CustFoodItemAdapter(Context context, ArrayList<FoodItem> dishes){
        super(context, 0, dishes);
        this.dishes = dishes;
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        FoodItem foodItem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cust_menu_item, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.cust_menu_list_item);
        tvName.setText(foodItem.getName()); //make each text view show name of foodItem

        View.OnClickListener add_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodItem selectedDish = dishes.get(position); //use position as index to find selected foodItem from list dishes
                ArrayList<FoodItem> currentOrder = ((MainActivity) context).getCurrentOrder(); //get current order and store as list currentDishes
                currentOrder.add(selectedDish); //add selected dish
                ((MainActivity) context).setCurrentOrder(currentOrder); //set current order to include latest dish
                ((MainActivity) context).changeFragment(new CustomerOrder()); //go back to order page
                notifyDataSetChanged();
            }
        };
        tvName.setOnClickListener(add_listener); //put listener on textviews
        return convertView;
    }
}
