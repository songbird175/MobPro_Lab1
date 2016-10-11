package celmafmary.mobprolab1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<FoodItem> menuList = new ArrayList<>();
    ArrayList<Orders> orders = new ArrayList<>();
    ArrayList<FoodItem> cur_order = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //transaction.add(R.id.fragment_container, new MainActivityFragment());
        transaction.add(R.id.fragment_container, new MainActivityFragment());
        transaction.commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Context context = this;
        FoodItem chicken = new FoodItem("chicken", new ArrayList<Ingredient>());
        menuList.add(chicken);
    }

    //switches fragments, new fragment is input
    public void changeFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    public ArrayList<FoodItem> getMenu(){
        return menuList;
    }

    public void setMenu(ArrayList<FoodItem> newMenu){
        menuList = newMenu;
    }
    public void addToMenu(FoodItem foodItem){
        menuList.add(foodItem);
    }

    public ArrayList<Orders> getOrders(){
        return orders;
    }

    public void setOrders(ArrayList<Orders> newOrders){
        orders = newOrders;
    }

    public ArrayList<FoodItem> getCurrentOrder(){
        return cur_order;
    }

    public void setCurrentOrder(ArrayList<FoodItem> newItems){
        cur_order = newItems;

    }

}
