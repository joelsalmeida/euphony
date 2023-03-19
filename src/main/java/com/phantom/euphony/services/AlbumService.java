package com.phantom.euphony.services;

import com.phantom.euphony.model.Album;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class AlbumService implements GenericService<Album> {
    private final HashMap<UUID, Album> albums = new HashMap<>();

    @Override
    public Album create(Album model) {
        Album newAlbum = Album.builder()
                .id(UUID.randomUUID())
                .name(model.getName())
                .artist(model.getArtist())
                .songs(model.getSongs())
                .build();

        albums.put(newAlbum.getId(), newAlbum);
        return newAlbum;
    }

    @Override
    public List<Album> getAll() {
        return new ArrayList<>(albums.values());
    }

    @Override
    public void deleteById(UUID id) {
        albums.remove(id);
    }

    @Override
    public void updateById(UUID id, Album newModel) {
        Album albumToBeUpdated = albums.get(id);

        albumToBeUpdated.setName(newModel.getName());
        albumToBeUpdated.setArtist(newModel.getArtist());
        albumToBeUpdated.setSongs(newModel.getSongs());
    }
}
