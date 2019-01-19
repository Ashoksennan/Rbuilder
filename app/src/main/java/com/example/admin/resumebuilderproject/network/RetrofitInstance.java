package com.example.admin.resumebuilderproject.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static Retrofit retrofit= null;
    public static String BASE_URL = "http://api.dataatwork.org/v1/";

    public static Retrofit getInstance(){
      if(retrofit == null){
          retrofit = new Retrofit.Builder()
                  .baseUrl(BASE_URL)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build();
      }
      return retrofit;
    }
}
