package ru.job4j.admin.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.admin.service.DishService;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class DishController {

    private DishService simpleDishService;

    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("dishes", simpleDishService.findAll());
        return "menu";
    }
}
