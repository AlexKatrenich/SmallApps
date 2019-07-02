package com.katrenich.alex.klara.vacancyScreen.model;

import java.util.Objects;

public class Vacancy {
    private String caption;
    private String shortDescription;

    public Vacancy(String caption) {
        this.caption = caption;
    }

    public Vacancy(String caption, String shortDescription) {
        this.caption = caption;
        this.shortDescription = shortDescription;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(caption, vacancy.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caption);
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "caption='" + caption + '\'' +
                '}';
    }
}
