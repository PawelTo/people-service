package pl.pawel.cqrs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pawel.cqrs.persistence.entity.ItemEntity;
import pl.pawel.cqrs.persistence.repository.ItemEntityRepository;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {
    
    private final ItemEntityRepository itemEntityRepository;
    
    public ItemEntity save(ItemEntity itemEntity){
        return itemEntityRepository.save(itemEntity);
    }
}
