package kh.edu.rupp.ite.onlineshop.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.onlineshop.api.model.ModelProducts;
import kh.edu.rupp.ite.onlineshop.databinding.ItemHolderHoprppBinding;

public class ProductAdapterHomePP extends ListAdapter<ModelProducts, ProductAdapterHomePP.ProductHomePPViewHolder> {

    public ProductAdapterHomePP() {
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
    public ProductHomePPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemHolderHoprppBinding itemHolderHoprppBinding = ItemHolderHoprppBinding.inflate(layoutInflater,parent,false);
        return new ProductHomePPViewHolder(itemHolderHoprppBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHomePPViewHolder holder, int position) {
        ModelProducts modelProducts = getItem(position);
        holder.bind(modelProducts);
    }

    public static class ProductHomePPViewHolder extends RecyclerView.ViewHolder{
        private final ItemHolderHoprppBinding itemHolderHoprppBinding;
        public ProductHomePPViewHolder( ItemHolderHoprppBinding itemHolderHoprppBinding) {
            super(itemHolderHoprppBinding.getRoot());
            this.itemHolderHoprppBinding = itemHolderHoprppBinding;
        }

        //bind data
        @SuppressLint("SetTextI18n")
        public void bind(ModelProducts modelProducts){
            String txtRating = ("Rating : " + modelProducts.getRating());

            Picasso.get().load(modelProducts.getImageUrl()).into(itemHolderHoprppBinding.imgHoPrPP);
            itemHolderHoprppBinding.txtProductPrice.setText("$"+modelProducts.getPrice());
            itemHolderHoprppBinding.txtRating.setText(txtRating);
        }
    }
}
