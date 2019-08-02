package com.katrenich.alex.cashnote.presentation.ui;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.katrenich.alex.cashnote.R;
import com.katrenich.alex.cashnote.presentation.presenter.EditCashNoteActivityPresenter;
import com.katrenich.alex.cashnote.presentation.view.EditCashNoteView;

public class EditCashNoteActivity extends MvpAppCompatActivity implements EditCashNoteView {

    @InjectPresenter
    EditCashNoteActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_note_edit);
        initUI(savedInstanceState);
    }

    private void initUI(Bundle savedInstanceState) {

    }
}
