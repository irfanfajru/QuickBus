package com.quickbus.controller;

import com.quickbus.model.Bus;
import com.quickbus.response.ResponseMap;
import com.quickbus.view.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/bus")
public class BusController {

    @Autowired
    BusService busService;

    @PostMapping("")
    public ResponseEntity<ResponseMap> save(
            @RequestBody Bus bus
            ){
        ResponseMap response = busService.save(bus);
        return new ResponseEntity<ResponseMap>(response,response.getCode());
    }

    @PutMapping("/{busId}")
    public ResponseEntity<ResponseMap> update(
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

//    @GetMapping("/list")
//    public ResponseEntity<Page<Transaksi>> list(
//            @RequestParam() Integer page,
//            @RequestParam() Integer size,
//            @RequestParam(required = false) String status){
//        Pageable showData = PageRequest.of(page,size);
//        Page<Transaksi> list = null;
//        if(status!=null){
//            list = transaksiRepo.findByStatusLike("%"+status+"%",showData);
//        }else{
//            list = transaksiRepo.findAll(showData);
//        }
//        return new ResponseEntity<Page<Transaksi>>(list,new HttpHeaders(), HttpStatus.OK);
//    }
}
