package celmafmary.mobprolab1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class ChefMenu extends Fragment {

    DishDbHelper dbHelper;

    public ChefMenu(){
        //Empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        dbHelper = new DishDbHelper((getContext()));

        View view = inflater.inflate(R.layout.fragment_chef_menu, container, false);

        final ListView foodItemListView = (ListView) view.findViewById(R.id.chef_menu_listview);
        final ArrayList<FoodItem> arrayOfFood = dbHelper.getAll();
        final FoodItemAdapter adapter = new FoodItemAdapter(getContext(), arrayOfFood,dbHelper);
        foodItemListView.setAdapter(adapter);


        Button addDish = (Button) view.findViewById(R.id.chef_add_dish);

        addDish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((MainActivity) getActivity()).changeFragment(new TabFragment());
            }
        });


        return view;

    }

}
