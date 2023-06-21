package kh.edu.rupp.ite.onlineshop.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.onlineshop.api.model.ModelProducts;
import kh.edu.rupp.ite.onlineshop.databinding.ItemHolderHoprallBinding;
import kh.edu.rupp.ite.onlineshop.ui.activity.DetailActivity;

public class ProductAdapterHomeAll extends ListAdapter<ModelProducts, ProductAdapterHomeAll.ProductHomeAllViewHolder> {

    public ProductAdapterHomeAll() {
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
    public ProductHomeAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemHolderHoprallBinding itemHolderHoprallBinding = ItemHolderHoprallBinding.inflate(layoutInflater,parent,false);
        return new ProductHomeAllViewHolder(itemHolderHoprallBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHomeAllViewHolder holder, int position) {
        ModelProducts item = getItem(position);
        holder.bind(item);

        //set event on item
        holder.itemHolderHoprallBinding.cardView.setOnClickListener(v->{
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

    public static class ProductHomeAllViewHolder extends RecyclerView.ViewHolder{
        private final ItemHolderHoprallBinding itemHolderHoprallBinding;
        public ProductHomeAllViewHolder(ItemHolderHoprallBinding itemHolderHoprallBinding) {
            super(itemHolderHoprallBinding.getRoot());
            this.itemHolderHoprallBinding = itemHolderHoprallBinding;
        }

        //bind data
        public void bind(ModelProducts modelProducts){
            Picasso.get().load(modelProducts.getImageUrl()).into(itemHolderHoprallBinding.imgHoPrAll);
            itemHolderHoprallBinding.txtProductName.setText(modelProducts.getName());
        }
    }
}
