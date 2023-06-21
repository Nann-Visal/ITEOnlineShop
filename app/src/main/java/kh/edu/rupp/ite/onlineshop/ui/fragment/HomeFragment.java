package kh.edu.rupp.ite.onlineshop.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.R;
import kh.edu.rupp.ite.onlineshop.api.model.ModelProducts;
import kh.edu.rupp.ite.onlineshop.api.model.ModelProfiles;
import kh.edu.rupp.ite.onlineshop.api.service.ServicesAPI;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentHomeBinding;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProfileBinding;
import kh.edu.rupp.ite.onlineshop.ui.adapter.ProductAdapterHomeAll;
import kh.edu.rupp.ite.onlineshop.ui.adapter.ProductAdapterHomePP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadProductFromServer();
    }


    //load data
    public void loadProductFromServer(){

        //create http-client
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create obj server api
        ServicesAPI servicesAPI = httpClient.create(ServicesAPI.class);

        //load data
        Call<List<ModelProducts>> task  = servicesAPI.loadProductList();
        task.enqueue(new Callback<List<ModelProducts>>() {
            @Override
            public void onResponse(Call<List<ModelProducts>> call, Response<List<ModelProducts>> response) {
               if(response.isSuccessful()){
                   showProductPP(response.body());
                   assert response.body() != null;
                   showProductAll(response.body());
               }else{
                   Toast.makeText(getContext(),"Data Loading . . .",Toast.LENGTH_LONG).show();
               }
            }

            @Override
            public void onFailure(Call<List<ModelProducts>> call, Throwable t) {
                Toast.makeText(getContext(),"Data Failed . . .",Toast.LENGTH_LONG).show();
            }
        });
    }

    //show data
    public void showProductPP(List<ModelProducts> modelProducts){

        //setup layout-manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.recyclerViewPopularProduct.setLayoutManager(linearLayoutManager);

        //setup adapter
        ProductAdapterHomePP productAdapterHomePP = new ProductAdapterHomePP();
        productAdapterHomePP.submitList(modelProducts);
        binding.recyclerViewPopularProduct.setAdapter(productAdapterHomePP);
    }

    public void showProductAll(List<ModelProducts> modelProducts){

        //setup layout-manager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        binding.recyclerViewAllProduct.setLayoutManager(gridLayoutManager);

        //setup adapter
        ProductAdapterHomeAll productAdapterHomeAll = new ProductAdapterHomeAll();
        productAdapterHomeAll.submitList(modelProducts);
        binding.recyclerViewAllProduct.setAdapter(productAdapterHomeAll);
    }

}
