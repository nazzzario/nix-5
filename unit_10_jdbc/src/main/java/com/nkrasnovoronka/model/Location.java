package com.nkrasnovoronka.model;

import java.util.Objects;

public class Location {
    private int id;
    private String text;

    public Location() {
    }

    public Location(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return id == location.id && Objects.equals(text, location.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
