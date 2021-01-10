package com.example.Praz.client.controllers;

import com.example.Praz.framework.anotations.PrazController;
import com.example.Praz.framework.anotations.PrazMethodRequest;

@PrazController( name = "location")
public class ServiceController {

    // service/index
    @PrazMethodRequest( method="GET", url="index")
    public void getIndex() {
        System.out.println("Service controller - GET - index");
    }

    @PrazMethodRequest( method="POST", url="index")
    public void processInitialOperation() {
        System.out.println("Service controller - POST - index");
    }

    @PrazMethodRequest( method="GET", url="transfer")
    public void transferCustomerData() {
        System.out.println("Service controller - transfer");
    }
}
