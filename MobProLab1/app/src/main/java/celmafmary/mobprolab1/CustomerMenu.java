package celmafmary.mobprolab1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class CustomerMenu extends Fragment {

    public CustomerMenu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_menu, container, false);
        Context context = getContext();

        ArrayList<FoodItem> menuList = ((MainActivity) getActivity()).getMenu();
        final CustFoodItemAdapter menuAdapter = new CustFoodItemAdapter(context, menuList);
        ListView menuView = (ListView) view.findViewById(R.id.cust_menu_list);
        menuView.setAdapter(menuAdapter);

        FoodItem test = new FoodItem("Yum", new ArrayList<Ingredient>());
        menuAdapter.add(test);

        //gives CANCEL button function -- creates listener that waits for a click and moves to CustomerOrder
        View.OnClickListener cancel_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFragment(new CustomerOrder());
            }
        };
        Button cancel_btn = (Button) view.findViewById(R.id.cust_cancel_add_menu_item);
        cancel_btn.setOnClickListener(cancel_listener); //sets up add button


        return view;
    }
}
