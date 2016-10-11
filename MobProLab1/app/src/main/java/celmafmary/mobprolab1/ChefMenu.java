package celmafmary.mobprolab1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class ChefMenu extends Fragment {

    public ChefMenu(){
        //Empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.d("ChefMenu", "Running before view");
        View view = inflater.inflate(R.layout.fragment_chef_menu, container, false);

        final ListView foodItemListView = (ListView) view.findViewById(R.id.chef_menu_listview);
        final FoodItemAdapter adapter = new FoodItemAdapter(getContext(), new ArrayList<FoodItem>());
        foodItemListView.setAdapter(adapter);

        Log.d("ChefMenu","After view");



        return view;

    }

}
