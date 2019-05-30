package com.yuliia.bookonlinelistener.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yuliia.bookonlinelistener.R;
import com.yuliia.bookonlinelistener.entity.AudioBook;

public class ListenAudioBookActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private AudioBook data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_audio_book);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        mToolbar = findViewById(R.id.tb_listen_audio_book_activity);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
    }


}
