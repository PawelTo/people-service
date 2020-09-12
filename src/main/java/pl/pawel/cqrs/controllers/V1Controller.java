package pl.pawel.cqrs.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(V1Controller.API_PATH)
@RequiredArgsConstructor
@RestController
@Tag(name = V1Controller.API_PATH)
public class V1Controller {

    static final String API_PATH = "/api/v1/users";
}
