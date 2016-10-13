package celmafmary.mobprolab1;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustFoodItemAdapter2 extends BaseExpandableListAdapter {

    private ArrayList<FoodItem> dishes;
    private Context context;
    private List<String> listDataHeader = new ArrayList<>();
    private HashMap<String, List<String>> listDataChild = new HashMap<>();
//    private ArrayList<FoodItem> checkedDishesName = new ArrayList<>();
    private List<String> checkedDishesName = new ArrayList<>(); //keeps track of names of checked items
    private HashMap<String, ArrayList<Ingredient>> checkedDishes = new HashMap<>(); //ties checked names to ingredients
    private HashMap<String, Integer> orderIndices = new HashMap<>(); //ties checked names to indices

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

        CheckBox dish_checked = (CheckBox) convertView.findViewById(R.id.cust_add_dish_checkbox);
        dish_checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               FoodItem selectedDish = dishes.get(groupPosition); //use position as index to find selected foodItem from list dishes
               ArrayList<FoodItem> currentOrder = ((MainActivity) context).getCurrentOrder(); //get current order and store as list currentDishes
               if (isChecked == true){
                   currentOrder.add(selectedDish); //add selected dish
                   checkedDishesName.add(selectedDish.getName());
                   checkedDishes.put(selectedDish.getName(),selectedDish.getIngredients());
                   orderIndices.put(selectedDish.getName(),currentOrder.indexOf(selectedDish));
               }
               else if (isChecked == false) {
                   currentOrder.remove(selectedDish); //remove selected dish
                   checkedDishesName.remove(selectedDish.getName());
                   checkedDishes.remove(selectedDish.getName());
                   orderIndices.remove(selectedDish.getName());
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

        final CheckBox ingred_checked = (CheckBox) convertView.findViewById(R.id.cust_add_ingred_checkbox);
        final FoodItem selectedDish = dishes.get(groupPosition); //use position as index to find selected foodItem from list dishes

        if (checkedDishesName.contains(selectedDish.getName())){} //unchecks checkbox if dish isn't also selected (so we don't add ingredients below without a dish)
        else {ingred_checked.setChecked(false);}

        ingred_checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (checkedDishesName.contains(selectedDish.getName())){ //only make changes if the dish is checked
                   ArrayList<FoodItem> currentOrder = ((MainActivity) context).getCurrentOrder(); //get current order and store as list currentDishes
                   ArrayList<Ingredient> checkedIngreds = checkedDishes.get(selectedDish.getName()); //get checked dish's ingredients
                   Ingredient selectedIng = dishes.get(groupPosition).getIngredients().get(childPosition); //get selected ingredient

                   if (isChecked == false){
                       if (checkedIngreds.contains(selectedIng)){} //if the ingredient is already part of the dish, do nothing (otherwise would have 2 because it starts with 1)
                       else {
                           checkedIngreds.add(selectedIng); //otherwise, add it
                       }}
                   else if (isChecked == true) {
                       checkedIngreds.remove(selectedIng); //remove the dish -- it must have already been added so it can be removed
                   }
                   checkedDishes.put(selectedDish.getName(),checkedIngreds); //put changed ingredient list back in
                   selectedDish.setIngredients(checkedIngreds); //set updated ingredients to blank dish
                   int dishIndex = orderIndices.get(selectedDish.getName());
                   currentOrder.set(dishIndex, selectedDish);
                   ((MainActivity) context).setCurrentOrder(currentOrder); //set current order to include latest dish
                   notifyDataSetChanged();
               }
               else {
                   ingred_checked.setChecked(false);
               }
           }
        });

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
