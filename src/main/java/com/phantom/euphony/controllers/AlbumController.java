package com.phantom.euphony.controllers;

import com.phantom.euphony.model.Album;
import com.phantom.euphony.services.AlbumService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/albums")
@AllArgsConstructor
public class AlbumController {
    private final AlbumService service;

    @PostMapping
    public ResponseEntity post(@RequestBody Album album){
        Album newAlbum = service.create(album);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/albums/" + newAlbum.getId().toString());

        return new ResponseEntity(newAlbum, headers, HttpStatus.CREATED );
    }

    @PutMapping("{id}")
    public ResponseEntity updateById(@PathVariable("id") UUID id, @RequestBody Album newAlbum) {
        service.updateById(id, newAlbum);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/albums");

        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Album>> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/album");

        List<Album> body = service.getAll();

        return new ResponseEntity<>(body, headers, HttpStatus.OK) ;
    }
}
