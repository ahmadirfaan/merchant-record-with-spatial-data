package com.irfaan.controllers;

import com.irfaan.entities.MerchantLocation;
import com.irfaan.models.MerchantLocationDistance;
import com.irfaan.models.ResponseMessage;
import com.irfaan.services.MerchantLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantLocationService service;

    @GetMapping("/withinsubcounty")
    public ResponseMessage<List<MerchantLocation>> findAllStoreWithinSubCounty(@RequestParam("id") Integer subCountyId) {
        List<MerchantLocation> data = service.findAllMerchantLocationWithInSubCounty(subCountyId);
        return ResponseMessage.success(data);
    }

    @GetMapping("/nearbymerchant")
    public ResponseMessage<List<MerchantLocationDistance>> findAllStoreByDistanceFromUser(@RequestParam("customerlocation") List<Double> customerLocation) {
        Double customerLongitude = customerLocation.get(0);
        Double customerLattitude = customerLocation.get(1);
        List<MerchantLocationDistance> merchants = service.findAllMerchanyByDistanceFromCustomer(customerLongitude, customerLattitude);
        List<MerchantLocationDistance> data = merchants.stream().peek(s -> s.setDistance(s.getDistance() * 111000.00)).collect(Collectors.toList());
        return ResponseMessage.success(data);
    }
}
