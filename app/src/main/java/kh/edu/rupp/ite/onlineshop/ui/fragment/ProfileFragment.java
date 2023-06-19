package kh.edu.rupp.ite.onlineshop.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import kh.edu.rupp.ite.onlineshop.api.model.ModelProfiles;
import kh.edu.rupp.ite.onlineshop.api.service.ServicesAPI;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProfileBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        Toolbar toolbar = (Toolbar) binding.toolBarProfileFragment;
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadModelProfile();
    }

    //function load data
    public  void loadModelProfile(){

        //create http-client
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://ferupp.s3.ap-southeast-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        //create obj service
        ServicesAPI servicesAPI = httpClient.create(ServicesAPI.class);

        //load data
        Call<ModelProfiles> task = servicesAPI.loadProfileData();
        task.enqueue(new Callback<ModelProfiles>() {
            @Override
            public void onResponse(Call<ModelProfiles> call, Response<ModelProfiles> response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    showModelProfile(response.body());
                }else{
                    Toast.makeText(getContext(),"Data Loading . . .",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ModelProfiles> call, Throwable t) {
                Toast.makeText(getContext(),"Data Failed . . .",Toast.LENGTH_LONG).show();
            }
        });

    }

    //bind data
    public  void showModelProfile(ModelProfiles modelProfiles){
        String txtUserName = (modelProfiles.getFirst_name()+" "+modelProfiles.getLast_name());
        String txtAddress  = ("Aaron Larson  123 Center Ln. Plymouth, MN 55441 United States");

        Picasso.get().load(modelProfiles.getImage_url()).into(binding.imgProfile);
        binding.txtUserName.setText(txtUserName);
        binding.txtUserEmail.setText(modelProfiles.getEmail());
        binding.txtValueEmail.setText(modelProfiles.getEmail());
        binding.txtValuePhone.setText(modelProfiles.getPhone_number());
        binding.txtValueGender.setText(modelProfiles.getGender());
        binding.txtValueBirthday.setText(modelProfiles.getBirthday());
        binding.txtValueAddress.setText(txtAddress);
    }
}
