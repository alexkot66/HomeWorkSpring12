package com.example.SpringAOP.controller;

import com.example.SpringAOP.model.Person;
import com.example.SpringAOP.service.PersonFacade;
import com.example.SpringAOP.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonFacade personFacade;

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personFacade.createPerson(person);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        return personFacade.getPerson(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Iterable<Person> getAllPersons() {
            return personFacade.getAllPersons();
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        return personFacade.updatePerson(id, personDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personFacade.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
