package com.example.abdallap.DataTables;
import com.example.abdallap.DataTables.TablesString.*;
public class QueryString {


    //region Create Tables
    public static final String SQL_CREATE_PRODUCT =
            "CREATE TABLE " + ProductTable.TABLE_PRODUCT + " (" +
                    ProductTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ProductTable.COLUMN_PRODUCT_TYPE + " TEXT," +
                    ProductTable.COLUMN_PRODUCT_DESCRIPTION + " TEXT," +
                    ProductTable.COLUMN_PRODUCT_STOCK + " INTEGER," +
                    ProductTable.COLUMN_PRODUCT_SALEPRICE + " DOUBLE,"+
                    ProductTable.COLUMN_PRODUCT_BUYPRICE + " DOUBLE,"+
                    ProductTable.COLUMN_PRODUCT_IMAGE + " BLOB);";

    public static final String SQL_CREATE_CART =
            "CREATE TABLE " + CartTable.TABLE_CART + " (" +
                    CartTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CartTable.COLUMN_PRODUCT_ID + " INTEGER," +
                    CartTable.COLUMN_USER_ID + " TEXT," +
                    CartTable.COLUMN_AMOUNT + " INTEGER);";


    public static final String SQL_CREATE_SALE =
            "CREATE TABLE " + SaleTable.TABLE_SALE + " (" +
                    SaleTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SaleTable.COLUMN_SALE_PROD_ID + " INTEGER," +
                    SaleTable.COLUMN_STOCKS_SOLD + "INTEGER);";

    //endregions

    //region Delete Tables

    public static final String SQL_DELETE_PRODUCT =
            "DROP TABLE IF EXISTS " + ProductTable.TABLE_PRODUCT;

    public static final String SQL_DELETE_CART =
            "DROP TABLE IF EXISTS " + CartTable.TABLE_CART;

    public static final String SQL_DELETE_SALE =
            "DROP TABLE IF EXISTS " + SaleTable.TABLE_SALE;

    //endregion
}
