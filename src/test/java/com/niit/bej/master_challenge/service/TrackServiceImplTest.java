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

import java.util.ArrayList;
import java.util.List;
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

    List<Track> trackList;


    @BeforeEach
    void setUp() {

        artist = new Artist(1,"Arijit");
        track = new Track(2,"Rom",4,artist);
        trackList = new ArrayList<>();
        trackList.add(track);
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
        when(trackRepository.findAll()).thenReturn(trackList);
        assertEquals(1,trackServiceImpl.getAllTrack().size());
        verify(trackRepository,times(1)).findAll();
    }

    @Test
    void getAllTrackFailure() throws TrackNotFound
    {
        assertThrows(TrackNotFound.class,()->trackServiceImpl.getAllTrack());
        verify(trackRepository,times(1)).findAll();
    }


    @Test
    void deleteTrackById() throws TrackNotFound {
        when(trackRepository.findById(any())).thenReturn(Optional.ofNullable(track));
    assertTrue(trackServiceImpl.deleteTrackById(10));
    verify(trackRepository,times(1)).deleteById(10);

    }
    @Test
    void deleteTrackByIdFailure() throws TrackNotFound {
        when(trackRepository.findById(any())).then(result -> {throw new TrackNotFound();});
        assertThrows(TrackNotFound.class,() ->trackServiceImpl.deleteTrackById(30));
        verify(trackRepository,times(0)).deleteById(10);
    }

    @Test
    void trackSearchByArtistNameSuccess() throws TrackNotFound
        {

            List<Track> fetchedTracks=trackList.stream().filter(x->x.getArtist().getArtistName().equals("Arijit")).toList();
            when(trackRepository.findByTrackArtistName("Arijit")).thenReturn(fetchedTracks);
            assertEquals(trackServiceImpl.trackSearchByArtistName("Arijit").size(),1);

    }
    @Test
    void trackRatingGreaterThanFour() throws TrackNotFound {

        List<Track> filterList = trackList.stream().filter(data -> data.getRating()>2).toList();
        when(trackRepository.findByRatingGreaterThanEqual(2)).thenReturn(filterList);
        assertEquals(1,trackServiceImpl.trackRatingGreaterThanFour(2).size());


    }

    @Test
    void trackSearchByArtistNameFailure() throws TrackNotFound
    {

        List<Track> fetchedTracks=trackList.stream().filter(x->x.getArtist().getArtistName().equals("Shiva")).toList();
        assertThrows(TrackNotFound.class,()-> trackServiceImpl.trackSearchByArtistName("Shiva"));

    }

}