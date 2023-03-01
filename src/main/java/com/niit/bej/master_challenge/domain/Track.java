package com.niit.bej.master_challenge.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Track {

    @Id
    private int id;
    private String name;
    private int rating;
    private Artist artist;

}
