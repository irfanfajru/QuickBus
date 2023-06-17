package com.quickbus.controller;

import com.quickbus.model.Log;
import com.quickbus.repository.LogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/log")
public class LogController {
    @Autowired
    public LogRepo logRepo;
    @GetMapping("")
    public ResponseEntity<Page<Log>> getAll(
        @RequestParam Integer page,
        @RequestParam Integer size
    ){
        Pageable showData = PageRequest.of(page,size);
        Page<Log> list = logRepo.findAll(showData);
        return new ResponseEntity<Page<Log>>(list,new HttpHeaders(), HttpStatus.OK);
    }
}
