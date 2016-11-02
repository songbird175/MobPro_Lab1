package celmafmary.mobprolab1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CustTabFragment extends Fragment {
    private FragmentTabHost mTabHost;

    //Mandatory Constructor
    public CustTabFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);

        mTabHost = (FragmentTabHost) rootView.findViewById(R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.fragment_container);

        mTabHost.addTab(mTabHost.newTabSpec("custMenu").setIndicator("Menu"),
                CustomerMenu.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("custOrder").setIndicator("Order"),
                CustomerOrder.class, null);


        return rootView;
    }
}
