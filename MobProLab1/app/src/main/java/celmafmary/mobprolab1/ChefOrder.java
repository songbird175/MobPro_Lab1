package celmafmary.mobprolab1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class ChefOrder extends Fragment {
//    private Fragment TabHost = mTabHost;

    public ChefOrder() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chef_order, container, false);
        final Context context = getContext();

        final ArrayList<Orders> currentOrders = ((MainActivity) getActivity()).getOrders();
        OrderAdapter orderAdapter = new OrderAdapter(context, currentOrders);
        ExpandableListView ordersView = (ExpandableListView) view.findViewById(R.id.chef_order_list);
        ordersView.setAdapter(orderAdapter);

        return view;
    }
}
