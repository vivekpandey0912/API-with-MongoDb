package com.niit.bej.master_challenge.service;

import com.niit.bej.master_challenge.domain.Artist;
import com.niit.bej.master_challenge.domain.Track;
import com.niit.bej.master_challenge.exception.TrackAlreadyExist;
import com.niit.bej.master_challenge.exception.TrackNotFound;
import com.niit.bej.master_challenge.repository.TrackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Switch.CaseOperator.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrackServiceImplTest {


    @Mock
    TrackRepository trackRepository;

    @InjectMocks
    TrackServiceImpl trackServiceImpl;


    Track track;
    Artist artist;


    @BeforeEach
    void setUp() {

        artist = new Artist(1,"Arijit");
        track = new Track(2,"Rom",4,artist);
    }

    @AfterEach
    void tearDown() {
        track = null;
        artist = null;
    }

    @Test
    void addTrackSuccess() throws TrackAlreadyExist {

        when(trackRepository.findById(any())).thenReturn(Optional.ofNullable(null));
        when(trackRepository.save(any())).thenReturn(track);
        assertEquals(track,trackServiceImpl.addTrack(track));
        verify(trackRepository,times(1)).findById(any());
        verify(trackRepository,times(1)).save(any());


    }
    @Test
    void addTrackFailure() throws TrackAlreadyExist {

        when(trackRepository.findById(any())).thenReturn(Optional.ofNullable(track));
        assertThrows(TrackAlreadyExist.class,() -> trackServiceImpl.addTrack(track));
        verify(trackRepository,times(1)).findById(any());
        verify(trackRepository,times(0)).save(any());


    }

    @Test
    void getAllTrackSuccess() throws TrackNotFound
    {

    }

    @Test
    void deleteTrackById() {
    }

    @Test
    void trackSearchByArtistName() {
    }

    @Test
    void trackRatingGreaterThanFour() {
    }
}