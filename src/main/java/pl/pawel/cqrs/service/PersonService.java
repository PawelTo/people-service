package pl.pawel.cqrs.service;

import pl.pawel.cqrs.controllers.form.PersonForm;
import pl.pawel.cqrs.controllers.view.PersonView;

import java.util.List;

public interface PersonService {

    PersonView createPerson(PersonForm personForm);

    List<PersonView> getAllPeople();
}
