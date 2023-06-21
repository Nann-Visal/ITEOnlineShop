package kh.edu.rupp.ite.onlineshop.api.service;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.api.model.ModelProducts;
import kh.edu.rupp.ite.onlineshop.api.model.ModelProfiles;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicesAPI {
    @GET("/kimsongsao/ferupp/main/products.json")
    Call<List<ModelProducts>> loadProductList();

    @GET("/kimsongsao/ferupp/main/profile.json")
    Call<ModelProfiles> loadProfileData();
}
