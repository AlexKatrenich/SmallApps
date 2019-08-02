package com.katrenich.alex.cashnote.presentation.ui;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.katrenich.alex.cashnote.R;
import com.katrenich.alex.cashnote.presentation.presenter.CashNotesActivityPresenter;
import com.katrenich.alex.cashnote.presentation.view.CashNotesView;

public class CashNotesActivity extends MvpAppCompatActivity implements CashNotesView {

    @InjectPresenter
    CashNotesActivityPresenter mPresenter;

    private FloatingActionButton btnNewNote;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        btnNewNote = findViewById(R.id.btn_cash_notes_activity_new_note);
        btnNewNote.setOnClickListener(mPresenter::onBtnNewNoteClicked);
    }
}
