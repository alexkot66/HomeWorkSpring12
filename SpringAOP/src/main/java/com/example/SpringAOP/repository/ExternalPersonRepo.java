package com.example.SpringAOP.repository;

import com.example.SpringAOP.model.ExternalPerson;
import com.example.SpringAOP.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalPersonRepo extends JpaRepository<Person, Long> {
    ExternalPerson getExternalPerson(Long id);
}
