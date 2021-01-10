package com.example.Praz.framework.controllers;

import com.example.Praz.framework.anotations.PrazMethodRequest;

public class PageNotFoundController {

    @PrazMethodRequest( method="GET", url="index")
    public void getIndex() {
        System.out.println("** 404 ** Not Found");
    }

    @PrazMethodRequest( method="POST", url="index")
    public void postIndex() {
        System.out.println("** 404 ** Not Found");
    }
}