package com.katrenich.alex.klara.placesListScreen.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.katrenich.alex.klara.BR;
import com.katrenich.alex.klara.placesListScreen.model.CoffeeShop;
import com.katrenich.alex.klara.placesListScreen.viewmodel.CoffeeShopsViewModel;

import java.util.List;

public class CoffeeShopsListAdapter extends RecyclerView.Adapter<CoffeeShopsListAdapter.CoffeeShopViewHolder> {
    private static final String TAG = "CoffeeShopsListAdapter";
    private int layoutId;
    private List<CoffeeShop> mShops;
    private CoffeeShopsViewModel viewModel;

    public CoffeeShopsListAdapter(@LayoutRes int layoutId, CoffeeShopsViewModel viewModel){
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    private int getLayoutIdForPosition(int position){
        return layoutId;
    }

    @NonNull
    @Override
    public CoffeeShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        return new CoffeeShopViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeShopViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    public void setCoffeeShops(List<CoffeeShop> shops){
        if (shops != null){
            mShops = shops;
        } else {
            Log.e(TAG, "shops is NULL");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    @Override
    public int getItemCount() {
        return mShops == null ? 0 : mShops.size();
    }

    class CoffeeShopViewHolder extends RecyclerView.ViewHolder{
        final ViewDataBinding mBinding;
        public CoffeeShopViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(CoffeeShopsViewModel viewModel, Integer position){
            mBinding.setVariable(BR.coffeeShopViewModel, viewModel);
            mBinding.setVariable(BR.coffeeShopItemPosition, position);
            mBinding.executePendingBindings();
        }
    }
}
