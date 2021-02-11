package pl.pawel.cqrs.service;

import pl.pawel.cqrs.persistence.entity.ItemEntity;

import java.util.List;
import pl.pawel.cqrs.service.NamesServiceImpl.CachedName;

public interface NamesService {
    
    List<CachedName> getAll();
    
    int changeName(String newName, String oldName);

    int create(String description, int id, String name);

    ItemEntity createJpa(String description, int id, String name);

    String requestAttributes();
}
