package com.moringaschool.beautypalour.Clients;

import com.moringaschool.beautypalour.Master.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BeautyApi {
    @GET("products.json")
    Call<List<Product>> getProduct();

}
