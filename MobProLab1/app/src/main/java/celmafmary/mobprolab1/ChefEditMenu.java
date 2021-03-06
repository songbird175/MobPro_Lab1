package celmafmary.mobprolab1;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ChefEditMenu extends Fragment {
    DishDbHelper dbHelper;
    FoodItem foodItem;
    FoodItemAdapter foodItemAdapter;
    Boolean isNewFoodItem;

    public ChefEditMenu(){

    }


    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_chef_edit_menu, container, false);
        View view2 = inflater.inflate(R.layout.fragment_chef_menu,container, false);

        dbHelper = new DishDbHelper(getContext());
        final ArrayList<FoodItem> arrayOfFood = dbHelper.getAll();
        final TextView dishName = (TextView) view.findViewById(R.id.chef_edit_menu_dish_name);

        final ListView listView = (ListView) view.findViewById(R.id.chef_edit_menu_LV);
        if (foodItem != null){
            dishName.setText(foodItem.getName());
            this.isNewFoodItem = false;
        } else {
            Log.d("ChefEditMent", "FoodItem is Null!!!!");
            foodItem = new FoodItem("NoName", new ArrayList<Ingredient>());
            this.isNewFoodItem = true;
        }

        final IngredientAdapter adapter = new IngredientAdapter(getContext(), foodItem.getIngredients());
        listView.setAdapter(adapter);

        final ExpandableListView listViewDish = (ExpandableListView) view2.findViewById(R.id.chef_menu_list);
        final ChefFoodItemAdapter foodItemAdapter = new ChefFoodItemAdapter(getContext(), arrayOfFood, dbHelper);
        listViewDish.setAdapter(foodItemAdapter);

        Button addButton = (Button) view.findViewById(R.id.add_item_btn);
        Button doneButton = (Button) view.findViewById(R.id.done_chef_menu_btn);
        Button cancelButton = (Button) view.findViewById(R.id.cancel_chef_menu_btn);

        dishName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
                alertDialog.setTitle("Set Dish Name");
                final EditText input = new EditText(view.getContext());
                alertDialog.setView(input);

                alertDialog.setButton(-1, "Done", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dishName.setText(input.getText().toString());
                        foodItem.setName(input.getText().toString());
//                        foodItemAdapter.add(foodItem);

                    }
                });
                alertDialog.setButton(-2, "Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogy, int which){
                    }
                });
                alertDialog.show();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("ChefEditMenuAdd", "Is button registering?");
                AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
                alertDialog.setTitle("Add Ingredient");
                final EditText input = new EditText(view.getContext());
                alertDialog.setView(input);

                alertDialog.setButton(-1, "Done", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Ingredient newIngredient = new Ingredient(input.getText().toString());
                        foodItem.addIngredients(newIngredient);
                        Log.d("ChefEdit", "add to food Item");
                        //Todo: Use fix add ingredient function
                        adapter.notifyDataSetChanged();
                        //adapter.add(newIngredient);
                        //Todo: I don't think I am actually saving the ingredients
                    }
                });
                alertDialog.setButton(-2, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}});
                alertDialog.show();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DishDbHelper dbHelper1 = new DishDbHelper(getContext());
                if (isNewFoodItem) {
                    dbHelper1.addToMenu(foodItem);
                    arrayOfFood.add(foodItem);
                } else {
                    dbHelper1.updateArray(foodItem.getId(), foodItem);
                    arrayOfFood.remove((int) foodItem.getId());
                    arrayOfFood.set((int) foodItem.getId() - 1, foodItem);
                }
                ((MainActivity) getActivity()).setMenu(arrayOfFood);
                foodItemAdapter.notifyDataSetChanged();
                //Switches view to the Menu tab
                FragmentTabHost host = (FragmentTabHost) getActivity().findViewById(R.id.tabhost);
                host.setCurrentTab(1);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                FragmentTabHost host = (FragmentTabHost) getActivity().findViewById(R.id.tabhost);
                host.setCurrentTab(1);
            }
        });

        return view;
    }




}
