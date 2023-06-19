package kh.edu.rupp.ite.onlineshop.api.service;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.api.model.ModelProducts;
import kh.edu.rupp.ite.onlineshop.api.model.ModelProfiles;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicesAPI {
    @GET("/Midterm/Products/products.json")
    Call<List<ModelProducts>> loadProductList();

    @GET("/Midterm/Profile/profile.json")
    Call<ModelProfiles> loadProfileData();
}
