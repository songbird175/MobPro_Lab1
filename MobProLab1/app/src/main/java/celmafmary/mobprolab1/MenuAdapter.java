package celmafmary.mobprolab1;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by mary on 10/8/16.
 */
public class MenuAdapter extends ArrayAdapter<MenuList>{
    private ArrayList<MenuList> menuList;
    private Context context;

    public MenuAdapter(Context context, ArrayList<MenuList> menuList){
        super(context, 0, menuList);
        this.menuList = menuList;
        this.context = context;
    }
}
