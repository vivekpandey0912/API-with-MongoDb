package com.niit.bej.master_challenge.service;

import com.niit.bej.master_challenge.domain.Track;
import com.niit.bej.master_challenge.exception.TrackAlreadyExist;
import com.niit.bej.master_challenge.exception.TrackNotFound;

import java.util.List;

public interface TrackService {

    Track addTrack(Track track) throws TrackAlreadyExist;

    List<Track> getAllTrack() throws TrackNotFound;

    boolean deleteTrackById(Integer id) throws TrackNotFound;

    List<Track> trackSearchByArtistName(String artistName) throws TrackNotFound;


}
