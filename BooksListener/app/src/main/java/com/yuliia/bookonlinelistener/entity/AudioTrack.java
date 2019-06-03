package com.yuliia.bookonlinelistener.entity;

import java.util.Objects;

public class AudioTrack {

    private String title;
    private boolean isSelected = false;
    private String playTime;
    private String loadRefference;

    public AudioTrack() {
    }

    public AudioTrack(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public String getLoadRefference() {
        return loadRefference;
    }

    public void setLoadRefference(String loadRefference) {
        this.loadRefference = loadRefference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AudioTrack that = (AudioTrack) o;
        return title.equals(that.title) &&
                playTime.equals(that.playTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, playTime);
    }

    @Override
    public String toString() {
        return "AudioTrack{" +
                "title='" + title + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
