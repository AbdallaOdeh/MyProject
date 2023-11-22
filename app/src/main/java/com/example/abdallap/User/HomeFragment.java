package com.example.abdallap.User;

import static com.example.abdallap.DataTables.TablesString.ProductTable.*;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.abdallap.Classes.Product;
import com.example.abdallap.Classes.ProductAdapter;
import com.example.abdallap.DataTables.DBHelper;
import com.example.abdallap.R;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    List<Product> productList;
    RecyclerView recyclerView;
    ProductAdapter mAdapter;
    DBHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        productList = new ArrayList<>();
        dbHelper = new DBHelper(inflater.getContext());
        dbHelper = dbHelper.OpenReadAble();
        Product p = new Product(),p2=new Product();
        Cursor c = p.Select(dbHelper.getDb());
        c.moveToFirst();
        while(!c.isAfterLast()){
            p.setPid(c.getInt(c.getColumnIndexOrThrow(BaseColumns._ID)));
            p.setProdtype(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_TYPE)));
            p.setProddisc(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_DESCRIPTION)));
            p.setBuyprice(c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_BUYPRICE)));
            p.setSaleprice(c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_SALEPRICE)));
            p.setStock(c.getInt(c.getColumnIndexOrThrow(COLUMN_PRODUCT_STOCK)));
            p.setImageByte(c.getBlob(c.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE)));
            productList.add(p2);
            c.moveToNext();
        }
        // adapter
        mAdapter = new ProductAdapter(inflater.getContext(), productList);
        recyclerView.setAdapter(mAdapter);
        // Inflate the layout for this fragment
        return  view;
    }
}