package celmafmary.mobprolab1;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


    public ChefEditMenu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_chef_edit_menu, container, false);

        final ListView listView = (ListView) view.findViewById(R.id.listView);
        final IngredientAdapter adapter = new IngredientAdapter(getContext(), new ArrayList<Ingredient>());
        listView.setAdapter(adapter);



        Button addButton = (Button) view.findViewById(R.id.add_item);
        TextView editDish = (TextView) view.findViewById(R.id.dish_name);


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



        return view;
    }




}
