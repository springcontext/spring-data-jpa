package com.springcontext.springdatajpa.repositories;

import com.springcontext.springdatajpa.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAllByFirstnameAndLastname(String firstname, String lastname);

    @Transactional
    void deleteAllByLastname(String lastname);
}
