package com.irfaan.services.impl;

import com.irfaan.entities.MerchantLocation;
import com.irfaan.models.MerchantLocationDistance;
import com.irfaan.repositories.MerchantLocationRepository;
import com.irfaan.services.MerchantLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantLocationServiceImpl implements MerchantLocationService {

    @Autowired
    private MerchantLocationRepository repository;

    @Override
    public List<MerchantLocation> findAllMerchantLocationWithInSubCounty(Integer subCountyId) {
        return  repository.findAllMerchantLocationWithInSubCounty(subCountyId);
    }

    @Override
    public List<MerchantLocationDistance> findAllMerchanyByDistanceFromCustomer(Double customerLongitude, Double customerLattitude) {
        return repository.findAllMerchanyByDistanceFromCustomer(customerLongitude, customerLattitude);
    }
}
