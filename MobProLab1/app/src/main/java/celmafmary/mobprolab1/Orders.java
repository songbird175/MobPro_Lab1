package celmafmary.mobprolab1;

import java.util.ArrayList;

/**
 * Created by celina on 10/6/16.
 */
public class Orders {
    private String customer;
    private ArrayList<FoodItem> orderList;

    private Orders(String customer, ArrayList<FoodItem> orderList){
        this.customer = customer;
        this.orderList = orderList;

    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ArrayList<FoodItem> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<FoodItem> orderList) {
        this.orderList = orderList;
    }
}
