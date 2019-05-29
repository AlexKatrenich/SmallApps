package com.yuliia.bookonlinelistener.entity;

import java.io.StringReader;
import java.util.Objects;

public class AudioBook {
    private int id;
    private String title;
    private String genre;
    private String author;
    private String reader;
    private String listenTime;

    public AudioBook(int id) {
        this.id = id;
    }

    public AudioBook(int id, String title, String genre, String author, String reader, String listenTime) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.reader = reader;
        this.listenTime = listenTime;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    private String imageLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getListenTime() {
        return listenTime;
    }

    public void setListenTime(String listenTime) {
        this.listenTime = listenTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AudioBook audioBook = (AudioBook) o;
        return id == audioBook.id &&
                Objects.equals(title, audioBook.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "AudioBook{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", listenTime='" + listenTime + '\'' +
                '}';
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }
}
