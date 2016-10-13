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
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        //retrieve menuList from Main Activity, set to corresponding adapter and list view
        ArrayList<FoodItem> menuList = ((MainActivity) getActivity()).getMenu();
        final CustFoodItemAdapter2 menuAdapter = new CustFoodItemAdapter2(context, menuList);
        ExpandableListView menuView = (ExpandableListView) view.findViewById(R.id.cust_menu_list);
        menuView.setAdapter(menuAdapter);


        //gives CANCEL button function -- creates listener that waits for a click and moves to CustomerOrder
        View.OnClickListener done_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFragment(new CustTabFragment());
            }
        };
        Button done_btn = (Button) view.findViewById(R.id.cust_cancel_add_menu_item);
        done_btn.setOnClickListener(done_listener); //sets up add button

        return view;
    }
}
