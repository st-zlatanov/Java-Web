package com.softuni.workshop1.service;


import com.softuni.workshop1.model.service.RoleServiceModel;

public interface RoleService {
    RoleServiceModel findByName(String name);
}
