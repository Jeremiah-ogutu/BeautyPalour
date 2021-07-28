package com.moringaschool.beautypalour.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.moringaschool.beautypalour.Adapter.BeautyPagerAdapter;
import com.moringaschool.beautypalour.Master.Product;
import com.moringaschool.beautypalour.R;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeautyDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private BeautyPagerAdapter adapterViewPager;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty_detail);
        ButterKnife.bind(this);

        productList = Parcels.unwrap(getIntent().getParcelableExtra("Product"));
        int startingPosition =getIntent().getIntExtra("position",0);
        adapterViewPager = new BeautyPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,productList);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }














}