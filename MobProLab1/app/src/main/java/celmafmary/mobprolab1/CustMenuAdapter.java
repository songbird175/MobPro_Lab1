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
 * Created by mary on 10/10/16.
 */

public class CustMenuAdapter extends ArrayAdapter<MenuList> {
    private ArrayList<MenuList> menuList;
    private Context context;

    public CustMenuAdapter(Context context, ArrayList<MenuList> menuList) {
        super(context, 0, menuList);
        this.menuList = menuList;
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        MenuList menuItem = (MenuList) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cust_menu_item, parent, false);
        }

        //put listener on DELETE button, calls delete method if clicked
        View.OnClickListener add_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) )
            }
        };
        TextView add_to_order = (TextView) convertView.findViewById(R.id.cust_menu_list_item);
        add_to_order.setOnClickListener(add_listener); //sets up switch button
        return convertView;
    }
}