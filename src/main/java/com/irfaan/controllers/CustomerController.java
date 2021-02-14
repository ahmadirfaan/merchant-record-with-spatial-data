package com.irfaan.controllers;


import com.irfaan.entities.Customer;
import com.irfaan.exceptions.EntityNotFoundException;
import com.irfaan.models.ResponseMessage;
import com.irfaan.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/customer")
@RestController
public class CustomerController {


    @Autowired
    private CustomerService service;


    @PostMapping
    public ResponseMessage<Customer> add(@RequestBody @Valid Customer customer) {
        Customer data = service.save(customer);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<Customer> edit(@PathVariable Integer id, @RequestBody @Valid Customer model) {

        Customer data = service.update(model, id);
        return ResponseMessage.success(data);
    }


    @DeleteMapping("/{id}")
    public ResponseMessage<Customer> removeById(@PathVariable Integer id) {
        Customer data = service.removeByID(id);
        if (data == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<Customer> findbyId(@PathVariable Integer id) {
        Customer entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(entity);
    }

    @GetMapping()
    public ResponseMessage<List<Customer>> findAll() {

        List<Customer> entities = service.findAll();
        return ResponseMessage.success(entities);
    }


}
