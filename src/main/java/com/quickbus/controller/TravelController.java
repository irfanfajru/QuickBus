package com.quickbus.controller;

import com.quickbus.model.Travel;
import com.quickbus.repository.TravelRepo;
import com.quickbus.response.ResponseMap;
import com.quickbus.view.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/v1/travel")
public class TravelController {

    @Autowired
    TravelService travelService;

    @Autowired
    TravelRepo travelRepo;

    @PostMapping("")
    public ResponseEntity<ResponseMap> save(
            @RequestBody Travel travel
            ){
        ResponseMap response = travelService.save(travel);
        return new ResponseEntity<ResponseMap>(response,response.getCode());
    }

    @PutMapping("/{travelId}")
    public ResponseEntity<ResponseMap> update(
            @RequestBody Travel travel,
            @PathVariable UUID travelId
            ){
        ResponseMap response = travelService.update(travel,travelId);
        return new ResponseEntity<ResponseMap>(response,response.getCode());
    }

    @DeleteMapping("/{travelId}")
    public ResponseEntity<ResponseMap> delete(
            @PathVariable UUID travelId
    ){
        ResponseMap response = travelService.delete(travelId);
        return new ResponseEntity<ResponseMap>(response,response.getCode());
    }

    @GetMapping("")
    public ResponseEntity<Page<Travel>> list(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam() String departure,
            @RequestParam() String arrival,
            @RequestParam() @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
            ){
        Pageable showData = PageRequest.of(page,size);
        Page<Travel> list = travelRepo.findTravel(departure,arrival,date,showData);
        return new ResponseEntity<Page<Travel>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{travelId}")
    public ResponseEntity<ResponseMap> travelDetail(
            @PathVariable UUID travelId
    ){
        ResponseMap response = travelService.getById(travelId);
        return new ResponseEntity<ResponseMap>(response, response.getCode());
    }
}
