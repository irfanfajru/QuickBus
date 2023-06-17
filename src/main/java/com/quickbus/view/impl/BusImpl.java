package com.quickbus.view.impl;

import com.quickbus.model.Bus;
import com.quickbus.repository.BusRepo;
import com.quickbus.response.ResponseMap;
import com.quickbus.view.BusService;
import com.quickbus.view.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class BusImpl implements BusService {
    @Autowired
    public BusRepo busRepo;

    @Autowired
    public LogService logService;

    @Override
    public ResponseMap save(Bus bus){
        try{
            Bus obj = busRepo.save(bus);
            logService.createActivity(obj.toString());
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

            Bus updatedBus = obj.get();
            updatedBus.setName(bus.getName());
            updatedBus.setType(bus.getType());
            updatedBus.setCapacity(bus.getCapacity());
            updatedBus.setSeatFormat(bus.getSeatFormat());
            updatedBus.setAc(bus.isAc());
            updatedBus.setEntertainment(bus.isEntertainment());
            updatedBus.setReclinerSeat(bus.isReclinerSeat());
            updatedBus.setSmookingRoom(bus.isSmookingRoom());
            updatedBus.setRestRoom(bus.isRestRoom());
            updatedBus.setLuggage(bus.isLuggage());
            updatedBus.setBigLuggage(bus.isBigLuggage());
            updatedBus.setToilet(bus.isToilet());
            busRepo.save(updatedBus);
            logService.updateActivity(updatedBus.toString());
            return new ResponseMap().success(updatedBus,"Success update bus");
        }catch (Exception e){
            return new ResponseMap().error(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to update bus"
            );
        }
    }

    @Override
    public ResponseMap delete(UUID busId){
        try{
            Optional<Bus> obj = busRepo.findById(busId);
            if(!obj.isPresent())
                return new ResponseMap().error(
                        HttpStatus.NOT_FOUND,
                        "Bus not found"
                );
            Bus deletedBus = obj.get();
            deletedBus.setDeletedAt(new Date());
            busRepo.save(deletedBus);
            logService.deleteActivity(deletedBus.toString());
            return new ResponseMap().success(deletedBus,"Success delete bus");
        }catch (Exception e){
            return new ResponseMap().error(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to delete bus"
            );
        }
    }

    @Override
    public ResponseMap getById(UUID busId){
        try{
            Optional<Bus> obj = busRepo.findById(busId);
            if(!obj.isPresent())
                return new ResponseMap().error(
                        HttpStatus.NOT_FOUND,
                        "Bus not found"
                );
            Bus busDetail = obj.get();
            return new ResponseMap().success(busDetail,"Success get bus detail");
        }catch (Exception e){
            return new ResponseMap().error(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to get bus detail"
            );
        }
    }

}
