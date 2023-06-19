package kh.edu.rupp.ite.onlineshop.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.api.model.ModelProducts;
import kh.edu.rupp.ite.onlineshop.api.service.ServicesAPI;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProductBinding;
import kh.edu.rupp.ite.onlineshop.ui.adapter.ProductAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductFragment extends Fragment {
    private FragmentProductBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater,container,false);

        Toolbar toolbar = (Toolbar) binding.toolBarProductFragment;
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadModelProduct();
    }

    //function load data
    private void loadModelProduct(){

        //create http client
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://ferupp.s3.ap-southeast-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create obj service
        ServicesAPI servicesAPI = httpClient.create(ServicesAPI.class);

        //load data
        Call<List<ModelProducts >> task = servicesAPI.loadProductList();
        task.enqueue(new Callback<List<ModelProducts>>() {
            @Override
            public void onResponse(Call<List<ModelProducts>> call, Response<List<ModelProducts>> response) {
                if(response.isSuccessful()){
                    showModelProduct(response.body());
                }else{
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelProducts>> call, Throwable t) {
                Toast.makeText(getContext(),"Data Failed . . .",Toast.LENGTH_LONG).show();
            }
        });
    }

    //create recycler-view and adapter
    public void showModelProduct(List<ModelProducts> modelProducts){

        //create layout-manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerViewProducts.setLayoutManager(linearLayoutManager);

        //create adapter
        ProductAdapter productAdapter = new ProductAdapter();
        productAdapter.submitList(modelProducts);
        binding.recyclerViewProducts.setAdapter(productAdapter);
    }



}
