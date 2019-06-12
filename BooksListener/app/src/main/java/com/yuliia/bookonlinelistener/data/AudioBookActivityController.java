package com.yuliia.bookonlinelistener.data;

import android.media.MediaPlayer;
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
    private PlayerStatus mPlayerStatus = PlayerStatus.DESTROYED;

    private AudioBookActivityController() {
        // hardcoded audioTrack references
        mAudioTracks = new ArrayList<>();
        mAudioTracks.add(new AudioTrack("А жизнь, как записка(Tears of Memory)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%90%20%D0%B6%D0%B8%D0%B7%D0%BD%D1%8C,%20%D0%BA%D0%B0%D0%BA%20%D0%B7%D0%B0%D0%BF%D0%B8%D1%81%D0%BA%D0%B0(Tears%20of%20Memory).mp3"));
        mAudioTracks.add(new AudioTrack("А жизнь это сумка(Memory Lane)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%90%20%D0%B6%D0%B8%D0%B7%D0%BD%D1%8C%20%D1%8D%D1%82%D0%BE%20%D1%81%D1%83%D0%BC%D0%BA%D0%B0(Memory%20Lane).mp3"));
        mAudioTracks.add(new AudioTrack("А ты похожа, на ту, из прошлого(Иван Савоськин)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%90%20%D1%82%D1%8B%20%D0%BF%D0%BE%D1%85%D0%BE%D0%B6%D0%B0,%20%D0%BD%D0%B0%20%D1%82%D1%83,%20%D0%B8%D0%B7%20%D0%BF%D1%80%D0%BE%D1%88%D0%BB%D0%BE%D0%B3%D0%BE(%D0%98%D0%B2%D0%B0%D0%BD%20%D0%A1%D0%B0%D0%B2%D0%BE%D1%81%D1%8C%D0%BA%D0%B8%D0%BD).mp3"));
        mAudioTracks.add(new AudioTrack("Весь этот мир(Иван Савоськин)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%92%D0%B5%D1%81%D1%8C%20%D1%8D%D1%82%D0%BE%D1%82%20%D0%BC%D0%B8%D1%80(%D0%98%D0%B2%D0%B0%D0%BD%20%D0%A1%D0%B0%D0%B2%D0%BE%D1%81%D1%8C%D0%BA%D0%B8%D0%BD).mp3"));
        mAudioTracks.add(new AudioTrack("Вручили жизнь(Иван Савоськин)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%92%D1%80%D1%83%D1%87%D0%B8%D0%BB%D0%B8%20%D0%B6%D0%B8%D0%B7%D0%BD%D1%8C(%D0%98%D0%B2%D0%B0%D0%BD%20%D0%A1%D0%B0%D0%B2%D0%BE%D1%81%D1%8C%D0%BA%D0%B8%D0%BD).mp3"));
        mAudioTracks.add(new AudioTrack("Дом(Tears of Memory)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%94%D0%BE%D0%BC(Tears%20of%20Memory).mp3"));
        mAudioTracks.add(new AudioTrack("Зима,весна ли(Иван Савоськин)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%97%D0%B8%D0%BC%D0%B0,%D0%B2%D0%B5%D1%81%D0%BD%D0%B0%20%D0%BB%D0%B8(%D0%98%D0%B2%D0%B0%D0%BD%20%D0%A1%D0%B0%D0%B2%D0%BE%D1%81%D1%8C%D0%BA%D0%B8%D0%BD).mp3"));
        mAudioTracks.add(new AudioTrack("Когда за двадцать(Иван Савоськин)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%9A%D0%BE%D0%B3%D0%B4%D0%B0%20%D0%B7%D0%B0%20%D0%B4%D0%B2%D0%B0%D0%B4%D1%86%D0%B0%D1%82%D1%8C(%D0%98%D0%B2%D0%B0%D0%BD%20%D0%A1%D0%B0%D0%B2%D0%BE%D1%81%D1%8C%D0%BA%D0%B8%D0%BD).mp3"));
        mAudioTracks.add(new AudioTrack("Когда ты не ищешь ни бледного солнца(Zomer Craft)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%9A%D0%BE%D0%B3%D0%B4%D0%B0%20%D1%82%D1%8B%20%D0%BD%D0%B5%20%D0%B8%D1%89%D0%B5%D1%88%D1%8C%20%D0%BD%D0%B8%20%D0%B1%D0%BB%D0%B5%D0%B4%D0%BD%D0%BE%D0%B3%D0%BE%20%D1%81%D0%BE%D0%BB%D0%BD%D1%86%D0%B0(Zomer%20Craft).mp3"));
        mAudioTracks.add(new AudioTrack("Когда уходят навсегда(Иван Савоськин)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%9A%D0%BE%D0%B3%D0%B4%D0%B0%20%D1%83%D1%85%D0%BE%D0%B4%D1%8F%D1%82%20%D0%BD%D0%B0%D0%B2%D1%81%D0%B5%D0%B3%D0%B4%D0%B0(%D0%98%D0%B2%D0%B0%D0%BD%20%D0%A1%D0%B0%D0%B2%D0%BE%D1%81%D1%8C%D0%BA%D0%B8%D0%BD).mp3"));
        mAudioTracks.add(new AudioTrack("Набережная неизлечимых(June_Ellington)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%9D%D0%B0%D0%B1%D0%B5%D1%80%D0%B5%D0%B6%D0%BD%D0%B0%D1%8F%20%D0%BD%D0%B5%D0%B8%D0%B7%D0%BB%D0%B5%D1%87%D0%B8%D0%BC%D1%8B%D1%85(June_Ellington).mp3"));
        mAudioTracks.add(new AudioTrack("Не мне ли ты говорил когда-то(Иван Савоськин)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%9D%D0%B5%20%D0%BC%D0%BD%D0%B5%20%D0%BB%D0%B8%20%D1%82%D1%8B%20%D0%B3%D0%BE%D0%B2%D0%BE%D1%80%D0%B8%D0%BB%20%D0%BA%D0%BE%D0%B3%D0%B4%D0%B0-%D1%82%D0%BE(%D0%98%D0%B2%D0%B0%D0%BD%20%D0%A1%D0%B0%D0%B2%D0%BE%D1%81%D1%8C%D0%BA%D0%B8%D0%BD).mp3"));
        mAudioTracks.add(new AudioTrack("Осень(Вирус)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%9E%D1%81%D0%B5%D0%BD%D1%8C(%D0%92%D0%B8%D1%80%D1%83%D1%81).mp3"));
        mAudioTracks.add(new AudioTrack("Поезд в рай (Ткач Грёз)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%9F%D0%BE%D0%B5%D0%B7%D0%B4%20%D0%B2%20%D1%80%D0%B0%D0%B9%20(%D0%A2%D0%BA%D0%B0%D1%87%20%D0%93%D1%80%D1%91%D0%B7).mp3"));
        mAudioTracks.add(new AudioTrack("Почему-то ошибки(Иван Савоськин)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%9F%D0%BE%D1%87%D0%B5%D0%BC%D1%83-%D1%82%D0%BE%20%D0%BE%D1%88%D0%B8%D0%B1%D0%BA%D0%B8(%D0%98%D0%B2%D0%B0%D0%BD%20%D0%A1%D0%B0%D0%B2%D0%BE%D1%81%D1%8C%D0%BA%D0%B8%D0%BD).mp3"));
        mAudioTracks.add(new AudioTrack("Проснувшись утром(Иван Савоськин)", "https://get.aknigi.club/b/50160/KIiAdTmCF59xFgwTqbbviQ,,/%D0%9F%D1%80%D0%BE%D1%81%D0%BD%D1%83%D0%B2%D1%88%D0%B8%D1%81%D1%8C%20%D1%83%D1%82%D1%80%D0%BE%D0%BC(%D0%98%D0%B2%D0%B0%D0%BD%20%D0%A1%D0%B0%D0%B2%D0%BE%D1%81%D1%8C%D0%BA%D0%B8%D0%BD).mp3"));
    }

    public static AudioBookActivityController getInstance(){
        if (instance == null) instance = new AudioBookActivityController();
        return instance;
    }

    public void bind(ListenAudioBookActivity activity){
        mActivity = activity;
        if (mAudioBook != null) {
            updateUI();
        } else {
            loadBook();
        }
    }

    public void unbind(){
        mActivity = null;
        stopPlay();
    }

    private void updateUI(){
        if(mActivity != null) mActivity.setBookinfo(mAudioBook);
        if(mActivity != null) mActivity.setAudioTrackList(mAudioTracks);
        if(mActivity != null) mActivity.setSelectedTrack(currentTrack);
    }

    public void loadBook() {
        if (bookReference != null){
            new Loader().execute(bookReference);
        }
    }

    public void startPlay(){
        if (mPlayerStatus != PlayerStatus.DESTROYED){
            mediaPlayer.start();
        } else {
            try {
                if (currentTrack == null) currentTrack = mAudioTracks.get(0);
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(currentTrack.getLoadRefference());
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                Log.e(TAG, "startPlay: ", e);
            }
        }
        new SeekBarChange().execute();
    }

    public void setCurrentTrack(AudioTrack track){
        currentTrack = track;
        updateUI();
        stopPlay();
    }

    public void stopPlay() {
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
            mPlayerStatus = PlayerStatus.DESTROYED;
            if (mActivity != null) {
                mActivity.setSeekBarProgress(0);
                mActivity.changePlayIcon(true);
            }
        }
    }

    public void pausePlay(){
        if(mediaPlayer != null) mediaPlayer.pause();
        mPlayerStatus = PlayerStatus.PAUSED;
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
        mPlayerStatus = PlayerStatus.CREATED;
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

            while (mActivity != null && mediaPlayer != null){
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

    public PlayerStatus getPlayerStatus(){
        return mPlayerStatus;
    }

    public enum PlayerStatus{
        CREATED,
        PAUSED,
        DESTROYED
    }
}
