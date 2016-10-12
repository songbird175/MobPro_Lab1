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
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustFoodItemAdapter2 extends BaseExpandableListAdapter {

    private ArrayList<FoodItem> dishes;
    private Context context;
    private List<String> listDataHeader = new ArrayList<>();
//    private List<String> listIngredTemp = new ArrayList<>();
    private HashMap<String, List<String>> listDataChild = new HashMap<>();

    public CustFoodItemAdapter2(Context context, ArrayList<FoodItem> dishes){
        super();
        this.dishes = dishes;
        this.context = context;
        for (int i = 0; i < dishes.size(); i++){ //loops through dishes
            String dishName = dishes.get(i).getName(); //extract dish name
            this.listDataHeader.add(dishName); //add it to headers list
            ArrayList<Ingredient> ingredients = dishes.get(i).getIngredients();
            ArrayList<String> listIngredTemp = new ArrayList<>();
            for (int n = 0; n < ingredients.size(); n++){ //loop through ingredients for each dish item
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
            convertView = Inflater.inflate(R.layout.cust_menu_item, null);
        }

        //set header (foodItem name)
        TextView tvName = (TextView) convertView.findViewById(R.id.cust_menu_list_item);
        tvName.setTypeface(null, Typeface.BOLD);
        tvName.setText(foodItemName);

        //gives INGREDIENTS button function -- creates listener that waits for a click and expands list
        View.OnClickListener expand_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isExpanded) ((ExpandableListView) parent).collapseGroup(groupPosition);
                else ((ExpandableListView) parent).expandGroup(groupPosition, true);
            }
        };
        Button expand_btn = (Button) convertView.findViewById(R.id.cust_expand_dish);
        expand_btn.setOnClickListener(expand_listener); //sets up add button

//        //gives ADD button function -- creates listener that waits for a click and adds dish to currentOrder
//        View.OnClickListener add_listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FoodItem selectedDish = dishes.get(groupPosition); //use position as index to find selected foodItem from list dishes
//                ArrayList<FoodItem> currentOrder = ((MainActivity) context).getCurrentOrder(); //get current order and store as list currentDishes
//                currentOrder.add(selectedDish); //add selected dish
//                ((MainActivity) context).setCurrentOrder(currentOrder); //set current order to include latest dish
//                ((MainActivity) context).changeFragment(new CustomerOrder()); //go back to order page
//                notifyDataSetChanged();
//            }
//        };
//        Button add_btn = (Button) convertView.findViewById(R.id.cust_add_dish_exp_btn);
//        add_btn.setOnClickListener(add_listener); //sets up add button

        CheckBox dish_checked = (CheckBox) convertView.findViewById(R.id.cust_add_dish_checkbox);
        dish_checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               FoodItem selectedDish = dishes.get(groupPosition); //use position as index to find selected foodItem from list dishes
               ArrayList<FoodItem> currentOrder = ((MainActivity) context).getCurrentOrder(); //get current order and store as list currentDishes
               if (isChecked == true){
                   currentOrder.add(selectedDish); //add selected dish
               }
               else if (isChecked == false) {
                   currentOrder.remove(selectedDish); //remove selected dish
               }
               ((MainActivity) context).setCurrentOrder(currentOrder); //set current order to include latest dish
               notifyDataSetChanged();
           }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String ingredientName = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater Inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = Inflater.inflate(R.layout.cust_menu_item_ingred, null);
        }

        //set header (ingredient name)
        TextView tvName = (TextView) convertView.findViewById(R.id.cust_item_ingred);
        tvName.setTypeface(null, Typeface.BOLD);
        tvName.setText(ingredientName);

//        CheckBox ingred_checked = (CheckBox) convertView.findViewById(R.id.cust_add_ingred_checkbox);
//        ingred_checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//           @Override
//           public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//               FoodItem selectedDish = dishes.get(groupPosition); //use position as index to find selected foodItem from list dishes
//               ArrayList<FoodItem> currentOrder = ((MainActivity) context).getCurrentOrder(); //get current order and store as list currentDishes
//               if (currentOrder.contains(selectedDish)) { //basically, only make changes if the dish is checked
//                   Ingredient selectedIng = selectedDish.getIngredients().get(childPosition); //get selected ingredient for that dish
//                   ArrayList<Ingredient> dishIngreds = selectedDish.getIngredients(); //get list of ingredients for that dish
//                   if (isChecked == true){
//                       if (dishIngreds.get(childPosition) == selectedIng){} //if the ingredient is already part of the dish, do nothing (otherwise would have 2 because it starts with 1)
//                       else {
//                           dishIngreds.add(selectedIng); //otherwise, add it
//                       }}
//                   else if (isChecked == false) {
//                       dishIngreds.remove(childPosition); //remove the dish -- it must have already been added so it can be removed
//                   }
////                   selectedDish.setIngredients(dishIngreds);
//                   currentOrder.get(groupPosition).setIngredients(dishIngreds);
//                   ((MainActivity) context).setCurrentOrder(currentOrder); //set current order to include latest dish
//                   notifyDataSetChanged();
//           }}
//        });

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
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosition);    }

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
