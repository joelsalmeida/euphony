package com.phantom.euphony.controllers;

import com.phantom.euphony.model.Song;
import com.phantom.euphony.services.SongService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/songs")
@AllArgsConstructor
public class SongController {
    private final SongService service;

    @PostMapping
    public ResponseEntity post(@RequestBody Song song){
        Song newSong = service.create(song);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/songs/" + newSong.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity updateById(@PathVariable("id") UUID id, @RequestBody Song newSong) {
        service.updateById(id, newSong);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/songs");

        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Song>> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/songs");

        List<Song> body = service.getAll();

        return new ResponseEntity<>(body, headers, HttpStatus.OK) ;
    }
}
