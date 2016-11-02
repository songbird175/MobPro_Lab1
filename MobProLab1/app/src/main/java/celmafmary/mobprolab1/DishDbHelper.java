package celmafmary.mobprolab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mafaldaborges on 10/11/16.
 */
public class DishDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DishDatabase.db";

    public DishDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DishTable.FeedEntryDish.SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DishTable.FeedEntryDish.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addToMenu(FoodItem foodItem) {
        SQLiteDatabase dbw = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DishTable.FeedEntryDish.DISH_FOOD_COLUMN_NAME, foodItem.getName());
        values.put(DishTable.FeedEntryDish.DISH_ING_COLUMN_NAME, arraytoString(foodItem.getIngredients()));
        dbw.insert(DishTable.FeedEntryDish.DISH_TABLE_NAME, null, values);
    }

    public void deleteRow(FoodItem foodItem) {
        SQLiteDatabase dbw = this.getWritableDatabase();
        String selection = DishTable.FeedEntryDish._ID + " =?";
        String[] selectionArgs = {String.valueOf(foodItem.getId())};
        dbw.delete(DishTable.FeedEntryDish.DISH_TABLE_NAME, selection, selectionArgs);
        dbw.close();
    }

    public ArrayList<FoodItem> getAll() {
        SQLiteDatabase dbr = this.getReadableDatabase();
        ArrayList<FoodItem> foodArray = new ArrayList<>();
        Cursor c = dbr.rawQuery("select * from " + DishTable.FeedEntryDish.DISH_TABLE_NAME, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            long readID = c.getLong(0);
            String readName = c.getString(1);
            String readIngredient = c.getString(2);

            FoodItem foodInput = new FoodItem(readName,fromStringtoIngredientArray(readIngredient));
            foodInput.setId(readID);
            foodArray.add(foodInput);

            c.moveToNext();

        }
        dbr.close();
        return foodArray;
    }

    public void updateArray(long id, FoodItem foodItem){
        SQLiteDatabase dbw = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DishTable.FeedEntryDish.DISH_FOOD_COLUMN_NAME, foodItem.getName());
        values.put(DishTable.FeedEntryDish.DISH_ING_COLUMN_NAME, arraytoString(foodItem.getIngredients()));

        String selection = DishTable.FeedEntryDish._ID+ " Like ?";
        String[] selectionArgs = {Long.toString(id)};

        dbw.update(DishTable.FeedEntryDish.DISH_TABLE_NAME, values, selection, selectionArgs);

    }

    private ArrayList<Ingredient> fromStringtoIngredientArray (String string){
        String[] strings = string.split(",");
        Log.d("DishHelp2", strings[0]);
        ArrayList<Ingredient> newArray = new ArrayList<>();

        for (int i = 0; i < strings.length; i++){
            Ingredient ingredient = new Ingredient(strings[i]);
            newArray.add(ingredient);
        }
        Log.d("DishHelper", newArray.get(0).getName());
        return newArray;
    }

    private String arraytoString (ArrayList<Ingredient> ingredients){
        StringBuilder sb = new StringBuilder();
        for (Ingredient s : ingredients){
            sb.append(s.getName());
            sb.append(",");
        }

        return sb.toString();
    }

    //Todo: Make it actually work
}
