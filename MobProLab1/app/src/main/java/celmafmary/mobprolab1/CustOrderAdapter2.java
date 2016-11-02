package celmafmary.mobprolab1;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mary on 10/10/16.
 */

public class CustOrderAdapter2 extends BaseExpandableListAdapter {

    private ArrayList<FoodItem> dishes;
    private Context context;
    private List<String> listDataHeader = new ArrayList<>();
    private HashMap<String, List<String>> listDataChild = new HashMap<>();

    public CustOrderAdapter2(Context context, ArrayList<FoodItem> dishes) {
        super();
        this.dishes = dishes;
        this.context = context;
        for (int i = 0; i < dishes.size(); i++) { //loops through dishes
            String dishName = dishes.get(i).getName(); //extract dish name
            this.listDataHeader.add(dishName); //add it to headers list
            ArrayList<Ingredient> ingredients = dishes.get(i).getIngredients();
            ArrayList<String> listIngredTemp = new ArrayList<>();
            for (int n = 0; n < ingredients.size(); n++) { //loop through ingredients for each dish item
                String ingredTemp = ingredients.get(n).getName();
                listIngredTemp.add(ingredTemp); //put ingredient strings in temporary list
            }
            this.listDataChild.put(dishName, listIngredTemp); //map dish names to ingredients
        }
        Log.d("hashmap", this.listDataChild.toString());
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {
        String foodItemName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater Inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = Inflater.inflate(R.layout.cust_order_item, null);
        }

        //set header (foodItem name)
        TextView tvName = (TextView) convertView.findViewById(R.id.dish);
        tvName.setTypeface(null, Typeface.BOLD);
        tvName.setText(foodItemName);

        //gives INGREDIENTS button function -- creates listener that waits for a click and expands list
        View.OnClickListener expand_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) ((ExpandableListView) parent).collapseGroup(groupPosition);
                else ((ExpandableListView) parent).expandGroup(groupPosition, true);
            }
        };
        Button expand_btn = (Button) convertView.findViewById(R.id.cust_order_ingred);
        expand_btn.setOnClickListener(expand_listener); //sets up add button

        //gives DELETE button function -- creates listener that waits for a click and deletes dish
        View.OnClickListener delete_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodItem selectedDish = dishes.get(groupPosition); //use position as index to find selected foodItem from list dishes
                ArrayList<FoodItem> currentOrder = ((MainActivity) context).getCurrentOrder(); //get current order and store as list currentDishes
                currentOrder.remove(selectedDish); //add selected dish
                ((MainActivity) context).setCurrentOrder(currentOrder); //set current order to include latest dish
                notifyDataSetChanged();
                ((MainActivity) context).changeFragment(new CustomerOrder()); //do this because it doesn't really update otherwise
            }
        };
        Button delete_btn = (Button) convertView.findViewById(R.id.cust_del_dish_btn);
        delete_btn.setOnClickListener(delete_listener); //sets up add button

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String ingredientName = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater Inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = Inflater.inflate(R.layout.cust_order_item_ingred, null);
        }

        //set header (ingredient name)
        TextView tvName = (TextView) convertView.findViewById(R.id.cust_order_ingred_tv);
        tvName.setTypeface(null, Typeface.BOLD);
        tvName.setText(ingredientName);

        return convertView;
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int temp = this.listDataChild.get(this.listDataHeader.get(groupPosition)).size();
        return temp;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
