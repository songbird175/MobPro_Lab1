package celmafmary.mobprolab1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        //most of this should be created by the ChefMenu fragment
        ArrayList<MenuList> menuList = new ArrayList<>();
        final MenuAdapter menuAdapter = new MenuAdapter(context, menuList);
        ListView menuView = (ListView) view.findViewById(R.id.cust_menu_list);
        menuView.setAdapter(menuAdapter);

        return view;
    }
}
