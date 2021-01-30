package pl.pawel.cqrs.service;

import java.util.List;

public interface NamesService {
    
    List<String> getAll();
    
    void changeName(String newName, String oldName);
}
