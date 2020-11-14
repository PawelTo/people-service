package pl.pawel.cqrs.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pawel.cqrs.controllers.form.PersonForm;
import pl.pawel.cqrs.controllers.view.PersonView;
import pl.pawel.cqrs.service.PeopleService;

@RequestMapping(V1PeopleController.API_PATH)
@RequiredArgsConstructor
@RestController
@Tag(name = V1PeopleController.API_PATH)
public class V1PeopleController {

    public static final String API_PATH = "/api/v1/people";

    private final PeopleService peopleService;

    @PostMapping
    public PersonView add(PersonForm personForm) {
        return peopleService.createPerson(personForm);
    }

    @GetMapping
    public PersonView fetchAll() {
        return peopleService.getAllPeople();
    }
}
