package celmafmary.mobprolab1;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
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
    private DishDbHelper dbHelper;
    private List<String> listDataHeader = new ArrayList<>(); //the dish names
    private HashMap<String, List<String>> listDataChild = new HashMap<>(); //the dish ingredients

    public ChefFoodItemAdapter(Context context, ArrayList<FoodItem> dishes, DishDbHelper dishDbHelper){
        super();
        this.dishes = dishes;
        this.context = context;
        this.dbHelper = dishDbHelper;
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
        final FoodItem selectedDish = dishes.get(groupPosition);
        if (convertView == null) { //if no current view, create one
            LayoutInflater Inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = Inflater.inflate(R.layout.chef_food_item, null);
        }

        //set header (foodItem name)
        TextView tvName = (TextView) convertView.findViewById(R.id.chef_food_item_name);
        tvName.setTypeface(null, Typeface.BOLD);
        tvName.setText(selectedDish.getName());

        //gives INGREDIENTS button function -- creates listener that waits for a click and expands list
        View.OnClickListener expand_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isExpanded) ((ExpandableListView) parent).collapseGroup(groupPosition); //collapses LV if expanded
                else ((ExpandableListView) parent).expandGroup(groupPosition, true); //expand LV if collapsed
            }
        };

        Button expand_btn = (Button) convertView.findViewById(R.id.chef_expand_food_item_ingred_btn);
        expand_btn.setOnClickListener(expand_listener); //sets up expand button

        //gives EDIT button function -- creates listener that waits for a click and moves to ChefEditMenu with selectedDish as param
        Button editButton = (Button) convertView.findViewById(R.id.chef_edit_food_item_btn);
        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ChefEditMenu chefEditMenu = new ChefEditMenu();
                chefEditMenu.setFoodItem(selectedDish);
                ((MainActivity) context).changeFragment(chefEditMenu);
            }
        });

        //gives DELETE button function -- creates listener that waits for a click and deletes selectedDish from list
        Button deleteButton = (Button) convertView.findViewById(R.id.chef_delete_food_item_btn);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dishes.remove(groupPosition);
//                dbHelper.deleteRow(selectedDish);
                ((MainActivity) context).setMenu(dishes); //update menu for customer side
                notifyDataSetChanged();
                ((MainActivity) context).changeFragment(new ChefMenu()); //do this because it doesn't really update otherwise

            }
        });

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String ingredientName = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater Inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = Inflater.inflate(R.layout.chef_order_ingredient, null);
        }

        //set header (ingredient name)
        TextView tvName = (TextView) convertView.findViewById(R.id.chef_order_ingredient);
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