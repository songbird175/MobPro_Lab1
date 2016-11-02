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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mary on 10/10/16.
 */

public class OrderAdapter extends BaseExpandableListAdapter {

    private ArrayList<Orders> orders;
    private Context context;
    private List<String> listDataHeader = new ArrayList<>(); //customer names
    private HashMap<String, List<FoodItem>> listDataChild = new HashMap<>(); //ties customer name to food items

    public OrderAdapter(Context context, ArrayList<Orders> orders) {
        super();
        this.orders = orders;
        this.context = context;
        for (int i = 0; i < orders.size(); i++) { //loops through dishes
            String customerName = orders.get(i).getCustomer(); //extract dish name
            this.listDataHeader.add(customerName); //add it to headers list
            ArrayList<FoodItem> foodItems = orders.get(i).getOrderList();
            ArrayList<FoodItem> listDishTemp = new ArrayList<>();
            for (int n = 0; n < foodItems.size(); n++) { //loop through ingredients for each dish item
                FoodItem dishTemp = foodItems.get(n);
                listDishTemp.add(dishTemp); //put ingredient strings in temporary list
            }
            this.listDataChild.put(customerName, listDishTemp); //map dish names to ingredients
        }
    }
    //Todo: Why does it crash when dishes/ingredients are expanded and you click COMPLETE?

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {
        String customerName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater Inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = Inflater.inflate(R.layout.chef_order_item, null);
        }

        //set header (customer name)
        TextView tvName = (TextView) convertView.findViewById(R.id.customer_name);
        tvName.setTypeface(null, Typeface.BOLD);
        tvName.setText(customerName);

        //gives DISHES button function -- creates listener that waits for a click and expands list
        View.OnClickListener expand_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) ((ExpandableListView) parent).collapseGroup(groupPosition);
                else ((ExpandableListView) parent).expandGroup(groupPosition, true);
            }
        };
        Button expand_btn = (Button) convertView.findViewById(R.id.chef_order_dish);
        expand_btn.setOnClickListener(expand_listener); //sets up add button

        //gives DELETE button function -- creates listener that waits for a click and deletes order
        View.OnClickListener delete_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Orders selectedOrder = orders.get(groupPosition); //use position as index to find selected order from list of orders
                ArrayList<Orders> currentOrders = ((MainActivity) context).getOrders(); //get current orders and store them as list
                currentOrders.remove(selectedOrder); //add selected dish
                ((MainActivity) context).setOrders(currentOrders); //set current order to include latest dish
                notifyDataSetChanged();
                ((MainActivity) context).changeFragment(new ChefOrder()); //do this because it doesn't really update otherwise
            }
        };
        Button delete_btn = (Button) convertView.findViewById(R.id.chef_comp_dish_btn);
        delete_btn.setOnClickListener(delete_listener); //sets up add button

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Orders selectedOrder = orders.get(groupPosition); //use position as index to find selected order from list of orders
        FoodItem selectedDish = selectedOrder.getOrderList().get(childPosition);
        String dishName = selectedDish.getName();

        if (convertView == null) {
            LayoutInflater Inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = Inflater.inflate(R.layout.chef_order_food_item, null);
        }

        //set header (dish name)
        TextView tvName = (TextView) convertView.findViewById(R.id.chef_order_dish_name);
        tvName.setTypeface(null, Typeface.BOLD);
        tvName.setText(dishName);

        //create list view for ingredients (ingredient list)
        ArrayList<Ingredient> ingredientList = selectedDish.getIngredients();
        Log.d("INGREDS",ingredientList.toString());
        ChefOrderAdapterIngred ingredientAdapter = new ChefOrderAdapterIngred(context,ingredientList);
        ListView ingredientLV = (ListView) convertView.findViewById(R.id.chef_order_ingredient_list);
        ingredientLV.setAdapter(ingredientAdapter);

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
