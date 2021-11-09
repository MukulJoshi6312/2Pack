package com.example.a2pack.helperClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    // version of the  database
    private static final int DATABASE_VERSION = 1;
    // database name
    private static final String DATABASE_NAME = "cartView";
    //database table name
    private static final String TABLE_NAME = "cartItems";


    // table columns
    public static final String ID = "id";
    public static final String MODEL = "model";
    public static final String SIZE = "size";
    public static final String PER_PRICE = "perPrice";
    public static final String PRICE = "price";
    public static final String QUANTITY = "quantity";

    private SQLiteDatabase sqLiteDatabase;


    // create the table inside the database
    private static final String CREATE_TABLE = " create table "+ TABLE_NAME + "("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MODEL + " TEXT NOT NULL,"
            + SIZE + " TEXT NOT NULL,"
            + PER_PRICE + " TEXT NOT NULL,"
            + PRICE + " TEXT NOT NULL,"
            + QUANTITY + " TEXT NOT NULL);";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void addItemIntoCart(productViewModelClass modelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.MODEL,modelClass.getProductModel());
        contentValues.put(DatabaseHelper.SIZE,modelClass.getProductSize());
        contentValues.put(DatabaseHelper.PER_PRICE,modelClass.getProductPricePerPiece());
        contentValues.put(DatabaseHelper.PRICE,modelClass.getProductPrice());
        contentValues.put(DatabaseHelper.QUANTITY,modelClass.getProductQty());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }

    public List<productViewModelClass> getCartItem(){
     String sql = "select * from " + TABLE_NAME;
     sqLiteDatabase = this.getReadableDatabase();

     List<productViewModelClass> storeItem = new ArrayList<>();
     Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
     if (cursor.moveToFirst()){
         do {
             int id = Integer.parseInt(cursor.getString(0));
             String model = cursor.getString(1);
             String size = cursor.getString(2);
             String perPrice = cursor.getString(3);
             String price = cursor.getString(4);
             String qty = cursor.getString(5);
             storeItem.add(new productViewModelClass(model,size,perPrice,price,qty,id));
         }while (cursor.moveToNext());
     }
     cursor.close();
     return storeItem;
    }


    public void deleteItem(int id){

        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,ID + " = ? ",new String[]
                {String.valueOf(id)});


    }

}
