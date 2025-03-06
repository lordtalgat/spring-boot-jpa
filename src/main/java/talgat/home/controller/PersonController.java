package talgat.home.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talgat.home.dto.PersonDto;
import talgat.home.service.PersonService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAll() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping("{id}")
    public ResponseEntity<PersonDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPerson(id));
    }

    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody PersonDto personDto) {
        PersonDto savedPerson = personService.createPerson(personDto);
        return new ResponseEntity <>(savedPerson, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<PersonDto> update(@PathVariable Long id, @RequestBody PersonDto personDto) {
        return ResponseEntity.ok(personService.updatePerson(id, personDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok("Person deleted");
    }
}
