package com.example.SpringAOP.service;

import com.example.SpringAOP.model.ExternalPerson;
import com.example.SpringAOP.model.Person;
import com.example.SpringAOP.repository.ExternalPersonRepo;

import java.util.Optional;

public class ExternalPersonAdapter extends PersonService{

    private final ExternalPersonRepo externalRepo;

    public ExternalPersonAdapter(ExternalPersonRepo externalRepo) {
        this.externalRepo = externalRepo;
    }

    @Override
    public Optional<Person> getPerson(Long id) {
        ExternalPerson externalPerson = externalRepo.getExternalPerson(id);

        if (externalPerson == null) {
            return Optional.empty(); // Возвращаем пустой Optional, если внешний объект не найден
        }

        Person person = new Person.Builder()
                .setName(externalPerson.getFullName())
                .setAge(externalPerson.getYearsOld())
                .build();
        return Optional.of(person); // Оборачиваем person в Optional
    }

}
