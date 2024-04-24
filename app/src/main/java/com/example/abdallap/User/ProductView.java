package com.example.abdallap.User;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.abdallap.Classes.Product;
import com.example.abdallap.Classes.ProductAdapter;
import com.example.abdallap.DataTables.DBHelper;
import com.example.abdallap.LogIn;
import com.example.abdallap.SignUp;
import com.example.abdallap.R;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import com.example.abdallap.Classes.Product;

import static com.example.abdallap.DataTables.TablesString.ProductTable.COLUMN_PRODUCT_BUYPRICE;
import static com.example.abdallap.DataTables.TablesString.ProductTable.COLUMN_PRODUCT_DESCRIPTION;
import static com.example.abdallap.DataTables.TablesString.ProductTable.COLUMN_PRODUCT_IMAGE;
import static com.example.abdallap.DataTables.TablesString.ProductTable.COLUMN_PRODUCT_SALEPRICE;
import static com.example.abdallap.DataTables.TablesString.ProductTable.COLUMN_PRODUCT_STOCK;
import static com.example.abdallap.DataTables.TablesString.ProductTable.COLUMN_PRODUCT_TYPE;
public class ProductView extends AppCompatActivity {

    List<Product> productList;
    RecyclerView recyclerView;
    ProductAdapter mAdapter;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        Bundle extras = getIntent().getExtras();
        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        productList = new ArrayList<>();
        dbHelper = new DBHelper(this);
        dbHelper = dbHelper.OpenReadAble();
        Product p = new Product(), p2;
        Cursor c = p.Select(dbHelper.getDb());
        c.moveToFirst();
        while (!c.isAfterLast()) {
            p2 = new Product(c.getInt(c.getColumnIndexOrThrow(BaseColumns._ID)),
                    c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_TYPE)),
                    c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_DESCRIPTION)),
                    c.getInt(c.getColumnIndexOrThrow(COLUMN_PRODUCT_STOCK)),
                    c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_SALEPRICE)),
                    c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_BUYPRICE)),
                    c.getBlob(c.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE)));

            productList.add(p2);
            c.moveToNext();
        }
        dbHelper.Close();
        // adapter
        mAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(mAdapter);
    }

}