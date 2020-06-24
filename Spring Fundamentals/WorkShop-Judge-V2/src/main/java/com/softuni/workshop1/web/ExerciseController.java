package com.softuni.workshop1.web;

import com.softuni.workshop1.model.binding.ExerciseBindingAddModel;
import com.softuni.workshop1.model.service.ExerciseServiceModel;
import com.softuni.workshop1.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;

    public ExerciseController(ExerciseService exerciseService, ModelMapper modelMapper) {
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(@Valid @ModelAttribute("exerciseBindingAddModel")
                                  ExerciseBindingAddModel exerciseBindingAddModel,
                      BindingResult bindingResult){
        return "exercise-add";
    }

    @PostMapping("/add")
    public String addConfirm(
            @Valid @ModelAttribute("exerciseBindingAddModel")
                    ExerciseBindingAddModel exerciseBindingAddModel,
            BindingResult bindingResult, RedirectAttributes redirectAttributes){

    if(bindingResult.hasErrors()){
        redirectAttributes.addFlashAttribute("exerciseBindingAddModel", exerciseBindingAddModel);
        return "redirect:/exercises/add";
    }else{
        //todo local date time vs mapped date (isBefore)
        this.exerciseService
                .addEx(this.modelMapper.map(exerciseBindingAddModel, ExerciseServiceModel.class));
        return "redirect:/";
    }
    }
}
