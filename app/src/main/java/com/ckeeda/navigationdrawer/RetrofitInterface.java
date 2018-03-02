package com.ckeeda.navigationdrawer;

import com.ckeeda.navigationdrawer.Model.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by HP on 20-Feb-18.
 */

public interface RetrofitInterface {
    @GET("/feeds/flowers.json")
    Call<List<Flower>> getFlowerList();


}
