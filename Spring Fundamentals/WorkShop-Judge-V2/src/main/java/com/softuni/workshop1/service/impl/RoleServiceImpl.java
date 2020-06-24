package com.softuni.workshop1.service.impl;

import com.softuni.workshop1.model.entity.Role;
import com.softuni.workshop1.model.service.RoleServiceModel;
import com.softuni.workshop1.repository.RoleRepository;
import com.softuni.workshop1.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        if(this.roleRepository.count()==0){
            Role admin = new Role("ADMIN");
            Role user = new Role("USER");

            this.roleRepository.save(admin);
            this.roleRepository.save(user);
        }
    }

    @Override
    public RoleServiceModel findByName(String name) {
        return this.roleRepository
                .findByName(name)
                .map(role ->
                        this.modelMapper.map(role, RoleServiceModel.class))
                .orElse(null);

    }
}
