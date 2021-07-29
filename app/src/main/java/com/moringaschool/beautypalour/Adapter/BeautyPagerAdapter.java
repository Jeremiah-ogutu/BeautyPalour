package com.moringaschool.beautypalour.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.beautypalour.Models.Product;
import com.moringaschool.beautypalour.Models.Product;
import com.moringaschool.beautypalour.UI.BeautyDetailFragment;

import java.util.List;

public class BeautyPagerAdapter extends FragmentPagerAdapter {
    private List<Product>productList;

    public BeautyPagerAdapter(FragmentManager fm, int behavior, List<Product>BeautyList){
        super(fm,behavior);
        productList =BeautyList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return BeautyDetailFragment.newInstance(productList.get(position));
    }

    @Override
    public int getCount() {
        return 0;
    }
}
