package pl.pawel.cqrs.service;

import pl.pawel.cqrs.controllers.form.PersonForm;
import pl.pawel.cqrs.controllers.view.PersonView;

public interface PeopleService {

    PersonView createPerson(PersonForm personForm);

    PersonView getAllPeople();
}
