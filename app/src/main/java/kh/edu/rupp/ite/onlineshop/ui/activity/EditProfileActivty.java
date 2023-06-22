package kh.edu.rupp.ite.onlineshop.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import kh.edu.rupp.ite.onlineshop.databinding.FragmentProfileEditBinding;

public class EditProfileActivty extends AppCompatActivity {
    private FragmentProfileEditBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = FragmentProfileEditBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        getIntentSignal();
    }

    //get signal intent
    public void getIntentSignal(){

        binding.txtValueUserName.setText(getIntent().getExtras().getString("username"));
        binding.txtValueEmail.setText(getIntent().getExtras().getString("useremail"));
        binding.txtValueGender.setText(getIntent().getExtras().getString("usergender"));
        binding.txtValueBirthday.setText(getIntent().getExtras().getString("userbirthday"));
        binding.txtValueAddress.setText(getIntent().getExtras().getString("useraddress"));
    }
}
