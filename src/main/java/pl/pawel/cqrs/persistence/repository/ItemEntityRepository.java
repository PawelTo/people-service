package pl.pawel.cqrs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pawel.cqrs.persistence.entity.ItemEntity;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, Integer> {
}
