package pl.pawel.cqrs.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pawel.cqrs.service.NamesService;

import java.util.List;

/**
 * Class for learning caffeine cache
 */
@RequestMapping(V1NameController.API_PATH)
@RequiredArgsConstructor
@RestController
@Tag(name = V1NameController.API_PATH)
public class V1NameController {

    public static final String API_PATH = "/api/v1/names";

    private final NamesService namesService;

    @GetMapping
    public List<String> getAll(){
        return namesService.getAll();
    }
}
