package com.softuni.workshop1.service.impl;

import com.softuni.workshop1.model.entity.Homework;
import com.softuni.workshop1.model.service.HomeworkServiceModel;
import com.softuni.workshop1.repository.HomeworkRepository;
import com.softuni.workshop1.service.HomeworkService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final ModelMapper modelMapper;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, ModelMapper modelMapper) {
        this.homeworkRepository = homeworkRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(HomeworkServiceModel homeworkServiceModel) {
        this.homeworkRepository.saveAndFlush(this.modelMapper.map(homeworkServiceModel, Homework.class));
    }

    @Override
    public HomeworkServiceModel findOneForCheck() {
        return this.homeworkRepository
                .findAll().stream()
                .min(Comparator.comparingInt(a->a.getComments().size()))
                .map(homework -> this.modelMapper.map(homework, HomeworkServiceModel.class))
                .orElse(null);
    }
}
