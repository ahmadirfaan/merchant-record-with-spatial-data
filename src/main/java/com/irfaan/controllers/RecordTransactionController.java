package com.irfaan.controllers;


import com.irfaan.entities.RecordsTransaction;
import com.irfaan.exceptions.EntityNotFoundException;
import com.irfaan.models.RecordCustomerMerchant;
import com.irfaan.models.ResponseMessage;
import com.irfaan.services.RecordTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/record")
@RestController
public class RecordTransactionController {


    @Autowired
    private RecordTransactionService service;


    @PostMapping
    public ResponseMessage<RecordsTransaction> add(@RequestBody @Valid RecordsTransaction record) {
        System.out.println(record);
        RecordsTransaction data = service.save(record);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<RecordsTransaction> edit(@PathVariable Integer id, @RequestBody @Valid RecordsTransaction model) {
        RecordsTransaction data = service.update(model, id);
        return ResponseMessage.success(data);
    }


    @DeleteMapping("/{id}")
    public ResponseMessage<RecordsTransaction> removeById(@PathVariable Integer id) {
        RecordsTransaction data = service.removeByID(id);
        if (data == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<RecordsTransaction> findbyId(@PathVariable Integer id) {
        RecordsTransaction entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return ResponseMessage.success(entity);
    }

    @GetMapping()
    public ResponseMessage<List<RecordCustomerMerchant>> findAll() {

        List<RecordCustomerMerchant> entities = service.findAllRecordWithCustomerAndMerchant();
        return ResponseMessage.success(entities);
    }


}
