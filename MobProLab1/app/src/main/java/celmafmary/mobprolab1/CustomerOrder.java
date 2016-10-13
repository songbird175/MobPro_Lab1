package celmafmary.mobprolab1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomerOrder extends Fragment {


    public CustomerOrder() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_customer_order, container, false);
        final Context context = getContext();

        final ArrayList<FoodItem> currentOrder = ((MainActivity) getActivity()).getCurrentOrder();
        CustOrderAdapter2 orderAdapter = new CustOrderAdapter2(context, currentOrder);
        ExpandableListView ordersView = (ExpandableListView) view.findViewById(R.id.cust_order_list);
        ordersView.setAdapter(orderAdapter);

        //gives ADD button function -- creates listener that waits for a click and adds a new list item to cur_dish_list
        View.OnClickListener add_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFragment(new CustomerMenu()); //adding happens in CustomerMenu
            }
        };
        Button add_btn = (Button) view.findViewById(R.id.cust_add_dish_btn);
        add_btn.setOnClickListener(add_listener); //sets up add button

        //gives ADD button function -- creates listener that waits for a click and adds a new list item to cur_dish_list
        View.OnClickListener place_order_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceOrder(currentOrder); //creates popup that gets customer name and updates currentOrders and the orders list in MA
            }
        };
        Button place_order_btn = (Button) view.findViewById(R.id.place_order_btn);
        place_order_btn.setOnClickListener(place_order_listener); //sets up add button

        return view;
    }

    //Gives EDIT button function -- creates listener that waits for a click and edits the selected text view
    public void PlaceOrder(final ArrayList<FoodItem> currentOrder) {
        final AlertDialog.Builder popup_maker = new AlertDialog.Builder(getContext());
        popup_maker //configure popup
                .setTitle("Ready to order?")
                .setMessage("Enter your name below and enjoy these stale chips while we heat up your delicious but currently frozen meal.")
                .setCancelable(true);
        final EditText field = new EditText(getContext());
        popup_maker.setView(field);
        popup_maker.setNegativeButton("Get off my back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //nothing is saved, they get out of popup
            }
        });
        popup_maker.setPositiveButton("Place order", new DialogInterface.OnClickListener() { //if they hit SET, it saves their change
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String custName = field.getText().toString(); //get customer name, necessary for order
                ArrayList<Orders> ordersList = ((MainActivity) getActivity()).getOrders(); //get orders list
                Orders newOrder = new Orders(custName, currentOrder); //make currentOrder into an Order (instead of a list of Food Items)
                ordersList.add(newOrder); //add currentOrder, in the form of newOrder, to list of orders
                ((MainActivity) getActivity()).setOrders(ordersList); //update the list of orders in MA
                ((MainActivity) getActivity()).setCurrentOrder(new ArrayList<FoodItem>()); //clear currentOrder, because it's been place
                ((MainActivity) getActivity()).changeFragment(new MainActivityFragment()); //move to some new fragment
            }
        });
        AlertDialog popup = popup_maker.create(); //make it
        popup.show(); //show it
    }
}
