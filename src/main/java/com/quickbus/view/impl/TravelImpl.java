package com.quickbus.view.impl;

import com.quickbus.model.Bus;
import com.quickbus.model.Travel;
import com.quickbus.repository.BusRepo;
import com.quickbus.repository.TravelRepo;
import com.quickbus.response.ResponseMap;
import com.quickbus.view.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class TravelImpl implements TravelService {

    @Autowired
    public TravelRepo travelRepo;

    @Autowired
    public BusRepo busRepo;

    @Override
    public ResponseMap save(Travel travel){
        try{
//            check available bus
            Optional<Bus> bus = busRepo.findById(travel.getBus().getId());
            if(!bus.isPresent()){
                return new ResponseMap().error(HttpStatus.NOT_FOUND,
                        "Bus not found"
                );
            }
            travel.setAvailableSeat(bus.get().getCapacity());
            travel.setBus(bus.get());
            Travel obj = travelRepo.save(travel);
            return new ResponseMap().success(obj,"Success create travel");
        }catch (Exception e){
            return new ResponseMap().error(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to create travel"
            );
        }
    }

    @Override
    public ResponseMap update(Travel travel, UUID travelId){
        try {
//        check travel id
            Optional<Travel> obj = travelRepo.findById(travelId);
            if (!obj.isPresent()) {
                return new ResponseMap().error(
                        HttpStatus.NOT_FOUND,
                        "Travel not found"
                );
            }
//        check updated bus
            Optional<Bus> bus = busRepo.findById(travel.getBus().getId());
            if (!bus.isPresent()) {
                return new ResponseMap().error(
                        HttpStatus.NOT_FOUND,
                        "Bus not found"
                );
            }
            Travel updatedTravel = obj.get();
            Bus updatedBus = bus.get();
//        update
            updatedTravel.setDeparture(travel.getDeparture());
            updatedTravel.setDepartureLocation(travel.getDepartureLocation());
            updatedTravel.setDepartureDate(travel.getDepartureDate());
            updatedTravel.setDepartureTime(travel.getDepartureTime());
            updatedTravel.setArrival(travel.getArrival());
            updatedTravel.setArrivalLocation(travel.getArrivalLocation());
            updatedTravel.setArrivalDate(travel.getArrivalDate());
            updatedTravel.setArrivalTime(travel.getArrivalTime());
            updatedTravel.setPrice(travel.getPrice());
            updatedTravel.setAvailableSeat(travel.getAvailableSeat());
            updatedTravel.setBus(updatedBus);
            travelRepo.save(updatedTravel);
            return new ResponseMap().success(
              updatedTravel, "Success update travel"
            );
        }catch (Exception e){
            return new ResponseMap().error(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to update travel"
            );
        }
    }

    @Override
    public ResponseMap delete(UUID travelId){
        try{
//            check travel id
            Optional<Travel> obj = travelRepo.findById(travelId);
            if (!obj.isPresent()) {
                return new ResponseMap().error(
                        HttpStatus.NOT_FOUND,
                        "Travel not found"
                );
            }

            Travel deletedTravel = obj.get();
            deletedTravel.setDeletedAt(new Date());
            travelRepo.save(deletedTravel);
            return new ResponseMap().success(
              HttpStatus.OK,
                    "Success delete travel"
            );

        }catch (Exception e){
            return new ResponseMap().error(
              HttpStatus.INTERNAL_SERVER_ERROR,
              "Failed to delete travel"
            );
        }
    }

    @Override
    public ResponseMap getById(UUID travelId){
        try{
            //            check travel id
            Optional<Travel> obj = travelRepo.findById(travelId);
            if (!obj.isPresent()) {
                return new ResponseMap().error(
                        HttpStatus.NOT_FOUND,
                        "Travel not found"
                );
            }

            Travel travelDetail = obj.get();
            return new ResponseMap().success(
                    travelDetail,
                    "Success get travel detail"
            );
        }catch (Exception e){
            return new ResponseMap().error(
              HttpStatus.INTERNAL_SERVER_ERROR,
              "Failed to get travel detail"
            );
        }
    }
}
