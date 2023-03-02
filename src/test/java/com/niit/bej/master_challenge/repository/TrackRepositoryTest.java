package com.niit.bej.master_challenge.repository;

import com.niit.bej.master_challenge.domain.Artist;
import com.niit.bej.master_challenge.domain.Track;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataMongoTest
class TrackRepositoryTest {


    Track track;
    Artist artist;
    @Autowired
    TrackRepository trackRepository;

    @BeforeEach
    void setUp() {
        artist = new Artist(1,"Arijit");
        track = new Track(2,"Rom",4,artist);

    }

    @AfterEach
    void tearDown() {
        artist = null;
        track = null;
        trackRepository.deleteAll();
    }


    @Test
    @DisplayName("Get All Track Test case")
    void getAllTrack()
    {
        trackRepository.save(track);
        List<Track> trackList = trackRepository.findAll();
        assertEquals(1,trackList.size());
    }
    @Test
    @DisplayName("Add Tracks Test")
    void AddTrack()
    {
        trackRepository.save(track);
        artist = new Artist(2,"Jubin");
        track = new Track(3,"Emo",5,artist);
        trackRepository.save(track);
        List<Track> trackList = trackRepository.findAll();
        assertEquals(2,trackList.size());
    }
    @Test
    @DisplayName("Delete track by Id")
    void deleteTrackById()
    {
        trackRepository.save(track);
        artist = new Artist(2,"Jubin");
        track = new Track(3,"Emo",5,artist);
        trackRepository.save(track);
        trackRepository.deleteById(3);
        List<Track> trackList = trackRepository.findAll();
        assertEquals(1,trackList.size());

    }

    @Test
    @DisplayName("Track Search By Artist Name Test Case")
    void findByTrackArtistName() {
        trackRepository.save(track);
        artist = new Artist(2,"Jubin");
        track = new Track(3,"Emo",5,artist);
        trackRepository.save(track);
        List<Track> trackList = trackRepository.findByTrackArtistName("Jubin");
        assertEquals(1,trackList.size());
    }


    @Test
    void findByRatingGreaterThanEqual() {
        trackRepository.save(track);
        artist = new Artist(2,"Shiv");
        track = new Track(3,"Emo",7,artist);
        trackRepository.save(track);
        artist = new Artist(4,"Ravi");
        track = new Track(4,"Emo",6,artist);
        trackRepository.save(track);
        List<Track> trackList = trackRepository.findByRatingGreaterThanEqual(6);
        assertEquals(2,trackList.size());
    }
}
























