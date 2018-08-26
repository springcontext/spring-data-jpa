package com.springcontext.springdatajpa.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Integer id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String name;

    @OneToMany
    private List<Person> employees;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Person> employees) {
        this.employees = employees;
    }
}
