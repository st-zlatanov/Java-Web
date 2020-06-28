package softuni.exam.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.model.entity.Product;
import softuni.exam.model.view.ProductViewModel;
import softuni.exam.service.ProductService;


import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession, ModelAndView modelAndView) {
        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("index");
        } else {
            modelAndView.addObject("products", this.productService.findAllProducts());
            modelAndView.setViewName("home");

        }

        return modelAndView;
    }
}
