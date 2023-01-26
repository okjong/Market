package com.example.market;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {

    static String baseUrl="http://jeilpharm.dothome.co.kr";

    static Retrofit getRetrofitInstanceGson(){
        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit= builder.build();

        return retrofit;

    }

}
