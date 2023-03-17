package com.phantom.euphony.services;

import com.phantom.euphony.model.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class SongService implements GenericService<Song> {
    private final HashMap<UUID, Song> songs = new HashMap<>();

    @Override
    public Song create(Song model) {
        Song newSong = Song.builder()
                .id(UUID.randomUUID())
                .name(model.getName())
                .artist(model.getArtist())
                .album(model.getAlbum())
                .duration(model.getDuration())
                .build();

        songs.put(newSong.getId(), newSong);
        return newSong;
    }

    @Override
    public List<Song> getAll() {
        return new ArrayList<>(songs.values());
    }

    @Override
    public void deleteById(UUID id) {
        songs.remove(id);
    }

    @Override
    public void updateById(UUID id, Song newSong) {
        Song songToBeUpdated = songs.get(id);

        songToBeUpdated.setName(newSong.getName());
        songToBeUpdated.setArtist(newSong.getArtist());
        songToBeUpdated.setAlbum(newSong.getAlbum());
        songToBeUpdated.setDuration(newSong.getDuration());
    }
}
