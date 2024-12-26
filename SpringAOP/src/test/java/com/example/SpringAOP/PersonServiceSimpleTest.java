package com.example.SpringAOP;

import com.example.SpringAOP.exception.PersonNotFoundException;
import com.example.SpringAOP.model.Person;
import com.example.SpringAOP.repository.PersonRepository;
import com.example.SpringAOP.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceSimpleTest {
    /**
     * используем аннотации
     * @Mock для создания мока PersonRepository
     * @InjectMocks для инъекции мока в PersonService.
     */
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    private Person person;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        person = new Person(1L, "John", 25);
    }

    /**
     * testCreatePerson: Проверяет, что метод createPerson сохраняет объект и возвращает его.
     * testGetPersonByID: Проверяет, что метод getPerson возвращает объект по ID.
     * testGetAllPersons: Проверяет, что метод getAllPersons возвращает список всех объектов.
     * testUpdatePerson: Проверяет, что метод updatePerson обновляет объект и возвращает его.
     * testDeletePerson: Проверяет, что метод deletePerson корректно удаляет объект.
     * testUpdatePersonNotFound: Проверяет, что при попытке обновления несуществующего объекта выбрасывается исключение.
     */
    @Test
    public void testCreatePerson() {
        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person createdPerson = personService.createPerson(person);

        assertNotNull(createdPerson);
        assertEquals("John", createdPerson.getName());
        assertEquals(25, createdPerson.getAge());
        verify(personRepository).save(person);
    }

    @Test
    public void testGetPersonByID(){
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        Optional<Person> foundPerson = personService.getPerson(1L);
        assertTrue(foundPerson.isPresent());
        assertEquals("John", foundPerson.get().getName());
        verify(personRepository).findById(1L);
    }

    @Test
    public void testGetAllPersons() {
        when(personRepository.findAll()).thenReturn(List.of(person));

        Iterable<Person> persons = personService.getAllPersons();

        assertNotNull(persons);
        assertTrue(persons.iterator().hasNext());
        assertEquals("John", persons.iterator().next().getName());
        verify(personRepository).findAll();
    }

    @Test
    public void testUpdatePerson() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenReturn(person);

        Person updatedPerson = personService.updatePerson(1L, new Person("Jane D", 25));

        assertNotNull(updatedPerson);
        assertEquals("Jane D", updatedPerson.getName());
        assertEquals(25, updatedPerson.getAge());
        verify(personRepository).findById(1L);
        verify(personRepository).save(updatedPerson);
    }

    @Test
    public void testDeletePerson() {
        doNothing().when(personRepository).deleteById(1L);

        personService.deletePerson(1L);

        verify(personRepository).deleteById(1L);
    }

    @Test
    public void testUpdatePersonNotFound() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> {
            personService.updatePerson(1L, new Person("Jane D", 25));
        });
    }
}
