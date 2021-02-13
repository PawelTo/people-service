package pl.pawel.cqrs.service;

import java.util.Optional;
import pl.pawel.cqrs.persistence.entity.ItemEntity;

import java.util.List;
import pl.pawel.cqrs.service.NamesServiceImpl.CachedName;

public interface NamesService {

    CachedName createName(int id);

    List<CachedName> getAll();

    Optional<CachedName> getById(int id);

    CachedName updateName(int id, String name);
    
    int changeName(String newName, String oldName);

    int create(String description, int id, String name);

    ItemEntity createJpa(String description, int id, String name);

    String requestAttributes();
}
