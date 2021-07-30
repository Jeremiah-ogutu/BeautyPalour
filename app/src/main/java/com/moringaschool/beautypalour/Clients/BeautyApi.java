package com.moringaschool.beautypalour.Clients;


import com.moringaschool.beautypalour.Models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BeautyApi {
    @GET("/api/v1/products.json")
    Call<List<Product>> getProduct();

}
