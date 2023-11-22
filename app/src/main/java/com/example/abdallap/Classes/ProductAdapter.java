package com.example.abdallap.Classes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.abdallap.Classes.Product;
import com.example.abdallap.R;

import com.example.abdallap.User.ProductInfo;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> productList;
    Context context;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.each_clothe, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        // here we will find the position and start setting the output on our views

        String nameofProduct = productList.get(position).getProdtype();
        String descriptionofproduct = productList.get(position).getProddisc();
        byte[] images = productList.get(position).getImageByte();
        Bitmap bm = BitmapFactory.decodeByteArray(images, 0 ,images.length);

        holder.tvNameOfProduct.setText(nameofProduct);
        holder.tvDescriptionOfProduct.setText(descriptionofproduct);
        holder.imageOfProduct.setImageBitmap(bm);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // in order to make our views responsive we can implement onclicklistener on our recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // here we will find the views on which we will inflate our data

        TextView tvNameOfProduct, tvDescriptionOfProduct,tvPriceOfProduct;
        ImageView imageOfProduct,addtocart;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNameOfProduct = itemView.findViewById(R.id.eachClotheName);
            tvDescriptionOfProduct = itemView.findViewById(R.id.eachClotheDisc);
            tvPriceOfProduct = itemView.findViewById(R.id.eachClothePriceTv);
            imageOfProduct = itemView.findViewById(R.id.eachClotheIv);
            addtocart = itemView.findViewById(R.id.eachClotheAddToCartBtn);
            addtocart.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.eachClotheAddToCartBtn){

            }
            else{
                Intent intent = new Intent(v.getContext(),ProductInfo.class);
                intent.putExtra("id",productList.get(getLayoutPosition()).getPid()+"");
                v.getContext().startActivity(intent);
            }
        }
    }
}