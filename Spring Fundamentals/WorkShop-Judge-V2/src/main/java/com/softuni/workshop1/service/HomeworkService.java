package com.softuni.workshop1.service;

import com.softuni.workshop1.model.service.HomeworkServiceModel;

public interface HomeworkService {
    void add(HomeworkServiceModel homeworkServiceModel);

    HomeworkServiceModel findOneForCheck();
}
