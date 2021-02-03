package pl.pawel.cqrs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pawel.cqrs.persistence.entity.ItemEntity;

@RequiredArgsConstructor
@Service
public class TransactionalMethodCallerService {

    private final NamesService namesService;

    public int changeNameTransactional(String newName, String oldName){
        return namesService.changeName(newName, oldName);
    }

    public int create(String description, int id, String name){
        return namesService.create(description, id, name);
    }

    public ItemEntity createJpa(String description, int id, String name) {
        return namesService.createJpa(description, id, name);
    }
}
