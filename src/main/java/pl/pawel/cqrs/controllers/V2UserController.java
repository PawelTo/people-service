package pl.pawel.cqrs.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pawel.cqrs.domain.User;
import pl.pawel.cqrs.domain.UserCommandService;
import pl.pawel.cqrs.domain.UserQueryService;

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
    public CompletableFuture<Object> create(User user){
        return userCommandService.create(user);
    }
}
