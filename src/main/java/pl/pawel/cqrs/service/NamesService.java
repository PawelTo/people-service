package pl.pawel.cqrs.service;

import java.util.List;

public interface NamesService {
    
    List<String> getAll();
    
    int changeName(String newName, String oldName);
}
