package kh.edu.rupp.ite.onlineshop.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.onlineshop.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    private  TextView txtProductName;
    private  TextView txtProductSKU;
    private  TextView txtProductDesc;
    private  TextView txtProductPrice;
    private  TextView txtProductRate;
    private  ImageView imageProduct;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kh.edu.rupp.ite.onlineshop.databinding.ActivityDetailBinding activityDetailBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(activityDetailBinding.getRoot());

        txtProductName = activityDetailBinding.txtProductName;
        txtProductSKU  = activityDetailBinding.txtSKU;
        txtProductDesc = activityDetailBinding.txtDescription;
        txtProductPrice= activityDetailBinding.txtPrice;
        txtProductRate = activityDetailBinding.txtRating;
        imageProduct   = activityDetailBinding.imgProductDetail;

        //bind data into view-lyt
        getIntentSignal();
    }

    //start intent
    public void getIntentSignal(){
        String txtSKU = ("SKU : " + getIntent().getExtras().getString("sku"));
        String txtRate = ("Rated : " + getIntent().getExtras().getString("rating"));
        String txtPrice = ("$"+getIntent().getExtras().getString("price"));

        txtProductName.setText(getIntent().getExtras().getString("name"));
        txtProductSKU.setText(txtSKU);
        txtProductRate.setText(txtRate);
        txtProductPrice.setText(txtPrice);
        txtProductDesc.setText(getIntent().getExtras().getString("description"));
        Picasso.get().load(getIntent().getExtras().getString("image_url")).into(imageProduct);
    }
}
