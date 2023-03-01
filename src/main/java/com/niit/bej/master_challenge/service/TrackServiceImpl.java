package com.niit.bej.master_challenge.service;

import com.niit.bej.master_challenge.domain.Track;
import com.niit.bej.master_challenge.exception.TrackAlreadyExist;
import com.niit.bej.master_challenge.exception.TrackNotFound;
import com.niit.bej.master_challenge.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }


    @Override
    public Track addTrack(Track track) throws TrackAlreadyExist {

        if (trackRepository.findById(track.getId()).isPresent()) {
            throw new TrackAlreadyExist();
        } else {
            return trackRepository.save(track);
        }
    }

    @Override
    public List<Track> getAllTrack() throws TrackNotFound {
        List<Track> listTrack = trackRepository.findAll();
        if (listTrack.isEmpty()) {
            throw new TrackNotFound();
        } else {
            return listTrack;
        }

    }

    @Override
    public boolean deleteTrackById(Integer id) throws TrackNotFound {

        Optional<Track> deleteTrackById = trackRepository.findById(id);

        if (deleteTrackById.isPresent()) {
            trackRepository.deleteById(id);
            return true;
        } else {
            throw new TrackNotFound();
        }


    }

    @Override
    public List<Track> trackSearchByArtistName(String artistName) throws TrackNotFound {
        List<Track> searchedItem = trackRepository.findByTrackArtistName(artistName);
        if (searchedItem.isEmpty()) {
            throw new TrackNotFound();
        } else {
            return searchedItem;
        }
    }
}
