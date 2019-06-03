package com.yuliia.bookonlinelistener.data;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.yuliia.bookonlinelistener.entity.AudioBook;
import com.yuliia.bookonlinelistener.entity.AudioTrack;
import com.yuliia.bookonlinelistener.ui.ListenAudioBookActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AudioBookActivityController implements MediaPlayer.OnPreparedListener{
    public static final String TAG = "AudioBookActivityContr";
    private static AudioBookActivityController instance;
    private ListenAudioBookActivity mActivity;
    private AudioBook mAudioBook;
    private AudioTrack currentTrack;
    private List<AudioTrack> mAudioTracks;
    private MediaPlayer mediaPlayer;
    private String bookReference = null;
    private String trackRef = "https://get.aknigi.club/b/49949/rtbp2yMXf73yTE8MuWAK0A,,/11%20%D0%A7%D0%B0%D1%81%D1%82%D1%8C%201.%20%D0%93%D0%BB%D0%B0%D0%B2%D0%B0%2011.%20%D0%A7%D0%B5%D1%82%D0%B2%D0%B5%D1%80%D0%BE%20%D0%9F%D1%80%D0%BE%D1%82%D0%B8%D0%B2%20%D0%93%D1%80%D0%B8%D0%BC%D1%81%D0%B1%D0%B8.mp3";
    private boolean playerActive = false;

    private AudioBookActivityController() {
    }

    public static AudioBookActivityController getInstance(){
        if (instance == null) instance = new AudioBookActivityController();

        return instance;
    }

    public void bind(ListenAudioBookActivity activity){
        mActivity = activity;
        mediaPlayer = new MediaPlayer();
        if (mAudioBook != null) {
            updateUI();
        } else {
            loadBook();
        }
    }

    public void unbind(){
        mActivity = null;
        mediaPlayer.release();
        mediaPlayer = null;
        playerActive = false;
    }

    private void updateUI(){
        if(mActivity != null) mActivity.setBookinfo(mAudioBook);
        if(mActivity != null) mActivity.setAudioTrackList(mAudioTracks);
    }

    public void loadBook() {
        if (bookReference != null){
            new Loader().execute(bookReference);
        }
    }

    public void startPlay(){
        if (playerActive){
            mediaPlayer.start();
        } else {
            try {
                mediaPlayer.setDataSource(trackRef);
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                Log.e(TAG, "startPlay: ", e);
            }
        }
        new SeekBarChange().execute();
    }

    public void pausePlay(){
        mediaPlayer.pause();
    }

    public void setBookReference(String bookReference) {
        this.bookReference = bookReference;
        loadBook();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer = mp;
        if (mActivity != null) mActivity.setMaxDurationSeekBar(mediaPlayer.getDuration());
        mp.start();
        playerActive = true;
    }

    private class Loader extends AsyncTask<String, AudioBook, List<AudioTrack>>{

        @Override
        protected List<AudioTrack> doInBackground(String... strings) {
            String ref = strings[0];
            Document doc = null;
            ArrayList<AudioTrack> tracks = new ArrayList<>();

            try {
                doc = Jsoup.connect(ref).get();
            } catch (IOException e) {
                Log.e(TAG, "doInBackground: ", e);
            }

            if (doc != null){
                Elements elementsSize = doc.select("article[class=ls-topic ls-topic--single   topic js-topic ]");
                int size = elementsSize.size();
                for (int i = 0; i < size; i++) {
                    // getting book title
                    Elements bookTitle = doc.select("h1[class=ls-topic-title ls-word-wrap]").eq(i);
                    String title = bookTitle.text();

                    // getting book genre
                    Elements bookGenre = doc.select("li[class=ls-topic-info-item ls-topic-info-item--blog]").select("a").eq(i);
                    String genre = bookGenre.text();

                    // getting author
                    Elements bookAuthor = doc.select("span[itemprop=author]").eq(i);
                    String author = bookAuthor.text();

                    // getting reader
                    Elements bookReader = doc.select("div[class=panel-item]").select("a[rel=performer]").eq(i);
                    String reader = bookReader.text();

                    // getting time
                    Elements bookListenTimeHours = doc.select("div[class=panel-item]").select("span[class=hours]").eq(i);
                    Elements bookListenTimeMinutes = doc.select("div[class=panel-item]").select("span[class=minutes]").eq(i);
                    String listenTime = bookListenTimeHours.text() + " " + bookListenTimeMinutes.text();


                    AudioBook book = new AudioBook(title, genre, author, reader, listenTime);
                    publishProgress(book);

                    Elements audio = doc.select("audio").eq(i);
                    String audioRef = audio.attr("src");
                    Log.i(TAG, "doInBackground: " + audioRef);
                }
            }

            return tracks;
        }

        @Override
        protected void onProgressUpdate(AudioBook... values) {
            if (values.length > 0) {
                mAudioBook = values[0];
                updateUI();
            }
            super.onProgressUpdate(values);
        }

    }

    private class SeekBarChange extends AsyncTask<Void, Integer, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            while (mActivity != null){
                int mCurrentPosition = mediaPlayer.getCurrentPosition();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(mCurrentPosition);
                Log.i(TAG, "doInBackground: Progress: " + mCurrentPosition);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (values.length > 0){
                if (mActivity != null) mActivity.setSeekBarProgress(values[0]);
            }
            super.onProgressUpdate(values);
        }
    }

}
