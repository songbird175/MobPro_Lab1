package celmafmary.mobprolab1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mafaldaborges on 10/6/16.
 */
public class FoodItemAdapter extends ArrayAdapter<FoodItem>{

    private ArrayList<FoodItem> dishList;
    private Context context;

    public FoodItemAdapter(Context context, ArrayList<FoodItem> dishItem){
        super(context, 0, dishItem);
        this.dishList = dishItem;
        this.context = context;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final FoodItem foodItem = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chef_food_item, parent, false);
        }

        final TextView individual_food_item = (TextView) convertView.findViewById(R.id.individual_food_item);
        individual_food_item.setText(foodItem.getName());
        Button deleteButton = (Button) convertView.findViewById(R.id.delete_food_item);
        Button editButton = (Button) convertView.findViewById(R.id.edit_food_item);

        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Todo switch to ChefEditMenu Fragment
                //((MainActivity) getActivity()).changeFragment(new ChefEditMenu());
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dishList.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }


}
