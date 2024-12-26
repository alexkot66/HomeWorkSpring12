package com.example.SpringAOP;

import com.example.SpringAOP.model.Person;
import com.example.SpringAOP.repository.PersonRepository;
import com.example.SpringAOP.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @SpringBootTest: Загружает контекст приложения для интеграционного тестирования.
 * @Transactional: Обеспечивает, что каждый тест будет выполняться в транзакции,
 * которая будет откатана после завершения теста.
 */
@SpringBootTest
@ActiveProfiles("test") // профиль для тестирования
@Transactional
public class PersonServiceSpringIntegrationTests {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;

    private Person testPerson;

    @BeforeEach
    public void setUp() {
        testPerson = new Person();
        testPerson.setName("John");
        testPerson.setAge(25);
    }

    /**
     * testCreatePerson(): Проверяет создание нового объекта Person.
     * testGetPerson(): Проверяет получение объекта Person по ID.
     * testGetAllPersons(): Проверяет получение всех объектов Person.
     * testUpdatePerson(): Проверяет обновление существующего объекта Person.
     * testDeletePerson(): Проверяет удаление объекта Person.
     */

    @Test
    public void testCreatePerson() {
        Person createdPerson = personService.createPerson(testPerson);
        assertThat(createdPerson).isNotNull();
        assertThat(createdPerson.getId()).isNotNull();
        assertThat(createdPerson.getName()).isEqualTo(testPerson.getName());
        assertThat(createdPerson.getAge()).isEqualTo(testPerson.getAge());
    }

    @Test
    public void testGetPerson() {
        Person createdPerson = personService.createPerson(testPerson);
        Optional<Person> foundPerson = personService.getPerson(createdPerson.getId());
        assertThat(foundPerson).isPresent();
        assertThat(foundPerson.get().getName()).isEqualTo(testPerson.getName());
    }

    @Test
    public void testGetAllPersons() {
        personService.createPerson(testPerson);
        Iterable<Person> persons = personService.getAllPersons();
        assertThat(persons).hasSize(3); //3 потому что в data.sql сделан insert двух людей
    }

    @Test
    public void testUpdatePerson() {
        Person createdPerson = personService.createPerson(testPerson);
        createdPerson.setName("Jane D");
        createdPerson.setAge(27);
        Person updatedPerson = personService.updatePerson(createdPerson.getId(), createdPerson);
        assertThat(updatedPerson.getName()).isEqualTo("Jane D");
        assertThat(updatedPerson.getAge()).isEqualTo(27);
    }

    @Test
    public void testDeletePerson() {
        Person createdPerson = personService.createPerson(testPerson);
        personService.deletePerson(createdPerson.getId());
        Optional<Person> foundPerson = personService.getPerson(createdPerson.getId());
        assertThat(foundPerson).isNotPresent();
    }

}
