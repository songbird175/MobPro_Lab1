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

}