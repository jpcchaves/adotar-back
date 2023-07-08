package com.jpcchaves.adotar.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 11)
    private String phone1;
    @Column(length = 11)
    private String phone2;
    @Column(length = 11)
    private String phone3;
}
