package br.com.vulcanodev.btgpactual_challenge.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vulcanodev.btgpactual_challenge.domain.applications.ports.OrderServicePort;
import br.com.vulcanodev.btgpactual_challenge.domain.model.Order;

@RestController()
@RequestMapping(path = "/orders")
public class OrderController {

    @Autowired
    private OrderServicePort orderService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
}
