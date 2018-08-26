package com.springcontext.springdatajpa.repositories;

import com.springcontext.springdatajpa.entities.Address;
import com.springcontext.springdatajpa.entities.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

    /* Address constants */

    private static final String COUNTRY = "Canada";
    private static final String CITY = "Vancouver";
    private static final String ZIP_CODE = "A0A 0A0";
    private static final String STREET = "Main street";
    private static final Integer STREET_NUMBER = 1234;

    /* Person constants */

    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void save_should_createNewPerson() {

        this.createPersonAndAddress();

        List<Person> persons = this.personRepository.findAll();

        assertThat(persons).isNotNull().hasSize(1);

        Person createdPerson = persons.get(0);

        Address createdAddress = createdPerson.getAddress();

        assertThat(createdAddress).isNotNull();

        assertThat(createdAddress.getCity()).isNotNull().isNotEmpty().isEqualTo(CITY);
        assertThat(createdAddress.getCountry()).isNotNull().isNotEmpty().isEqualTo(COUNTRY);
        assertThat(createdAddress.getId()).isNotNull().isGreaterThan(0);
        assertThat(createdAddress.getStreet()).isNotNull().isNotEmpty().isEqualTo(STREET);
        assertThat(createdAddress.getZipCode()).isNotNull().isNotEmpty().isEqualTo(ZIP_CODE);
        assertThat(createdAddress.getStreetNumber()).isNotNull().isEqualTo(STREET_NUMBER);
        assertThat(createdAddress.getPerson()).isNotNull().hasFieldOrPropertyWithValue("id", createdPerson.getId());

        assertThat(createdPerson).isNotNull();

        assertThat(createdPerson.getId()).isNotNull().isGreaterThan(0);
        assertThat(createdPerson.getFirstname()).isNotNull().isNotEmpty().isEqualTo(FIRST_NAME);
        assertThat(createdPerson.getLastname()).isNotNull().isNotEmpty().isEqualTo(LAST_NAME);
        assertThat(createdPerson.getAddress()).isNotNull().hasFieldOrPropertyWithValue("id", createdAddress.getId());
    }

    @Test
    public void findAllByFirstnameAndLastname_should_return_oneInstanceWhenAPersonExistWithThisFirstnameAndLastname() {

        this.personRepository.deleteAll();

        List<Person> persons = this.personRepository.findAllByFirstnameAndLastname(FIRST_NAME, LAST_NAME);

        assertThat(persons).isNotNull().isEmpty();

        this.createPersonAndAddress();

        persons = this.personRepository.findAllByFirstnameAndLastname(FIRST_NAME, LAST_NAME);

        assertThat(persons).isNotNull().hasSize(1);

        Person createdPerson = persons.get(0);

        assertThat(createdPerson.getFirstname()).isNotNull().isNotEmpty().isEqualTo(FIRST_NAME);
        assertThat(createdPerson.getLastname()).isNotNull().isNotEmpty().isEqualTo(LAST_NAME);
    }

    @Test
    public void deleteAllByLastname_should_remove_everyPersonThatSharesTheSameGivenLastname() {

        this.createPersonAndAddress();

        this.personRepository.deleteAllByLastname(LAST_NAME);

        List<Person> persons = this.personRepository.findAllByFirstnameAndLastname(FIRST_NAME, LAST_NAME);

        assertThat(persons).isNotNull().isEmpty();
    }

    private void createPersonAndAddress() {

        this.personRepository.deleteAll();

        List<Person> persons = this.personRepository.findAll();

        assertThat(persons).isNotNull().isEmpty();

        Address address = new Address();
        Person person = new Person();

        address.setCountry(COUNTRY);
        address.setCity(CITY);
        address.setZipCode(ZIP_CODE);
        address.setStreet(STREET);
        address.setStreetNumber(STREET_NUMBER);

        person.setFirstname(FIRST_NAME);
        person.setLastname(LAST_NAME);

        address.setPerson(person);
        person.setAddress(address);

        this.personRepository.save(person);
    }
}
