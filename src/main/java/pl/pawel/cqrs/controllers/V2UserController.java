package pl.pawel.cqrs.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.pawel.cqrs.domain.User;
import pl.pawel.cqrs.domain.UserCommandService;
import pl.pawel.cqrs.domain.UserQueryService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequestMapping(V2UserController.API_PATH)
@RequiredArgsConstructor
@RestController
@Tag(name = V2UserController.API_PATH)
public class V2UserController {

    static final String API_PATH = "/api/v2/users";

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @PostMapping
    public CompletableFuture<Object> create(@RequestBody User user){
        return userCommandService.create(user);
    }
    
    @GetMapping
    public List<User> fetchAll(){
        return userQueryService.findAll();
    }

    @GetMapping(path = "/{id}")
    public User fetchById(@PathVariable int id){
        return userQueryService.findById(id);
    }

    @GetMapping(path = "/{id}/events")
    public List<?> fetchEventsFor(@PathVariable int id){
        return userQueryService.findEventsFor(id);
    }
}
