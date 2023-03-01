package com.niit.bej.master_challenge.controller;

import com.niit.bej.master_challenge.domain.Track;
import com.niit.bej.master_challenge.exception.TrackAlreadyExist;
import com.niit.bej.master_challenge.exception.TrackNotFound;
import com.niit.bej.master_challenge.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("/track/add")
    public ResponseEntity<?> addTrack(@RequestBody Track track) throws TrackAlreadyExist {
        try {
            Track track1 = trackService.addTrack(track);
            return new ResponseEntity<>(track1, HttpStatus.ACCEPTED);
        } catch (TrackAlreadyExist trackAlreadyExist) {
            throw new TrackAlreadyExist();
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/track/alltrack")
    public ResponseEntity<?> getAllTracks() throws TrackNotFound {
        try {
            List<Track> trackList = trackService.getAllTrack();
            return new ResponseEntity<>(trackList, HttpStatus.FOUND);
        } catch (TrackNotFound trackNotFound) {
            throw new TrackNotFound();
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }


}
