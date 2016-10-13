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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ChefEditMenu extends Fragment {

    private FragmentTabHost mTabHost;


    public ChefEditMenu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_chef_edit_menu, container, false);
        View view2 = inflater.inflate(R.layout.fragment_chef_menu,container, false);

        final ListView listView = (ListView) view.findViewById(R.id.listView);
        final IngredientAdapter adapter = new IngredientAdapter(getContext(), new ArrayList<Ingredient>());
        listView.setAdapter(adapter);

        final ListView listViewDish = (ListView) view2.findViewById(R.id.chef_menu_listview);
        final FoodItemAdapter foodItemAdapter = new FoodItemAdapter(getContext(), new ArrayList<FoodItem>());
        listViewDish.setAdapter(foodItemAdapter);

        final TextView dishName = (TextView) view.findViewById(R.id.dish_name);
        final FoodItem foodItem = new FoodItem("bob", new ArrayList<Ingredient>());


        Button addButton = (Button) view.findViewById(R.id.add_item);
        Button doneButton = (Button) view.findViewById(R.id.done_chef_menu);
        Button cancelButton = (Button) view.findViewById(R.id.cancel_chef_menu);
        TextView editDishName = (TextView) view.findViewById(R.id.dish_name);

        editDishName.setOnClickListener(new View.OnClickListener(){
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
                        foodItemAdapter.add(foodItem);

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
                        adapter.add(newIngredient);
                        //Todo: I don't think I am actually saving the ingredients
                    }
                });
                alertDialog.setButton(-2, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((MainActivity) getActivity()).addToMenu(foodItem);
                ((MainActivity) getActivity()).changeFragment(new ChefMenu());
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                ((MainActivity) getActivity()).changeFragment(new ChefMenu());
            }
        });


        return view;
    }




}
