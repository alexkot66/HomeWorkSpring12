package com.example.SpringAOP.service;

import com.example.SpringAOP.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonFacade {
    private final PersonService personService;

    @Autowired
    public PersonFacade(PersonService personService) {
        this.personService = personService;
    }

    public Person createPerson(String name, int age) {
        return personService.createPerson(name, age);
    }

    public Person createPerson(Person person) {
        return personService.createPerson(person);
    }

    public Optional<Person> getPerson(Long id) {
        return personService.getPerson(id);
    }

    public Iterable<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    public Person updatePerson(Long id, Person personDetails) {
        return personService.updatePerson(id, personDetails);
    }

    public void deletePerson(Long id) {
        personService.deletePerson(id);
    }
}
