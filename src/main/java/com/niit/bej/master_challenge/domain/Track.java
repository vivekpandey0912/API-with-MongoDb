package com.niit.bej.master_challenge.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Track {

    @Id
    private int id;
    private String name;
    private int rating;
    private Artist artist;

    public Track(int id, String name, int rating, Artist artist) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.artist = artist;
    }

    public Track() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return id == track.id && rating == track.rating && Objects.equals(name, track.name) && Objects.equals(artist, track.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating, artist);
    }
}
