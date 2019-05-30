package com.yuliia.bookonlinelistener.data;

import android.os.AsyncTask;
import android.util.Log;

import com.yuliia.bookonlinelistener.entity.AudioBook;
import com.yuliia.bookonlinelistener.ui.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AudioBooksInfoLoader {
    public static final String TAG = "AudioBooksInfoLoader";
    private MainActivity mActivity;
    private String mainRefference = "https://audioknigi.club/";
    private boolean isLoading = false;
    private static AudioBooksInfoLoader instance;
    private List<AudioBook> data;

    private AudioBooksInfoLoader(){

    }

    public static AudioBooksInfoLoader getInstance() {
        if (instance == null){
            instance = new AudioBooksInfoLoader();
        }
        return instance;
    }

    public void bind(MainActivity activity){
        mActivity = activity;
        if (data == null){
            if (!isLoading){
                isLoading = true;
                mActivity.showProgressBar(true);
                mActivity.showProgressStatus(0);
                new Loader().execute(mainRefference);
            }
        } else {
            mActivity.updateList(data);
        }


    }

    public void unbind(){
        mActivity = null;
    }

    private void updateUI(List<AudioBook> list){
        if (mActivity != null){
            mActivity.updateList(list);
        }
    }

    private class Loader extends AsyncTask<String, Integer , List<AudioBook>>{

        @Override
        protected List<AudioBook> doInBackground(String... strings) {
            int progress = 10;
            String ref = strings[0];
            Document doc = null;
            ArrayList<AudioBook> books = new ArrayList<>();
            try {
                publishProgress(progress);
                doc = Jsoup.connect(ref).get();
            } catch (IOException e){
                Log.e(TAG, "doInBackground: ", e);
            }

            if (doc != null){
                publishProgress(progress + 10);
                publishProgress(progress);
                Elements elementsSize = doc.select("article[class=ls-topic ls-topic-a-list topic-type-book js-topic]");
                int size = elementsSize.size();
                for (int i = 0; i < size; i++) {
                    publishProgress(++progress);
                    // getting book title
                    Elements bookTitle = doc.select("h3[class=ls-topic-title]").select("a").eq(i);
                    String title = bookTitle.text();

                    // getting book genre
                    Elements bookGenre = doc.select("div[class=topic-blog]").select("a").eq(i);
                    String genre = bookGenre.text();

                    // getting author
                    Elements bookAuthor = doc.select("div[class=a-info-item]").select("a[rel=author]").eq(i);
                    String author = bookAuthor.text();

                    // getting reader
                    Elements bookReader = doc.select("div[class=a-info-item]").select("a[rel=performer]").eq(i);
                    String reader = bookReader.text();

                    // getting time
                    Elements bookListenTimeHours = doc.select("div[class=a-info-item]").select("span[class=hours]").eq(i);
                    Elements bookListenTimeMinutes = doc.select("div[class=a-info-item]").select("span[class=minutes]").eq(i);
                    String listenTime = bookListenTimeHours.text() + " " + bookListenTimeMinutes.text();

                    // getting image refference
                    Elements bookImageRefference = doc.select("img[class=topic_preview]").eq(i);
                    String refference = bookImageRefference.attr("src");
                    Log.i(TAG, "doInBackground: Ref: " + refference);

                    AudioBook book = new AudioBook(title, genre, author, reader, listenTime);
                    book.setImageLink(refference);
                    books.add(book);
                    publishProgress();
                }
                progress = 90;
                publishProgress(progress);
            }

            return books;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (values.length > 0) mActivity.showProgressStatus(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<AudioBook> audioBooks) {
            super.onPostExecute(audioBooks);
            isLoading = false;
            data = audioBooks;
            updateUI(audioBooks);
            mActivity.showProgressStatus(100);
            mActivity.showProgressBar(false);
        }
    }
}
