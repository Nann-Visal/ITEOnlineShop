package kh.edu.rupp.ite.onlineshop.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.onlineshop.api.model.ModelProducts;
import kh.edu.rupp.ite.onlineshop.databinding.ItemHolderProductBinding;
import kh.edu.rupp.ite.onlineshop.ui.activity.DetailActivity;

public class ProductAdapter extends ListAdapter<ModelProducts,ProductAdapter.ProductViewHolder> {

    public ProductAdapter() {
        super(new DiffUtil.ItemCallback<ModelProducts>() {
            @Override
            public boolean areItemsTheSame(@NonNull ModelProducts oldItem, @NonNull ModelProducts newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull ModelProducts oldItem, @NonNull ModelProducts newItem) {
                return oldItem.getId().equals(newItem.getId());
            }
        });
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //create view item
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemHolderProductBinding binding = ItemHolderProductBinding.inflate(layoutInflater,parent,false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        //bind data
        ModelProducts item = getItem(position);
        holder.bind(item);

        //set even for navigate activity
        holder.itemHolderProductBinding.btnDetail.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("sku",item.getSku());
            intent.putExtra("name",item.getName());
            intent.putExtra("description",item.getDescription());
            intent.putExtra("price",String.valueOf(item.getPrice()));
            intent.putExtra("image_url",item.getImageUrl());
            intent.putExtra("rating",String.valueOf(item.getRating()));

            v.getContext().startActivity(intent);
        });
    }

    //create view holder
    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        //create obj binding
        private final ItemHolderProductBinding itemHolderProductBinding;
        public ProductViewHolder(ItemHolderProductBinding itemHolderProductBinding) {
            super(itemHolderProductBinding.getRoot());
            this.itemHolderProductBinding = itemHolderProductBinding;
        }

        //bind data
        public void bind(ModelProducts modelProducts){
            String txtProductPrice = ("$"+modelProducts.getPrice()+".000");
            Picasso.get().load(modelProducts.getImageUrl()).into(itemHolderProductBinding.imgProduct);
            itemHolderProductBinding.txtProductName.setText(modelProducts.getName());
            itemHolderProductBinding.txtProductPrice.setText(txtProductPrice);
        }
    }
}
