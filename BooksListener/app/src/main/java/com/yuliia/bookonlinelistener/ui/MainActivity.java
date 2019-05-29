package com.yuliia.bookonlinelistener.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.yuliia.bookonlinelistener.R;
import com.yuliia.bookonlinelistener.adapters.PopularBooksRecyclerViewAdapter;
import com.yuliia.bookonlinelistener.entity.AudioBook;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PopularBooksRecyclerViewAdapter mAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        ArrayList<AudioBook> list = new ArrayList<>();
        list.add(new AudioBook(1, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        list.add(new AudioBook(2, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        list.add(new AudioBook(3, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        list.add(new AudioBook(4, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        list.add(new AudioBook(5, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        list.add(new AudioBook(6, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        list.add(new AudioBook(7, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        list.add(new AudioBook(8, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        list.add(new AudioBook(9, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        list.add(new AudioBook(10, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        list.add(new AudioBook(11, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        list.add(new AudioBook(12, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        list.add(new AudioBook(13, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        list.add(new AudioBook(14, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        list.add(new AudioBook(15, "Воровка", "ФАНТАСТИКА, ФЭНТЕЗИ", "Марина Милованова","Надежда Колганова" ,"8 часов 18 минут"));
        mAdapter.setBooksList(list);

    }

    @Override
    protected void onDestroy() {
        mRecyclerView.setLayoutManager(null);
        mRecyclerView.setAdapter(null);
        super.onDestroy();
    }
}
