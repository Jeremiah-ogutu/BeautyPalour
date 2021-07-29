package com.moringaschool.beautypalour.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moringaschool.beautypalour.Adapter.BeautyPalourAdapter;
import com.moringaschool.beautypalour.Clients.BeautyClient;
import com.moringaschool.beautypalour.Models.Product;
import com.moringaschool.beautypalour.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.progressBar) ProgressBar mProgressbar;
    @BindView(R.id.RecyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView)TextView mErrorTextView;

    private BeautyPalourAdapter mAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new BeautyPalourAdapter(MainActivity.this, productList);




        ButterKnife.bind(this);

        fetchPosts();

    }

    public void fetchPosts(){

        Log.e("TAG","fetchPosts");
        BeautyClient.getRetrofitClient().getProduct().  enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {

                    productList = response.body();
                    mAdapter = new BeautyPalourAdapter(MainActivity.this, productList);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showBeautyProduct();
                } else {
                    showUnsuccessfulMessage();
                }

            }


            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("TAG", "onFailure", t);
                hideProgressBar();
                showFailureMessage();
            };

        }
    }


    public void hideProgressBar(){
        mProgressbar.setVisibility(View.GONE);
    }
    public void showBeautyProduct(){
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    public void showFailureMessage(){
        mErrorTextView.setText("Check your internate conection");
        mErrorTextView.setVisibility(View.VISIBLE);
    }
    public void showUnsuccessfulMessage(){
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

}














