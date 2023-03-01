package com.niit.bej.master_challenge.controller;

import com.niit.bej.master_challenge.service.TrackService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }


}
