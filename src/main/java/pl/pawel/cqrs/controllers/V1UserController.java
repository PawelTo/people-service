package pl.pawel.cqrs.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;
import pl.pawel.cqrs.domain.CreateUserCommand;
import pl.pawel.cqrs.domain.FindAllUsersQuery;
import pl.pawel.cqrs.domain.User;

import java.util.List;

import static java.lang.String.valueOf;
import static org.axonframework.messaging.responsetypes.ResponseTypes.multipleInstancesOf;

@RequestMapping(V1UserController.API_PATH)
@RequiredArgsConstructor
@RestController
@Tag(name = V1UserController.API_PATH)
public class V1UserController {

    static final String API_PATH = "/api/v1/users";

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @GetMapping()
    public List<User> fetchAll(){
        return queryGateway.query(new FindAllUsersQuery(), multipleInstancesOf(User.class)).join();
    }

    @PostMapping("{id}")
    public void createUser(@PathVariable long id){
        commandGateway.send(new CreateUserCommand(valueOf(id), "imie"));
    }
}
