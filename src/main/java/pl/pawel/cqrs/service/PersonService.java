package pl.pawel.cqrs.service;

import pl.pawel.cqrs.controllers.form.PersonForm;
import pl.pawel.cqrs.controllers.view.PersonView;
import pl.pawel.cqrs.persistence.aggregatestatistic.PersonAggregateStatistics;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    PersonView createPerson(PersonForm personForm);

    List<PersonView> getAllPeople();

    List<PersonAggregateStatistics> getAverageSalary();

    Optional<PersonAggregateStatistics> getAverageSalaryFor(String name);
}
    