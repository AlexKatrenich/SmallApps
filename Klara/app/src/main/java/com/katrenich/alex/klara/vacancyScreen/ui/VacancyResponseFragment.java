package com.katrenich.alex.klara.vacancyScreen.ui;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.katrenich.alex.klara.R;
import com.katrenich.alex.klara.databinding.FragmentVacancyResponceBinding;
import com.katrenich.alex.klara.vacancyScreen.viewmodel.VacancyResponseViewModel;

public class VacancyResponseFragment extends BottomSheetDialogFragment {
    public static final String TAG = "VacancyResponseFragment";
    private VacancyResponseViewModel mViewModel;

    public VacancyResponseFragment() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(dialog1 -> {
            BottomSheetDialog d = (BottomSheetDialog) dialog1;
            FrameLayout bottomSheet = d.findViewById(android.support.design.R.id.design_bottom_sheet);
            BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);
        });
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentVacancyResponceBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vacancy_responce, container, false);
        View view = binding.getRoot();
        mViewModel = new VacancyResponseViewModel();
        binding.setVacancyResponseViewModel(mViewModel);
        binding.setVacancyResponseFragment(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel.init();
        super.onViewCreated(view, savedInstanceState);
    }

    public void hideView(View view){
        this.dismiss();
    }
}
