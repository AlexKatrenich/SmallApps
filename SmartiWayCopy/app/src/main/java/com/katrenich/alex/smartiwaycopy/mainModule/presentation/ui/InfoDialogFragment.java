package com.katrenich.alex.smartiwaycopy.mainModule.presentation.ui;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.katrenich.alex.smartiwaycopy.R;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.presenter.InfoDialogPresenter;
import com.katrenich.alex.smartiwaycopy.mainModule.presentation.view.InfoView;

public class InfoDialogFragment extends MvpAppCompatDialogFragment implements InfoView {
    public static final String TAG = "InfoDialogFragment";

    @InjectPresenter
    InfoDialogPresenter mPresenter;

    private TextView btnPhone, btnQuestions, btnCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("Обери метод зв'язку");
        View v = inflater.inflate(R.layout.fragment_dialog_info, container, false);

        initUI(v);
        return v;
    }

    private void initUI(View v) {
        btnPhone = v.findViewById(R.id.tv_info_dialog_fragment_button_phone);
        btnPhone.setOnClickListener(mPresenter::onPhoneBtnClick);
        btnQuestions = v.findViewById(R.id.tv_info_dialog_fragment_button_questions);
        btnQuestions.setOnClickListener(mPresenter::onQuestionsBtnClick);
        btnCancel = v.findViewById(R.id.tv_info_dialog_fragment_button_cancel);
        btnCancel.setOnClickListener(mPresenter::onCancelBtnClick);
    }

    @Override
    public void hideDialog() {
        dismiss();
    }
}
