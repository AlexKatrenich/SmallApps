package com.yuliia.bookonlinelistener.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.yuliia.bookonlinelistener.R;
import com.yuliia.bookonlinelistener.adapters.PopularBooksRecyclerViewAdapter;
import com.yuliia.bookonlinelistener.data.AudioBooksInfoLoader;
import com.yuliia.bookonlinelistener.entity.AudioBook;


import java.util.List;

public class BooksListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PopularBooksRecyclerViewAdapter mAdapter;
    private Toolbar mToolbar;
    private AudioBooksInfoLoader mLoader;
    private ProgressBar mProgressBar;
    private ImageButton btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        mRecyclerView = findViewById(R.id.rv_online_books_list);
        mAdapter = new PopularBooksRecyclerViewAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);

        mToolbar = findViewById(R.id.tb_books_list_activity);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mProgressBar = findViewById(R.id.pb_books_list_download);
        mProgressBar.setMax(100);

        mLoader = AudioBooksInfoLoader.getInstance();
        btnRefresh = findViewById(R.id.btn_refresh_book_list);
        btnRefresh.setOnClickListener(v -> mLoader.loadData());
    }

    @Override
    protected void onStart() {
        mLoader.bind(this);
        super.onStart();
    }



    @Override
    protected void onStop() {
        mLoader.unbind();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mRecyclerView.setLayoutManager(null);
        mRecyclerView.setAdapter(null);
        super.onDestroy();
    }

    public void updateList(List<AudioBook> list) {
        mAdapter.setBooksList(list);
    }

    public void showProgressStatus(int status){
        if(status >= 100){
            mProgressBar.setProgress(100);
        } else if(status < 0){
            mProgressBar.setProgress(0);
        } else {
            mProgressBar.setProgress(status);
        }
    }

    public void showProgressBar(boolean b){
        if (b){
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
