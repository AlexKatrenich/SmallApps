package com.yuliia.bookonlinelistener.data;

import com.yuliia.bookonlinelistener.entity.AudioBook;
import com.yuliia.bookonlinelistener.entity.AudioTrack;
import com.yuliia.bookonlinelistener.ui.ListenAudioBookActivity;

import java.util.List;

public class AudioBookActivityController {
    private static AudioBookActivityController instance;
    private ListenAudioBookActivity mActivity;
    private AudioBook mAudioBook;
    private List<AudioTrack> mAudioTracks;


    private AudioBookActivityController() {
    }

    public static AudioBookActivityController getInstance(){
        if (instance == null) instance = new AudioBookActivityController();

        return instance;
    }

    public void bind(ListenAudioBookActivity activity){
        mActivity = activity;
        updateUI();
    }

    public void unbind(){
        mActivity = null;
    }

    private void updateUI(){
        if(mActivity != null) mActivity.setBookinfo(mAudioBook);
        if(mActivity != null) mActivity.setAudioTrackList(mAudioTracks);
    }

    public void setAudioBook(AudioBook audioBook) {
        mAudioBook = audioBook;
    }

}
