package com.niit.bej.master_challenge.repository;

import com.niit.bej.master_challenge.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {


}
