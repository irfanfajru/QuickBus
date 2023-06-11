package com.quickbus.view.impl;

import com.quickbus.model.Bus;
import com.quickbus.repository.BusRepo;
import com.quickbus.response.ResponseMap;
import com.quickbus.view.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class BusImpl implements BusService {
    @Autowired
    public BusRepo busRepo;

    @Override
    public ResponseMap save(Bus bus){
        try{
            Bus obj = busRepo.save(bus);
            return new ResponseMap().success(obj,"Success create bus");

        }catch(Exception e){
            return new ResponseMap().error(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to create bus"
            );
        }
    }

    @Override
    public ResponseMap update(Bus bus, UUID busId){
        try{
           Optional<Bus> obj = busRepo.findById(busId);
            if(!obj.isPresent())
                return new ResponseMap().error(
                        HttpStatus.NOT_FOUND,
                        "Bus not found"
                );

            busRepo.save(bus);
            return new ResponseMap().success(bus,"Succes update bus");
        }catch (Exception e){
            return new ResponseMap().error(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to update bus"
            );
        }
    }
}
