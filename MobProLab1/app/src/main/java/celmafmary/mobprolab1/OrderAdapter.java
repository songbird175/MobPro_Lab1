package celmafmary.mobprolab1;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by mafaldaborges on 10/6/16.
 */
public class OrderAdapter extends ArrayAdapter<Orders> {
    private ArrayList<Orders> orders;
    private Context context;

    public OrderAdapter(Context context, ArrayList<Orders> orders){
        super(context, 0, orders);
        this.orders = orders;
        this.context = context;

    }
}
