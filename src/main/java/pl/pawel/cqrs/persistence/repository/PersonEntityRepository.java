package pl.pawel.cqrs.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.pawel.cqrs.persistence.entity.PersonEntity;
import pl.pawel.cqrs.persistence.aggregatestatistic.PersonAggregateStatistics;

import java.util.List;
import java.util.Optional;

public interface PersonEntityRepository extends JpaRepository<PersonEntity, PersonEntity.PersonKey> {

    @Query(value ="SELECT new pl.pawel.cqrs.persistence.aggregatestatistic.PersonAggregateStatistics(" +
                 "p.name, "+
                 "AVG(p.salary) as avgSalary"+
                 ") "+
                 "FROM person p " +
                 "GROUP BY p.name"
    )
    List<PersonAggregateStatistics> countAverageSalary();

    @Query(value ="SELECT new pl.pawel.cqrs.persistence.aggregatestatistic.PersonAggregateStatistics(" +
            "p.name, "+
            "AVG(p.salary) as avgSalary"+
            ") "+
            "FROM person p " +
            "WHERE p.name = :name " +
            "GROUP BY p.name"
    )
    Optional<PersonAggregateStatistics> countAverageSalaryFor(String name);
}
