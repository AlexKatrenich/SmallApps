package com.katrenich.alex.smartiwaycopy.creditModule.presentation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.creditModule.presentation.presenter.UserDocumentsFragmentPresenter;
import com.katrenich.alex.smartiwaycopy.creditModule.presentation.view.UserDocumentsView;

public class UserDocumentsFragment extends MvpAppCompatFragment implements UserDocumentsView {

    @InjectPresenter
    UserDocumentsFragmentPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_documents, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view, savedInstanceState);

    }

    private void initUI(View v, Bundle savedInstanceState) {

    }

}
