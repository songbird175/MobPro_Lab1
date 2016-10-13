package celmafmary.mobprolab1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TabFragment extends Fragment {
    private FragmentTabHost mTabHost;

    //Mandatory Constructor
    public TabFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //Code taken largely from the Android documentation, specifically at https://developer.android.com/reference/android/support/v4/app/FragmentTabHost.html
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);


        mTabHost = (FragmentTabHost) rootView.findViewById(R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.fragment_container);

        //Yay tabs!
        mTabHost.addTab(mTabHost.newTabSpec("chefEdit").setIndicator("Edit Menu"),
                ChefEditMenu.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("chefMenu").setIndicator("Menu"),
                ChefMenu.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("chefOrders").setIndicator("Orders"),
                ChefOrder.class, null);


        return rootView;
    }

    public void setCurrentTab(int tab_index) {
        //Gives us a way to make buttons connect to a certain tab
        mTabHost = (FragmentTabHost)getActivity().findViewById(android.R.id.tabhost);
        mTabHost.setCurrentTab(tab_index);
    }
}
