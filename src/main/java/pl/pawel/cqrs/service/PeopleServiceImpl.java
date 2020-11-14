package pl.pawel.cqrs.service;

import org.springframework.stereotype.Service;
import pl.pawel.cqrs.controllers.form.PersonForm;
import pl.pawel.cqrs.controllers.view.PersonView;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Override
    public PersonView createPerson(PersonForm personForm) {
        return null;
    }

    @Override
    public PersonView getAllPeople() {
        return null;
    }
}
