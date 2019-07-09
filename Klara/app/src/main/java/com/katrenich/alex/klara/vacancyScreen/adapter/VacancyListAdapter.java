package com.katrenich.alex.klara.vacancyScreen.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.katrenich.alex.klara.BR;
import com.katrenich.alex.klara.vacancyScreen.model.Vacancy;
import com.katrenich.alex.klara.vacancyScreen.viewmodel.VacancyListViewModel;

import java.util.List;

public class VacancyListAdapter extends RecyclerView.Adapter<VacancyListAdapter.VacancyViewHolder> {
    private int layoutId;
    private List<Vacancy> mVacancies;
    private VacancyListViewModel mViewModel;

    public VacancyListAdapter(@LayoutRes int layoutId, VacancyListViewModel viewModel){
        this.layoutId = layoutId;
        this.mViewModel = viewModel;
    }

    private int getLayoutIdForPosition(int position){
        return layoutId;
    }

    @NonNull
    @Override
    public VacancyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        return new VacancyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VacancyViewHolder holder, int position) {
        holder.bind(mViewModel, position);
    }

    @Override
    public int getItemCount() {
        return mVacancies == null ? 0 :mVacancies.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public void setVacancies(List<Vacancy> vacancies) {
        if(vacancies != null){
            mVacancies = vacancies;
        }
    }

    class VacancyViewHolder extends RecyclerView.ViewHolder{
        final ViewDataBinding mBinding;
        public VacancyViewHolder(ViewDataBinding binding){
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(VacancyListViewModel viewModel, Integer position){
            mBinding.setVariable(BR.vacancyViewModel, viewModel);
            mBinding.setVariable(BR.vacancyPosition, position);
            mBinding.executePendingBindings();
        }
    }
}
