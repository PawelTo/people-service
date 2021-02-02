package pl.pawel.cqrs.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.pawel.cqrs.service.NamesService;
import pl.pawel.cqrs.service.TransactionalMethodCallerService;

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
    private final TransactionalMethodCallerService transactionalMethodCallerService;

    @GetMapping
    public List<String> getAll(){
        return namesService.getAll();
    }

    @PutMapping("/{oldName}")
    public int changeName(@PathVariable String oldName, @RequestBody String newName){
        return namesService.changeName(newName, oldName);
    }

    @PutMapping("/transactional/{oldName}")
    public int changeNameTransactional(@PathVariable String oldName, @RequestBody String newName){
        return transactionalMethodCallerService.changeNameTransactional(oldName, newName);
    }

    @PostMapping("/transactional")
    public int createTransactional(@RequestParam String description, @RequestParam int id, @RequestParam String name){
        return transactionalMethodCallerService.create(description, id, name);
    }
}
