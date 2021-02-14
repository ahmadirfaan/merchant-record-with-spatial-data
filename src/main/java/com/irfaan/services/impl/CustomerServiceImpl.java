package com.irfaan.services.impl;

import com.irfaan.entities.Customer;
import com.irfaan.repositories.CustomerRepository;
import com.irfaan.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        Customer entity = null;
        if(customer != null) {
            entity = repository.save(customer);
        }
        return entity;
    }

    @Override
    public Customer findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Customer update(Customer customer, Integer id) {
        Customer entity = findById(id);
        if(entity != null) {
            if(customer.getFirstName() == null) {
                customer.setFirstName(entity.getFirstName());
            }
            if(customer.getLastName() == null) {
                customer.setLastName(entity.getLastName());
            }
            if(customer.getAddress() == null) {
                customer.setAddress(entity.getAddress());
            }
            if(customer.getCity() == null) {
                customer.setCity(entity.getCity());
            }
            entity = repository.update(customer, id);
        }
        return entity;
    }

    @Override
    public Customer removeByID(Integer id) {
        Customer entity = findById(id);
        if(entity != null) {
            entity = repository.removeByID(id);
        }
        return entity;
    }
}
