package celmafmary.mobprolab1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //Gives CHEF button function -- creates listener that waits for a click and switches to ChefOrder when clicked
        View.OnClickListener chef_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFragment(new TabFragment()); //calls MainActivity method to switch fragments
            }
        };
        Button chef_btn = (Button) view.findViewById(R.id.Chef_btn);
        chef_btn.setOnClickListener(chef_listener); //sets up Chef button

        //Gives CUSTOMER button function -- creates listener that waits for a click and switches to CustomerOrder when clicked
        View.OnClickListener cust_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((MainActivity) getActivity()).changeFragment(cust_fragment, cust_fragment); //calls MainActivity method to switch
                ((MainActivity) getActivity()).changeFragment(new CustTabFragment()); //calls MainActivity method to switch fragments
            }
        };
        Button cust_btn = (Button) view.findViewById(R.id.Customer_btn);
        cust_btn.setOnClickListener(cust_listener); //sets up Customer button

        return view;
    }
}
