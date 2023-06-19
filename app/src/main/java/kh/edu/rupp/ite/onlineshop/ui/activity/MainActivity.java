package kh.edu.rupp.ite.onlineshop.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import kh.edu.rupp.ite.onlineshop.R;
import kh.edu.rupp.ite.onlineshop.databinding.ActivityMainBinding;
import kh.edu.rupp.ite.onlineshop.ui.fragment.HomeFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.MoreFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.ProductFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //show fragment
        showFragment(new HomeFragment());
        //setup listener to item bottom-navigation-bar
        binding.bottomNavigationBar.setOnItemSelectedListener(item -> {
            if(item.getItemId()==R.id.menuHome){
                showFragment(new HomeFragment());
            }else if(item.getItemId()==R.id.menuProducts){
                showFragment(new ProductFragment());
            }else if(item.getItemId()==R.id.menuProfile){
                showFragment(new ProfileFragment());
            }else if(item.getItemId()==R.id.menuMore){
                showFragment(new MoreFragment());
            }
            return true;
        });
    }

    //show fragment in main activity
    private void showFragment(Fragment fragment){

        //fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //fragment transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //fragment replace in lytFragment layout-view
        fragmentTransaction.replace(R.id.lytFragments,fragment);

        //fragment commit
        fragmentTransaction.commit();
    }
}