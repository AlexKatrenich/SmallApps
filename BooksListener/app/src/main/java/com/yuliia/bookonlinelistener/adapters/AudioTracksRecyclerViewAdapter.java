package com.yuliia.bookonlinelistener.adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuliia.bookonlinelistener.R;
import com.yuliia.bookonlinelistener.entity.AudioTrack;

import java.util.ArrayList;
import java.util.List;

public class AudioTracksRecyclerViewAdapter extends RecyclerView.Adapter<AudioTracksRecyclerViewAdapter.TrackViewHolder> {
    private List<AudioTrack> mTracks = new ArrayList<>();

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_audio_tracks_list, viewGroup, false);

        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int i) {
        holder.bind(mTracks.get(i));
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    public void setTracks(List<AudioTrack> tracks){
        mTracks = tracks;
        notifyDataSetChanged();
    }

    protected void changeSelectedItem(AudioTrack track){
        for (AudioTrack t : mTracks) {
            if (!t.equals(track)) t.setSelected(false);
        }

        notifyDataSetChanged();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder{
        private TextView trackCaption;
        private boolean isSelected;
        private AudioTrack data;

        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);
            trackCaption = itemView.findViewById(R.id.tv_audio_track_caption);
            itemView.setOnClickListener(v -> {
                data.setSelected(true);
                trackCaption.setBackgroundColor(Color.YELLOW);
                changeSelectedItem(data);
            });
        }

        protected void bind(AudioTrack track){
            data = track;
            trackCaption.setText(track.getTitle());
            isSelected = track.isSelected();
            if (isSelected) {
                trackCaption.setBackgroundColor(Color.YELLOW);
            } else {
                trackCaption.setBackgroundColor(Color.WHITE);
            }
        }


    }
}
