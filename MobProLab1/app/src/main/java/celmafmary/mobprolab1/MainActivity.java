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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, new MainActivityFragment());
        transaction.commit();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Context context = this;

////        ArrayList<Ingredient> ingredients = new ArrayList<>();
//        ArrayList<FoodItem> foodItems = new ArrayList<>();
//        ArrayList<Orders> orders = new ArrayList<>();
////        ArrayList<MenuList> menu = new ArrayList<>();
////        IngredientAdapter ingredientAdapter = new IngredientAdapter(this,ingredients);
//        FoodItemAdapter foodItemAdapter = new FoodItemAdapter(this,foodItems);
//        OrderAdapter orderAdapter = new OrderAdapter(this, orders);
////        ListView ingredientView = (ListView) findViewById(R.id.app_list);
////        ListView foodItemView = (ListView) findViewById(R.id.cust_cur_dish_list);
//        ListView ordersView = (ListView) findViewById(R.id.cust_cur_dish_list);
////        ingredientView.setAdapter(ingredientAdapter);
////        foodItemView.setAdapter(foodItemAdapter);
//        ordersView.setAdapter(orderAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeFragment(Fragment oldFragment, Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.remove(oldFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
