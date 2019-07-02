package com.katrenich.alex.klara.assortmentScreen.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.katrenich.alex.klara.BR;
import com.katrenich.alex.klara.assortmentScreen.viewmodel.ProductListViewModel;
import com.katrenich.alex.klara.assortmentScreen.model.Product;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    private int layoutId;
    private List<Product> mProducts;
    private ProductListViewModel viewModel;

    public ProductListAdapter (@LayoutRes int layoutId, ProductListViewModel viewModel){
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    private int getLayoutIdForPosition(int position){
        return layoutId;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    public void setProducts(List<Product> products){
        mProducts = products;
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    @Override
    public int getItemCount() {
        return mProducts == null ? 0 : mProducts.size();
    }


    // view holder
    class ProductViewHolder extends RecyclerView.ViewHolder{
        final ViewDataBinding mBinding;
        ProductViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(ProductListViewModel viewModel, Integer position){
            mBinding.setVariable(BR.viewmodel, viewModel);
            mBinding.setVariable(BR.position, position);
            mBinding.executePendingBindings();
        }
    }
}
