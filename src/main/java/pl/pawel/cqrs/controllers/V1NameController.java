package pl.pawel.cqrs.controllers;

import static org.springframework.http.ResponseEntity.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pawel.cqrs.persistence.entity.ItemEntity;
import pl.pawel.cqrs.service.NamesService;
import pl.pawel.cqrs.service.NamesServiceImpl.CachedName;
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

    @Operation(summary = "get names to test caches")
    @GetMapping
    public List<CachedName> getAll(){
        return namesService.getAll();
    }

    @Operation(summary = "get names by id to test caches")
    @GetMapping("/{id}")
    public ResponseEntity<CachedName> getById(@PathVariable int id){
        return namesService.getById(id)
            .map(ResponseEntity::ok)
            .orElse(notFound().build());
    }

    @Operation(summary = "create to test caches")
    @PostMapping()
    public CachedName createName(@RequestBody int id){
        return namesService.createName(id);
    }

    @Operation(summary = "update to test caches")
    @PutMapping(path = "/{id}")
    public CachedName updateName(@PathVariable int id, @RequestBody String name){
        return namesService.updateName(id, name);
    }

    @PutMapping("/trans/{oldName}")
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

    @PostMapping("/transactionalJpa")
    public ItemEntity createTransactionalJpa(@RequestParam String description, @RequestParam int id, @RequestParam String name){
        return transactionalMethodCallerService.createJpa(description, id, name);
    }

    @GetMapping("/testRequestAttribiutes")
    public String testRequestAttribiutes(){
        return namesService.requestAttributes();
    }
}
