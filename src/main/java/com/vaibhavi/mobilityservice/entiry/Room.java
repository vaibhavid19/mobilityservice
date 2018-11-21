package com.vaibhavi.mobilityservice.entiry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     private String description;
     private int[] exits;

     public Room() {}

     public Room(int id, String description, int[] exits) {
        this.id = id;
        this.description = description;
        this.exits = exits;
     }

     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getExits() {
        return exits;
    }

    public void setExits(int[] exits) {
        this.exits = exits;
    }
}
