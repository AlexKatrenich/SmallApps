package com.katrenich.alex.klara.vacancyScreen.model;

import java.util.Objects;

public class VacancyResponse {
    private String name;
    private String email;
    private String comment;

    public VacancyResponse() {

    }

    public VacancyResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public VacancyResponse(String name, String email, String comment) {
        this.name = name;
        this.email = email;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacancyResponse that = (VacancyResponse) o;
        return name.equals(that.name) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }

    @Override
    public String toString() {
        return "VacancyResponse{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
