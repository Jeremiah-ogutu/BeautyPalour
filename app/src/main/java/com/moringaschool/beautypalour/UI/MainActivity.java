package com.moringaschool.beautypalour.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.beautypalour.Adapter.BeautyPalourAdapter;
import com.moringaschool.beautypalour.Clients.BeautyClient;
import com.moringaschool.beautypalour.Models.Product;
import com.moringaschool.beautypalour.Models.ProductColor;
import com.moringaschool.beautypalour.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener  {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener  mAuthListener;


    @BindView(R.id.progressBar)
    ProgressBar mProgressbar;
    @BindView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;

    private BeautyPalourAdapter mAdapter;

    private List<Product>  productList;
//    public  ProductColor

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new BeautyPalourAdapter(MainActivity.this, (List<Product>) productList);


        ButterKnife.bind(this);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }

            }
        };


            fetchPosts();

    }

    public void fetchPosts() {

        Log.e("TAG", "fetchPosts");
        BeautyClient.getRetrofitClient().getProduct().enqueue(new Callback<List<Product>>(){
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                hideProgressBar();
                if(response.isSuccessful()){
                    productList = response.body();
                    mAdapter = new  BeautyPalourAdapter(MainActivity.this,productList);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showBeautyProduct();
                } else {
                    showUnsuccessfulMessage();
                }

            }


//

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

                Log.e("TAG",t.getMessage().toString());
                hideProgressBar();
                showFailureMessage();

            }


            private void showFailureMessage() {
                mErrorTextView.setText("Check your internate conection");
                mErrorTextView.setVisibility(View.VISIBLE);
            }

            private void hideProgressBar() {
                mProgressbar.setVisibility(View.GONE);
            }

            public void showUnsuccessfulMessage() {
                mErrorTextView.setText("Something went wrong. Please try again later");
                mErrorTextView.setVisibility(View.VISIBLE);
            }

            public void showBeautyProduct() {
                mRecyclerView.setVisibility(View.VISIBLE);

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v) {

    }
}


















