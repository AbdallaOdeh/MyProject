package com.example.abdallap.DataTables;

import android.provider.BaseColumns;

public class TablesString {

    public TablesString() {
    }
    //region Product Table
    public static class ProductTable implements BaseColumns {
        public static final String TABLE_PRODUCT = "Product";
        public static final String COLUMN_PRODUCT_TYPE = "Type";
        public static final String COLUMN_PRODUCT_DESCRIPTION = "Description";
        public static final String COLUMN_PRODUCT_IMAGE = "ProductImage";
        public static final String COLUMN_PRODUCT_STOCK = "Stock";
        public static final String COLUMN_PRODUCT_SALEPRICE = "SalePrice";
        public static final String COLUMN_PRODUCT_BUYPRICE = "BuyPrice";

    }
    //endregion

    //region Cart Table
    public static class CartTable implements BaseColumns {
        public static final String TABLE_CART = "Cart";
        public static final String COLUMN_PRODUCT_ID = "PID";
        public static final String COLUMN_USER_ID = "UID";
        public static final String COLUMN_AMOUNT = "AMOUNT";


    }
    //endregion

    //region Sale Table
    public static class SaleTable implements BaseColumns {
        public static final String TABLE_SALE = "SALE";
        public static final String COLUMN_SALE_PROD_ID = "PID";
        public static final String COLUMN_STOCKS_SOLD = "AmountSold";
    }
    //endregion
}
