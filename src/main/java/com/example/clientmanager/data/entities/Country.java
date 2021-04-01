package com.example.clientmanager.data.entities;

import javax.persistence.*;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String Name;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return Name; }
    public void setName(String name) { Name = name; }
}
