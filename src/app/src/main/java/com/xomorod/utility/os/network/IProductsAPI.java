package com.xomorod.utility.os.network;


import com.xomorod.utility.section.market.CategoriesData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ahmad on 2/11/2016.
 */
public interface IProductsAPI {

    @GET("/categories.json")
    Call<CategoriesData> getCategory();

}
