package com.niit.bej.master_challenge.repository;

import com.niit.bej.master_challenge.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {

    @Query("{'artist.artistName':?0}")
    List<Track> findByTrackArtistName(String artistName);
}
