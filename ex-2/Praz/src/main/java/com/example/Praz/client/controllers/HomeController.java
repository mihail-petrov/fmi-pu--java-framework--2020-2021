package com.example.Praz.client.controllers;

import com.example.Praz.framework.anotations.PrazMethodRequest;

// * http://localhost/praz_artefact/home
// * http://localhost/praz_artefact/home/index
// * http://localhost/praz_artefact/home/buy
// * http://localhost/praz_artefact/home/sell
public class HomeController {

    @PrazMethodRequest( method="GET", url="index")
    public void index() {
        System.out.println("Hello world");
    }

    @PrazMethodRequest( method="GET", url="buy")
    public void buy() {
        System.out.println("Buy new stock");
    }

    @PrazMethodRequest( method="GET", url="sell")
    public void sell() {
        System.out.println("Sell my stock");
    }
}
