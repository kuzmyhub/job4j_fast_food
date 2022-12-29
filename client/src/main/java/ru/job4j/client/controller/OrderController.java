package ru.job4j.client.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.client.service.DishService;
import ru.job4j.client.service.CustomerService;
import ru.job4j.domain.model.Customer;
import ru.job4j.domain.model.Dish;
import ru.job4j.domain.model.Order;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/client/order")
@AllArgsConstructor
public class OrderController {

    private DishService simpleDishService;
    private CustomerService simpleCustomerService;

    @GetMapping("/orderForm")
    public String orderForm(@ModelAttribute(name = "dishIds") String dishIds,
                            Model model, Principal principal) {
        model.addAttribute("dishIds", dishIds);
        model.addAttribute("username",
                simpleCustomerService.getUsername(principal));
        return "addOrder";
    }

    @PostMapping("/createOrder")
    public String createOrder(Principal principal,
                              @ModelAttribute(name = "dishIds") String ids,
                              @ModelAttribute(name = "street") String street,
                              @ModelAttribute(name = "house") String house,
                              @ModelAttribute(name = "entrance") String entrance,
                              @ModelAttribute(name = "flor") String flor,
                              @ModelAttribute(name = "apartment") String apartment) {
        StringBuilder address = new StringBuilder();
        address.append("Ул. ").append(street)
                .append(", дом ").append(house)
                .append(", подъезд ").append(entrance)
                .append(", эт. ").append(flor)
                .append(", кв. ").append(apartment);
        List<Dish> dishes = simpleDishService.getBasketDishes(ids);
        int dishAmount = simpleDishService.getDishAmount(dishes);
        Customer customer = simpleCustomerService.findCustomerByUsername(
                simpleCustomerService.getUsername(principal)
        );
        Order order = new Order();
        order.setAmount(dishAmount);
        order.setAddress(address.toString());
        order.setDishes(dishes);
        order.setCustomer(customer);
        /*
        добавить заказчика, курьера и статус
        */
        return "redirect:/client/dish/menu";
    }

}
