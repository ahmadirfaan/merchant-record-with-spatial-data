package com.irfaan.services;

import com.irfaan.entities.MerchantLocation;
import com.irfaan.models.MerchantLocationDistance;

import java.util.List;

public interface MerchantLocationService {
    List<MerchantLocation> findAllMerchantLocationWithInSubCounty(Integer subCountyId);
    List<MerchantLocationDistance> findAllMerchanyByDistanceFromCustomer(Double customerLongitude, Double customerLattitude);
}
