package com.moringaschool.beautypalour.Clients;

import com.moringaschool.beautypalour.Clients.BeautyApi;
import com.moringaschool.beautypalour.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BeautyClient {

    private  static Retrofit retrofit =null;

    public static BeautyApi getRetrofitClient() {



        if(retrofit == null){
            retrofit = new Retrofit.Builder()

                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(BeautyApi.class);
    }
}
