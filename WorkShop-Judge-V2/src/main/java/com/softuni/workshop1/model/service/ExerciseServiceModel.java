package com.softuni.workshop1.model.service;

import com.softuni.workshop1.model.service.BaseServiceModel;

import java.time.LocalDateTime;

public class ExerciseServiceModel extends BaseServiceModel {

    private String name;
    private LocalDateTime addedOn;
    private LocalDateTime dueDate;

    public ExerciseServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
