package pl.pawel.cqrs.service;

import pl.pawel.cqrs.persistence.entity.ItemEntity;

public interface ItemService {

    ItemEntity save(ItemEntity itemEntity);
}
