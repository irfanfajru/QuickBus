package com.quickbus.controller;

import com.quickbus.model.Bus;
import com.quickbus.model.Log;
import com.quickbus.model.oauth.User;
import com.quickbus.repository.BusRepo;
import com.quickbus.response.ResponseMap;
import com.quickbus.view.BusService;
import com.quickbus.view.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/v1/bus")
public class BusController {

    @Autowired
    BusService busService;

    @Autowired
    BusRepo busRepo;

    @PostMapping("")
    public ResponseEntity<ResponseMap> save(
            @Valid
            @RequestBody Bus bus
            ){
        ResponseMap response = busService.save(bus);
        return new ResponseEntity<ResponseMap>(response,response.getCode());
    }

    @PutMapping("/{busId}")
    public ResponseEntity<ResponseMap> update(
            @Valid
            @RequestBody Bus bus,
            @PathVariable UUID busId
            ){
        ResponseMap response = busService.update(bus, busId);
        return new ResponseEntity<ResponseMap>(response,response.getCode());
    }

    @DeleteMapping("/{busId}")
    public ResponseEntity<ResponseMap> delete(
            @PathVariable UUID busId
    ){
        ResponseMap response = busService.delete(busId);
        return new ResponseEntity<ResponseMap>(response,response.getCode());
    }

    @GetMapping("")
    public ResponseEntity<Page<Bus>> list(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String name
            ){
        Pageable showData = PageRequest.of(page,size);
        Page<Bus> list = null;
        if(name!=null){
            list = busRepo.findByNameLike("%"+name+"%",showData);
        }else{
            list = busRepo.findAll(showData);
        }
        return new ResponseEntity<Page<Bus>>(list,new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/{busId}")
    public ResponseEntity<ResponseMap> busDetail(
            @PathVariable UUID busId
    ){
        ResponseMap response = busService.getById(busId);
        return new ResponseEntity<ResponseMap>(response,response.getCode());
    }
}
