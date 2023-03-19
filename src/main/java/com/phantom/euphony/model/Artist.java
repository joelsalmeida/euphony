package com.phantom.euphony.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Artist {
    private UUID id;
    private String name;
    private List<Album> albums;
}
