package pl.pawel.cqrs.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.pawel.cqrs.controllers.form.PersonForm;
import pl.pawel.cqrs.controllers.view.PersonView;
import pl.pawel.cqrs.service.PersonService;

import java.util.List;

@RequestMapping(V1PeopleController.API_PATH)
@RequiredArgsConstructor
@RestController
@Tag(name = V1PeopleController.API_PATH)
public class V1PeopleController {

    public static final String API_PATH = "/api/v1/people";

    private final PersonService personService;

    @PostMapping
    public PersonView add(@RequestBody PersonForm personForm) {
        return personService.createPerson(personForm);
    }

    @GetMapping
    public List<PersonView> fetchAll() {
        return personService.getAllPeople();
    }
}
