package pl.pawel.cqrs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.pawel.cqrs.persistence.entity.PersonEntity;
import pl.pawel.cqrs.persistence.aggregatestatistic.PersonAggregateStatistics;

import java.util.Optional;

public interface PersonEntityRepository extends JpaRepository<PersonEntity, PersonEntity.PersonKey> {

    @Query(value ="SELECT new pl.pawel.cqrs.persistence.aggregatestatistic.PersonAggregateStatistics(" +
                 "p.name, "+
                 "p.salary as avgSalary"+
                 ")"+
                 " FROM person p"
    )
    Optional<PersonAggregateStatistics> countAverageSalary();
}
