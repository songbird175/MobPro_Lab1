package celmafmary.mobprolab1;

import android.provider.BaseColumns;

/**
 * Created by mafaldaborges on 10/11/16.
 */
public class DishTable {

    private DishTable(){}

    public static class FeedEntryDish implements BaseColumns {
        public static final String DISH_TABLE_NAME = "dish_table";
        public static final String DISH_FOOD_COLUMN_NAME = "dish_food";
        public static final String DISH_ING_COLUMN_NAME = "dish_ing";

        public static final String TEXT_TYPE = " TEXT";
        public static final String INT_TYPE = " INTEGER";
        public static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DISH_TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        DISH_FOOD_COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                        DISH_ING_COLUMN_NAME + TEXT_TYPE + " )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DISH_TABLE_NAME;
    }
}
