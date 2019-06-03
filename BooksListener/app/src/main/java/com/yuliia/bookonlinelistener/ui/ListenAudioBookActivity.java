package com.yuliia.bookonlinelistener.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yuliia.bookonlinelistener.R;
import com.yuliia.bookonlinelistener.adapters.AudioTracksRecyclerViewAdapter;
import com.yuliia.bookonlinelistener.data.AudioBookActivityController;
import com.yuliia.bookonlinelistener.entity.AudioBook;
import com.yuliia.bookonlinelistener.entity.AudioTrack;

import java.util.List;

public class ListenAudioBookActivity extends AppCompatActivity {
    public static final String TAG_AUDIO_BOOK_TITLE = "TAG_AUDIO_BOOK_TITLE";
    private Toolbar mToolbar;
    private RecyclerView trackList;
    private AudioTracksRecyclerViewAdapter mAudioTracksRecyclerViewAdapter;

    private TextView bookTitle, bookListenTime, bookAuthor, bookReader;
    private TextView trackTitle, trackCurrentTime, trackTotalTime;
    private AppCompatImageButton btnPlayStop;
    private SeekBar mSeekBar;

    private boolean play = false;

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

        bookTitle = findViewById(R.id.tv_audiobook_title);
        bookAuthor = findViewById(R.id.tv_audiobook_author);
        bookReader = findViewById(R.id.tv_audiobook_reader);
        bookListenTime = findViewById(R.id.tv_audiobook_time);

        trackTitle = findViewById(R.id.tv_audio_track_title);
        trackCurrentTime = findViewById(R.id.tv_audio_track_current_time);
        trackTotalTime = findViewById(R.id.tv_audio_track_total_time);

        btnPlayStop = findViewById(R.id.btn_stop_play_audio_track);
        btnPlayStop.setOnClickListener(v -> {
            if (play){
                play = false;
                btnPlayStop.setImageResource(R.drawable.ic_play);
                AudioBookActivityController.getInstance().pausePlay();
            } else {
                play = true;
                btnPlayStop.setImageResource(R.drawable.ic_pause);
                AudioBookActivityController.getInstance().startPlay();
            }
        });

        mSeekBar = findViewById(R.id.sb_audio_track_line);

        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        trackList = findViewById(R.id.rv_audio_track_list);
        mAudioTracksRecyclerViewAdapter = new AudioTracksRecyclerViewAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        trackList.setAdapter(mAudioTracksRecyclerViewAdapter);
        trackList.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        String bookRef = intent.getStringExtra(TAG_AUDIO_BOOK_TITLE);
        if (bookRef != null && savedInstanceState == null) AudioBookActivityController.getInstance().setBookReference(bookRef);
    }

    public void setMaxDurationSeekBar(int duration){
        mSeekBar.setMax(duration);
    }

    public void setSeekBarProgress(int progress){
        mSeekBar.setProgress(progress);
    }

    public void setBookinfo(AudioBook audioBook) {
        if (audioBook == null) return;
        if (audioBook.getTitle() != null) bookTitle.setText(audioBook.getTitle());
        if (audioBook.getAuthor() != null) bookAuthor.setText(audioBook.getAuthor());
        if (audioBook.getReader() != null) bookReader.setText(audioBook.getReader());
        if (audioBook.getListenTime() != null) bookListenTime.setText(audioBook.getListenTime());
    }

    public void setAudioTrackList(List<AudioTrack> audioTracks) {
        if(audioTracks != null && audioTracks.size() > 0){
            mAudioTracksRecyclerViewAdapter.setTracks(audioTracks);
            AudioTrack track = audioTracks.get(0);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        AudioBookActivityController.getInstance().bind(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        AudioBookActivityController.getInstance().unbind();
    }
}
