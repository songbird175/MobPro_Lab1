package celmafmary.mobprolab1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;


public class ChefMenu extends Fragment {

    DishDbHelper dbHelper;

    public ChefMenu(){
        //Empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_chef_menu, container, false);

        final ExpandableListView foodItemListView = (ExpandableListView) view.findViewById(R.id.chef_menu_list);
        ArrayList<FoodItem> arrayOfFood = ((MainActivity) getActivity()).getMenu();
        final ChefFoodItemAdapter adapter = new ChefFoodItemAdapter(getContext(), arrayOfFood,dbHelper);
        foodItemListView.setAdapter(adapter);

        Button addDish = (Button) view.findViewById(R.id.chef_add_dish_btn);
        addDish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Switches view to the Edit Menu tab
                FragmentTabHost host = (FragmentTabHost) getActivity().findViewById(R.id.tabhost);
                host.setCurrentTab(0);
            }
        });

        return view;
    }

}
