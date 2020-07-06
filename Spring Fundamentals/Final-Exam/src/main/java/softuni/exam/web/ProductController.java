package softuni.exam.web;

import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.exam.model.binding.ProductAddBindingModel;
import softuni.exam.model.service.ProductServiceModel;
import softuni.exam.service.ProductService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView add(HttpSession httpSession, ModelAndView modelAndView, Model model){
        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("index");
        } else {
            if(!model.containsAttribute("productAddBindingModel")){
                model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
            }
            modelAndView.setViewName("product-add");
        }

        return modelAndView;
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("productAddBindingModel") ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);
            return "redirect:add";
        }

        this.productService.addProduct(this.modelMapper
                .map(productAddBindingModel, ProductServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String delete(@PathVariable("id")String id){
       this.productService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String deleteAllProducts(){
        this.productService.deleteAll();
        return "redirect:/";
    }
}
