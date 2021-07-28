package com.moringaschool.beautypalour.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.beautypalour.Master.Product;
import com.moringaschool.beautypalour.R;
import com.moringaschool.beautypalour.UI.BeautyDetailActivity;
import com.moringaschool.beautypalour.UI.MainActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeautyPalourAdapter extends RecyclerView.Adapter<BeautyPalourAdapter.PostViewHolder> {
    private List<Product> productList;
    private Context mContext;

    public BeautyPalourAdapter(MainActivity mainActivity, List<Product> productList){
        this.productList=productList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.product_main,parent,false);
        PostViewHolder viewholder = new PostViewHolder(view);



        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull  BeautyPalourAdapter.PostViewHolder holder, int position) {
holder.bindBeautyPalour(productList.get(position));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.productImageView)
        ImageView mProductImageView;
        @BindView(R.id.nameTextView)
        TextView mNameTextView;
        @BindView(R.id.ProductTextView)
        TextView mProductTextView;
        @BindView(R.id.descriptionTextView)
        TextView mDescriptionTextView;
        @BindView(R.id.PriceTextView)
        TextView mPriceTextView;


        public PostViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            int position = getLayoutPosition();
            Intent intent = new Intent(mContext, BeautyDetailActivity.class);
            intent.putExtra("position",position);
            intent.putExtra("Product", Parcels.wrap(productList));
            mContext.startActivity(intent);
        }

        public void bindBeautyPalour(Product product){
            mNameTextView.setText(product.getName());
            mProductTextView.setText(product.getProductType());
            mDescriptionTextView.setText(product.getDescription());
            mPriceTextView.setText(product.getPrice());
            Picasso.get().load(product.getImageLink()).into(mProductImageView);


        }

    }





}
