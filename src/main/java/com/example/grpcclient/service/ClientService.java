package com.example.grpcclient.service;

import com.poc.grpc.generate.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author a0k02hz
 */
@Service
public class ClientService {

    @Autowired
    private RestTemplate restTemplate;

    public HelloResponse hello(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080 )
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub
                = HelloServiceGrpc.newBlockingStub(channel);

        com.poc.grpc.generate.HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Baeldung")
                .setLastName("gRPC")
                .build());

        return helloResponse;
    }

    public com.example.grpcclient.dto.HelloResponse helloWithRest(){
        String url = "http://localhost:8091/hellowithrest";
        return restTemplate.getForEntity(url, com.example.grpcclient.dto.HelloResponse.class).getBody();
    }

    public HelloResponse order(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080 )
                .usePlaintext()
                .build();

        OrderServiceGrpc.OrderServiceBlockingStub stub
                = OrderServiceGrpc.newBlockingStub(channel);

        com.poc.grpc.generate.HelloResponse helloResponse = stub.processOrder(DailyShippedOrders_v2.newBuilder()
                .build());

        return helloResponse;
    }

    public com.example.grpcclient.dto.HelloResponse orderWithRest(com.example.grpcclient.dto.DailyShippedOrders_v2 request){
        String url = "http://localhost:8091/orderwithrest";
        return restTemplate.postForEntity(url, request, com.example.grpcclient.dto.HelloResponse.class).getBody();
    }
}
