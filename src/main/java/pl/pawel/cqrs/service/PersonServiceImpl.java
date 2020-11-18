package pl.pawel.cqrs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pawel.cqrs.controllers.form.PersonForm;
import pl.pawel.cqrs.controllers.view.PersonView;
import pl.pawel.cqrs.persistence.repository.PersonRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static pl.pawel.cqrs.controllers.view.PersonView.from;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Override
    public PersonView createPerson(PersonForm personForm) {
        return from(personRepository.save(
                                          personForm.toPersonEntity()
        ));
    }

    @Override
    public List<PersonView> getAllPeople() {
        return personRepository.findAll().stream()
                .map(personEntity -> from(personEntity))
                .collect(toList());
    }
}
