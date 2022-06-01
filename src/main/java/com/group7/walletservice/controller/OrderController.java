package com.group7.walletservice.controller;

import com.group7.walletservice.dto.OrderRequest;
import com.group7.walletservice.dto.OrderResponse;
import com.group7.walletservice.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public Flux<OrderResponse> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<OrderResponse> save(@Valid @RequestBody OrderRequest orderRequest) {
        return orderService.save(orderRequest);
    }

    @PutMapping("{id}/take")
    public Mono<OrderResponse> take(@PathVariable String id, @RequestBody String walletSeller) {
        return orderService.take(id, walletSeller);
    }

    @PutMapping("{id}/done")
    public Mono<OrderResponse> done(@PathVariable String id, @RequestBody String transactionNumber) {
        return orderService.done(id, transactionNumber);
    }
}
