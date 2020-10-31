package pl.pawel.cqrs.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;
import pl.pawel.cqrs.domain.*;
import pl.pawel.cqrs.domain.commands.ChangeOrganizationCommand;
import pl.pawel.cqrs.domain.commands.ChangeUserNameCommand;
import pl.pawel.cqrs.domain.commands.CreateUserCommand;
import pl.pawel.cqrs.domain.queries.FindUserQuery;

import java.util.List;

import static java.lang.String.valueOf;
import static org.axonframework.messaging.responsetypes.ResponseTypes.multipleInstancesOf;
import static pl.pawel.cqrs.domain.User.Organization.valueOf;

@RequestMapping(V1UserController.API_PATH)
@RequiredArgsConstructor
@RestController
@Tag(name = V1UserController.API_PATH)
public class V1UserController {

    static final String API_PATH = "/api/v1/users";

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @GetMapping()
    public List<User> fetchAll() {
        return queryGateway.query(new FindUserQuery(), multipleInstancesOf(User.class)).join();
    }

    @PostMapping("{id}")
    public void createUser(@PathVariable long id) {
        commandGateway.send(new CreateUserCommand(valueOf(id), "imie"));
    }

    @PutMapping("{id}")
    public void changeUser(@PathVariable long id, @RequestParam(required = false) String organization,
                           @RequestParam(required = false) String name, @RequestParam(required = false) String surname) {
        if (organization != null) {
            commandGateway.send(new ChangeOrganizationCommand(valueOf(id), valueOf(organization)));
        }
        if (name != null || surname != null) {
            commandGateway.send(new ChangeUserNameCommand(valueOf(id), name, surname));
        }
    }
}
