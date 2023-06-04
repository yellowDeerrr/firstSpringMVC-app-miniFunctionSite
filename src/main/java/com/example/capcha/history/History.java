package com.example.capcha.history;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    private String action;
    @Column
    private Timestamp time;

    public History() {
    }

    public History(String action, Timestamp time) {
        this.action = action;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
