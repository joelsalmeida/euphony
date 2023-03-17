package com.phantom.euphony.services;

import java.util.List;
import java.util.UUID;

public interface GenericService<T> {
    T create(T model);
    List<T> getAll();
    void deleteById(UUID id);
    void updateById(UUID id, T newModel);
}
