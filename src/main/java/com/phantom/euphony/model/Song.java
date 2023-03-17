package com.phantom.euphony.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Song {
    private UUID id;
    private String name;
    private Artist artist;
    private Album album;
    private Float duration;
}
