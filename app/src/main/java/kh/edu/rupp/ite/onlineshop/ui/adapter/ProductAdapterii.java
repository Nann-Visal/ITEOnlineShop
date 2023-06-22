package kh.edu.rupp.ite.onlineshop.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.onlineshop.api.model.ModelProducts;
import kh.edu.rupp.ite.onlineshop.databinding.ItemHolderProductBinding;
import kh.edu.rupp.ite.onlineshop.databinding.ItemHolderProductiiBinding;

public class ProductAdapterii extends ListAdapter<ModelProducts,ProductAdapterii.ProductViewHolderii> {

    public ProductAdapterii() {
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
    public ProductViewHolderii onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemHolderProductiiBinding itemHolderProductiiBinding = ItemHolderProductiiBinding.inflate(layoutInflater,parent,false);
        return new ProductViewHolderii(itemHolderProductiiBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolderii holder, int position) {
        ModelProducts item = getItem(position);
        holder.bind(item);
    }

    public static class ProductViewHolderii extends RecyclerView.ViewHolder {

        //create obj binding
       private ItemHolderProductiiBinding itemHolderProductiiBinding;
        public ProductViewHolderii(ItemHolderProductiiBinding itemHolderProductiiBinding) {
            super(itemHolderProductiiBinding.getRoot());
            this.itemHolderProductiiBinding = itemHolderProductiiBinding;
        }

        //bind data
        public void bind(ModelProducts modelProducts){
           String txtProductPrice = ("$"+ String.valueOf(modelProducts.getPrice()));
           String txtProductRate  = String.valueOf((modelProducts.getRating()));
           Picasso.get().load(modelProducts.getImageUrl()).into(itemHolderProductiiBinding.imgHoPrAll);
            itemHolderProductiiBinding.txtProductPrice.setText(txtProductPrice);
            itemHolderProductiiBinding.txtProductRate.setText(txtProductRate);
            itemHolderProductiiBinding.txtProductName.setText(modelProducts.getName());
        }
    }
}
