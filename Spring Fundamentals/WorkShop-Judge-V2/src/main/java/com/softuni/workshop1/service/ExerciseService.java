package com.softuni.workshop1.service;

import com.softuni.workshop1.model.service.ExerciseServiceModel;

import java.util.List;

public interface ExerciseService {
    void addEx(ExerciseServiceModel exerciseServiceModel);

    List<String> findAllExNames();

    ExerciseServiceModel findByName(String exercise);
}
