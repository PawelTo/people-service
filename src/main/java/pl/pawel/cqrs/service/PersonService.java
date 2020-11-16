package pl.pawel.cqrs.service;

import pl.pawel.cqrs.controllers.form.PersonForm;
import pl.pawel.cqrs.controllers.view.PersonView;

public interface PersonService {

    PersonView createPerson(PersonForm personForm);

    PersonView getAllPeople();
}
