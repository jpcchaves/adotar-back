package com.jpcchaves.adotar.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "states")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60, nullable = false)
    private String name;
    @Column(length = 2, nullable = false)
    private String uf;

    public State() {
    }

    public State(Long id,
                 String name,
                 String uf) {
        this.id = id;
        this.name = name;
        this.uf = uf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
