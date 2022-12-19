package ru.job4j.contorller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.Customer;
import ru.job4j.Order;
import ru.job4j.service.OrderService;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private OrderService simpleOrderService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return new ResponseEntity<>(
                simpleOrderService.createOrder(order),
                HttpStatus.OK
        );
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable int id) {
        Order order = simpleOrderService.findOrderById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Order number %s not found", id
                                )
                        )
                );
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<List<Order>> findAllByCustomer(
            @RequestBody Customer customer
    ) {
        return new ResponseEntity<>(
                simpleOrderService.findAllOrdersByCustomer(customer),
                HttpStatus.OK
        );
    }
}