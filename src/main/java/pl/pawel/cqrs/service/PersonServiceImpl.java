package pl.pawel.cqrs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pawel.cqrs.controllers.form.PersonForm;
import pl.pawel.cqrs.controllers.view.PersonView;
import pl.pawel.cqrs.persistence.repository.PersonRepository;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Override
    public PersonView createPerson(PersonForm personForm) {
        return null;
    }

    @Override
    public PersonView getAllPeople() {
        return null;
    }
}
