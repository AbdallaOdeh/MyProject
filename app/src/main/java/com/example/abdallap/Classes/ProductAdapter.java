package com.example.abdallap.Classes;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.abdallap.User.ProductInfo;
import com.example.abdallap.DataTables.DBHelper;
import com.example.abdallap.DataTables.TablesString;
import com.example.abdallap.R;

import com.example.abdallap.User.ProductInfo;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> productList;
    int pid;
    String uid;
    View view;
    Context context;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        view = LayoutInflater.from(context).inflate(R.layout.each_clothe, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        // here we will find the position and start setting the output on our views


        String typeOfProduct = productList.get(position).getProdtype();
        double price = productList.get(position).getSaleprice();
        byte[] images = productList.get(position).getImageByte();
        Bitmap bm = BitmapFactory.decodeByteArray(images, 0 ,images.length);
        pid = productList.get(position).getPid();
        uid = FirebaseAuth.getInstance().getUid();
        DBHelper dbHelper = new DBHelper(context);
        dbHelper.OpenReadAble();
        dbHelper.Close();
        holder.tvTypeOfProduct.setText(typeOfProduct);
        holder.tvJewelryPrice.setText(price+"");
        holder.imageOfProduct.setImageBitmap(bm);


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // in order to make our views responsive we can implement onclicklistener on our recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // here we will find the views on which we will inflate our data

        TextView tvTypeOfProduct, tvJewelryPrice, tvJewelryYOP;
        ImageView imageOfProduct, addToCartBtn, favBtn;

        public ViewHolder(View itemView) {
            super(itemView);

            tvTypeOfProduct = itemView.findViewById(R.id.eachClotheName);
            tvJewelryPrice = itemView.findViewById(R.id.eachClothePriceTv);
            imageOfProduct = itemView.findViewById(R.id.eachClotheIv);
            addToCartBtn = itemView.findViewById(R.id.eachClotheAddToCartBtn);
            addToCartBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pid = productList.get(getLayoutPosition()).getPid();
                    uid = FirebaseAuth.getInstance().getUid();
                    Cart c = new Cart(uid,pid,1);
                    DBHelper dbHelper = new DBHelper(context);
                    dbHelper.OpenWriteAble();
                    if(c.Add(dbHelper.getDb())>-1)
                        Toast.makeText(context, "Added To Cart Successfully", Toast.LENGTH_SHORT).show();
                    dbHelper.Close();
                }
            });


            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), ProductInfo.class);
            intent.putExtra("id", productList.get(getLayoutPosition()).getPid() + "");
            v.getContext().startActivity(intent);
        }

    }
}

