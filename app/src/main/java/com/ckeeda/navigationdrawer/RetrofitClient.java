package com.ckeeda.navigationdrawer;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 20-Feb-18.
 */

public class RetrofitClient {
    public static final String BASE_URL = "http://services.hanselandpetal.com";
    private static Retrofit retrofit;

    public static Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

           if(retrofit == null){
               retrofit = new Retrofit.Builder()
                       .baseUrl(BASE_URL)
                       .client(client)
                       .addConverterFactory(GsonConverterFactory.create())
                       .build();

           }
        return  retrofit;
    }
}
