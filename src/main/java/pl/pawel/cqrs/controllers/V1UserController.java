package pl.pawel.cqrs.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pawel.cqrs.domain.FindAllUsers;
import pl.pawel.cqrs.domain.User;
import pl.pawel.cqrs.domain.UserQueryService;

import java.util.List;

import static org.axonframework.messaging.responsetypes.ResponseTypes.multipleInstancesOf;

@RequestMapping(V1UserController.API_PATH)
@RequiredArgsConstructor
@RestController
@Tag(name = V1UserController.API_PATH)
public class V1UserController {

    static final String API_PATH = "/api/v1/users";

    //private final UserQueryService userQueryService;
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @GetMapping()
    public List<User> fetchAll(){
        return queryGateway.query(new FindAllUsers(), multipleInstancesOf(User.class)).join();
    }
}
