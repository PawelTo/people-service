package pl.pawel.cqrs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pawel.cqrs.persistence.entity.PersonEntity;

public interface PersonEntityRepository extends JpaRepository<PersonEntity, PersonEntity.PersonKey> {
}
