package celmafmary.mobprolab1;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChefOrderAdapterIngred extends ArrayAdapter<Ingredient> {

    public ChefOrderAdapterIngred(Context context, ArrayList<Ingredient> ingredients) {
        super(context, 0, ingredients);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Ingredient ingredient = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chef_order_ingredient, parent, false);
        }

        //set header (foodItem name)
        TextView tvName = (TextView) convertView.findViewById(R.id.chef_order_ingredient);
        tvName.setTypeface(null, Typeface.BOLD);
        tvName.setText(ingredient.getName());

        return convertView;
    }
}