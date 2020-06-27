package com.softuni.workshop1.service.impl;

import com.softuni.workshop1.model.entity.Exercise;
import com.softuni.workshop1.model.service.ExerciseServiceModel;
import com.softuni.workshop1.repository.ExerciseRepository;
import com.softuni.workshop1.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addEx(ExerciseServiceModel exerciseServiceModel) {
        this.exerciseRepository.saveAndFlush(this.modelMapper.map(exerciseServiceModel, Exercise.class));
    }

    @Override
    public List<String> findAllExNames() {

        return this.exerciseRepository.findAll()
                .stream()
                .map(Exercise::getName).collect(Collectors.toList());
    }

    @Override
    public ExerciseServiceModel findByName(String exercise) {
        return this.exerciseRepository
                .findByName(exercise)
                .map(exercise1 -> this.modelMapper.map(exercise1, ExerciseServiceModel.class))
                .orElse(null);
    }
}
