package celmafmary.mobprolab1;

import java.util.ArrayList;

/**
 * Created by mary on 10/8/16.
 */
public class MenuList {

    private ArrayList<FoodItem> menuList;

    private MenuList(ArrayList<FoodItem> menuList){
        this.menuList = menuList;
    }

    public ArrayList<FoodItem> getMenuList() {
        return menuList;
    }

    public void setMenuList(ArrayList<FoodItem> menuList) {
        this.menuList = menuList;
    }
}

