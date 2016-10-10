package celmafmary.mobprolab1;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mary on 10/8/16.
 */
public class IngredientAdapter extends ArrayAdapter<Ingredient> {

    private ArrayList<Ingredient> ingredientList;
    private Context context;

    public IngredientAdapter(Context context, ArrayList<Ingredient> ingredientItem){
        super(context, 0, ingredientItem);
        this.ingredientList = ingredientItem;
        this.context = context;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final Ingredient ingredientItem = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingredient_item, parent, false);
        }

        final TextView individual_ingredient = (TextView) convertView.findViewById(R.id.individual_ingredient);
        Button deleteButton = (Button) convertView.findViewById(R.id.delete_ingredient);
        Button editButton = (Button) convertView.findViewById(R.id.edit_ingredient);
        individual_ingredient.setText(ingredientItem.getName());




        editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
                alertDialog.setTitle("Edit Ingredient");
                final EditText input = new EditText(view.getContext());
                alertDialog.setView(input);

                alertDialog.setButton(-1, "Done", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Log.d("IngredientAdapter", input.getText().toString());
                        individual_ingredient.setText(input.getText().toString());
                        ingredientItem.setName(input.getText().toString());
                    }
                });
                alertDialog.setButton(-2, "Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){

                    }
                });
                alertDialog.show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ingredientList.remove(position);
                notifyDataSetChanged();

            }
        });
        return convertView;

}
}
