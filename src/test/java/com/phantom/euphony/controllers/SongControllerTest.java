package com.phantom.euphony.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phantom.euphony.model.Song;
import com.phantom.euphony.services.GenericService;
import com.phantom.euphony.services.SongService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SongController.class)
class SongControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    GenericService<Song> genericService;
    @MockBean
    SongService service;

    @BeforeEach
    void setUp() {
        service = new SongService();
    }

    @Test
    void testCreateSong() throws Exception {
        Song niceSong = Song.builder()
                .id(UUID.randomUUID())
                .name("Nice Song")
                .duration(7.7f)
                .build();

        given(genericService.create(any(Song.class))).willReturn(niceSong);

        mockMvc.perform(post("/api/songs")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(niceSong))
                )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void updateById() {
    }

    @Test
    void testGetAllSongs() throws Exception {
        ArrayList<Song> songsMock = new ArrayList<>();

        Song niceSong = Song.builder()
                .id(UUID.randomUUID())
                .name("Nice Song")
                .artist(null)
                .album(null)
                .duration(7.7f)
                .build();

        songsMock.add(niceSong);

        given(genericService.getAll()).willReturn(songsMock);

        mockMvc.perform(get("/api/songs")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(header().exists("Location"));
    }
}