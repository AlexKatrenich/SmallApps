package com.yuliia.bookonlinelistener.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuliia.bookonlinelistener.R;
import com.yuliia.bookonlinelistener.entity.AudioBook;

import java.util.ArrayList;
import java.util.List;

public class PopularBooksRecyclerViewAdapter extends RecyclerView.Adapter<PopularBooksRecyclerViewAdapter.BooksViewHolder> {
    private List<AudioBook> mBooks = new ArrayList<>();

    @NonNull
    @Override
    public PopularBooksRecyclerViewAdapter.BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_online_books_list_rv, parent, false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularBooksRecyclerViewAdapter.BooksViewHolder holder, int i) {
        holder.bind(mBooks.get(i));
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public void setBooksList(List<AudioBook> list){
        mBooks = list;
        notifyDataSetChanged();
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private AppCompatImageView bookImg;
        private TextView bookTitle
                , bookGenre
                , bookAuthor
                , bookReader
                , bookListenTime;


        public BooksViewHolder(@NonNull View view) {
            super(view);
            bookImg = view.findViewById(R.id.iv_books_list_item_image);
            bookTitle = view.findViewById(R.id.tv_books_list_title);
            bookGenre = view.findViewById(R.id.tv_books_list_genre);
            bookAuthor = view.findViewById(R.id.tv_books_list_author);
            bookReader = view.findViewById(R.id.tv_books_list_reader);
            bookListenTime = view.findViewById(R.id.tv_books_listen_time);
            mContext = view.getContext();
        }


        protected void bind(AudioBook book){
            if(book.getTitle() != null) bookTitle.setText(book.getTitle());
            if (book.getAuthor() != null) bookGenre.setText(book.getGenre());
            if (book.getListenTime() != null) bookListenTime.setText(book.getListenTime());
            if (book.getReader() != null) bookReader.setText(book.getReader());
            if (book.getAuthor() != null) bookAuthor.setText(book.getAuthor());

            if (book.getImageLink() != null) {
                Glide.with(mContext)
                        .load(book.getImageLink())
                        .placeholder(R.drawable.no_image)
                        .into(bookImg);
            }
        }

    }
}
