package com.ckeeda.navigationdrawer;

import android.util.Log;

import com.ckeeda.navigationdrawer.Model.Flower;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 20-Feb-18.
 */

public class FlowerPresenter {
    private final FlowerCallbackListener listener;
    private List<Flower> flowerList = new ArrayList<>();

    public FlowerPresenter(FlowerCallbackListener listener) {
        this.listener = listener;
    }

    public void fetch() {
        listener.onFetchStart();
        Log.v("Retofit","fetch");
            RetrofitInterface retrofitservice = RetrofitClient.getClient().create(RetrofitInterface.class);
            Call<List<Flower>> call = retrofitservice.getFlowerList();

            call.enqueue(new Callback<List<Flower>>() {
                @Override
                public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                    Log.v("Retofit","Onresponse");
                    flowerList = response.body();

                    Log.v("Retrofit", String.valueOf(flowerList.size()));
                    listener.onFetchProgress(flowerList);

                }

                @Override
                public void onFailure(Call<List<Flower>> call, Throwable t) {
                    Log.e("Retofit","Onfail" + t.toString());
                }
            });

          listener.OnFetchComplete();
    }

    public static  List<Flower> getflowerfromJSONarray(String JSONstring, Type list) {
        if(!isValid(JSONstring)){
            return null;
        }
        return new Gson().fromJson(JSONstring,list);
    }

    private static boolean isValid(String jsoNstring) {
        try {
            new JsonParser().parse(jsoNstring);
            return true;
        }catch (JsonSyntaxException e){
            return false;
        }
    }

    public interface  FlowerCallbackListener{
        void onFetchStart();
        void onFetchProgress(List<Flower> flowerlist);
        void OnFetchComplete();

    }
}
