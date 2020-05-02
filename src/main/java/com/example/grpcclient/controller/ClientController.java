package com.example.grpcclient.controller;

import com.example.grpcclient.service.ClientService;
import com.poc.grpc.generate.HelloResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author a0k02hz
 */

@RestController
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @GetMapping("/hellowithgrpc")
    public String helloWithGrpc() {
        Date start = new Date();
        String greeting = clientService.hello().getGreeting();
        Date end = new Date();
        System.out.println("time taken for grpc:"+(end.getTime()-start.getTime()));
        return greeting;
    }

    @GetMapping("/hellowithrest")
    public String helloWithRest() {
        Date start = new Date();
        String greeting = clientService.helloWithRest().getGreeting();
        Date end = new Date();
        System.out.println("time taken for rest:"+(end.getTime()-start.getTime()));
        return greeting;
    }
}
