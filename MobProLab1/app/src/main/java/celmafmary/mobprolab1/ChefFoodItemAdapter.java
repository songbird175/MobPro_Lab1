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

public class ChefFoodItemAdapter extends BaseExpandableListAdapter {

    private ArrayList<FoodItem> dishes; //the menu
    private Context context;
    private List<String> listDataHeader = new ArrayList<>(); //the dish names
    private HashMap<String, List<String>> listDataChild = new HashMap<>(); //the dish ingredients
    private List<String> checkedDishesName = new ArrayList<>(); //keeps track of the names of checked items
    private HashMap<String, ArrayList<Ingredient>> checkedDishes = new HashMap<>(); //ties checked names to ingredients
    private HashMap<String, Integer> orderIndices = new HashMap<>(); //ties checked names to their indices in currentOrder

    public ChefFoodItemAdapter(Context context, ArrayList<FoodItem> dishes){
        super();
        this.dishes = dishes;
        this.context = context;
        for (int i = 0; i < dishes.size(); i++){ //loops through dishes
            String dishName = dishes.get(i).getName(); //extract dish name
            this.listDataHeader.add(dishName); //add it to headers list
            ArrayList<Ingredient> ingredients = dishes.get(i).getIngredients(); //extract dish ingredients
            ArrayList<String> listIngredTemp = new ArrayList<>(); //create temporary ingredient list (new one each time)
            for (int n = 0; n < ingredients.size(); n++){ //loop through ingredients for each dish item
                String ingredTemp = ingredients.get(n).getName(); //extract ingredient name, hold in temporary variable
                listIngredTemp.add(ingredTemp); //put ingredient strings in temporary list
            }
            this.listDataChild.put(dishName, listIngredTemp); //map dish names to ingredients
        }
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {
        String foodItemName = (String) getGroup(groupPosition); //get dish name based on location (group name) to put in TextView
        if (convertView == null) { //if no current view, create one
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
                if(isExpanded) ((ExpandableListView) parent).collapseGroup(groupPosition); //collapses LV if expanded
                else ((ExpandableListView) parent).expandGroup(groupPosition, true); //expand LV if collapsed
            }
        };
        Button expand_btn = (Button) convertView.findViewById(R.id.cust_expand_dish);
        expand_btn.setOnClickListener(expand_listener); //sets up add button

        //gives ADD checkbox function -- creates listener that waits for a click and either removes or adds the dish to currentOrder plus updates a bunch of lists and hash maps
        CheckBox dish_checked = (CheckBox) convertView.findViewById(R.id.cust_add_dish_checkbox);
        dish_checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                FoodItem selectedDish = dishes.get(groupPosition); //use position as index to find selected foodItem from list dishes
                ArrayList<FoodItem> currentOrder = ((MainActivity) context).getCurrentOrder(); //get current order and store as list currentDishes
                if (isChecked == true){ //if it's checked, continue
                    currentOrder.add(selectedDish); //add selected dish to current order
                    checkedDishesName.add(selectedDish.getName()); //add dish name to list of checked items (so we can keep track of what's checked and what's not)
                    checkedDishes.put(selectedDish.getName(),selectedDish.getIngredients()); //add dish name and ingredients to map, keep track of checked dishes' ingredients
                    orderIndices.put(selectedDish.getName(),currentOrder.indexOf(selectedDish)); //add dish name and index of dish in currentOrder to keep track of checked dishes' indices (so we can make changes)
                }
                else if (isChecked == false) {
                    currentOrder.remove(selectedDish); //opposite of all the stuff above
                    checkedDishesName.remove(selectedDish.getName()); //ditto
                    checkedDishes.remove(selectedDish.getName()); //ditto
                    orderIndices.remove(selectedDish.getName()); //ditto
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

        //create checkbox and check to see if the ingredient's dish is checked (if it's not, the ingredient can't be checked)
        final CheckBox ingred_checked = (CheckBox) convertView.findViewById(R.id.cust_add_ingred_checkbox);
        final FoodItem selectedDish = dishes.get(groupPosition); //use position as index to find selected foodItem from list dishes
        if (checkedDishesName.contains(selectedDish.getName())){}
        else {ingred_checked.setChecked(false);} //unchecks checkbox if dish isn't also selected (so we don't add ingredients below without a dish)

        //gives EXCLUDE checkbox function -- creates listener that waits for a click and either removes the ingredient, adds it back, or does nothing
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
                        checkedIngreds.remove(selectedIng); //remove the ingredient
                    }
                    checkedDishes.put(selectedDish.getName(),checkedIngreds); //put changed ingredient list back in hash map of dish names and their ingredients
                    selectedDish.setIngredients(checkedIngreds); //set updated ingredients to untouched dish
                    int dishIndex = orderIndices.get(selectedDish.getName()); //get index of dish from hash map of dish names and their indices
                    currentOrder.set(dishIndex, selectedDish); //use index to update dish in currentOrder list
                    ((MainActivity) context).setCurrentOrder(currentOrder); //reset current order to include latest dish
                    notifyDataSetChanged();
                }
                else {
                    ingred_checked.setChecked(false); //if the dish isn't selected, uncheck the ingredient
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