package com.softuni.workshop1.web;

import com.softuni.workshop1.model.binding.CommentAddBindingModel;
import com.softuni.workshop1.model.binding.HomeworkAddBindingModel;
import com.softuni.workshop1.model.service.ExerciseServiceModel;
import com.softuni.workshop1.model.service.HomeworkServiceModel;
import com.softuni.workshop1.model.service.UserServiceModel;
import com.softuni.workshop1.service.ExerciseService;
import com.softuni.workshop1.service.HomeworkService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/homework")
public class HomeworkController {
    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;
    private final HomeworkService homeworkService;

    public HomeworkController(ExerciseService exerciseService, ModelMapper modelMapper, HomeworkService homeworkService) {
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
        this.homeworkService = homeworkService;
    }

    @GetMapping("/add")
    public String add(Model model){
        if(!model.containsAttribute("homeworkAddBindingModel")){
            model.addAttribute("homeworkAddBindingModel", new HomeworkAddBindingModel());
            model.addAttribute("isLate", false);
        }

        model.addAttribute("allExNames", this.exerciseService.findAllExNames());

        return "homework-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("homeworkAddBindingModel")HomeworkAddBindingModel homeworkAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession){
        ExerciseServiceModel exercise = this.exerciseService.findByName(homeworkAddBindingModel.getExercise());
        boolean isLate = exercise.getDueDate().isBefore(LocalDateTime.now());

        if(bindingResult.hasErrors() || isLate){
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeworkAddBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("isLate", isLate);

            return "redirect:add";
        }
        HomeworkServiceModel homeworkServiceModel = this.modelMapper.map(homeworkAddBindingModel, HomeworkServiceModel.class);
        homeworkServiceModel.setAddedOn(LocalDateTime.now());
        homeworkServiceModel.setExercise(exercise);
        homeworkServiceModel.setAuthor((UserServiceModel) httpSession.getAttribute("user"));

        this.homeworkService.add(homeworkServiceModel);

        return "redirect:/";
    }

    @GetMapping("/check")
    public String check(Model model){

        if(!model.containsAttribute("commentAddBindingModel")){
            model.addAttribute("commentAddBindingModel", new CommentAddBindingModel());
        }

        model.addAttribute("homework", this.homeworkService.findOneForCheck());

        return "homework-check";
    }

    @PostMapping("/check")
    public String checkConfirm(@Valid @ModelAttribute("commentAddBindingModel")CommentAddBindingModel commentAddBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("commentAddBindingModel", commentAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentAddBindingModel", bindingResult);

            return "redirect:check";
        }
        //todo save in db
        return "redirect:/";
    }
}
