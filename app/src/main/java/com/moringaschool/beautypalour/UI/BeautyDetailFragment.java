package com.moringaschool.beautypalour.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.beautypalour.Constants;
import com.moringaschool.beautypalour.Models.Product;
import com.moringaschool.beautypalour.Models.ProductColor;
import com.moringaschool.beautypalour.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BeautyDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.productImageView)
    ImageView mImageViewLabel;
    @BindView(R.id.descriptionTextView)
    TextView mDescriptionTextvie;
    @BindView(R.id.nameTextView)
    TextView mNameTextview;
    @BindView(R.id.websiteTextView)
    TextView mWebsiteTextview;
    @BindView(R.id.phoneTextView)
    TextView mPhoneTextview;
    @BindView(R.id.addressTextView)
    TextView mAddressTextView;
    @BindView(R.id.saveProductsButton)
    Button mSaveProductsButton;
    @BindView(R.id.ProductTextView)
    TextView mProductTextview;

    private Product productList;


    public BeautyDetailFragment() {
        // Required empty public constructor
    }


    public static BeautyDetailFragment newInstance(Product productList) {
        BeautyDetailFragment  fragment = new BeautyDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("productList", Parcels.wrap(productList));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        productList = Parcels.unwrap(getArguments().getParcelable("productList"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_beauty_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.get().load(productList.getImageLink()).into(mImageViewLabel);
        mNameTextview.setText(productList.getName());
        mProductTextview.setText(productList.getProductType());
        mDescriptionTextvie.setText(productList.getDescription());

        mSaveProductsButton.setOnClickListener(this);


//        List<String> mProduct = new ArrayList<>();
//
//        for (Product product: productList.getmProduct()) {
//            mProduct.add(product.getProductType());
//        }


        return view;
    }


    @Override
    public void onClick(View view){
        if(view == mSaveProductsButton){
            DatabaseReference favouriteProductsRef = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_FAVOURITE_PRODUCTS);
            favouriteProductsRef.push().setValue(productList);
            Toast.makeText(getContext(),"Product has been added to favouries!",Toast.LENGTH_SHORT).show();
        }

    }
}