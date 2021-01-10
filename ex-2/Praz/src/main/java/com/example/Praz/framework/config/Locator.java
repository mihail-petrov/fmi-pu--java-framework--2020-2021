package com.example.Praz.framework.config;

public class Locator {

    private static final String CLIENT_CONTROLLER_LOCATION      = "com.example.Praz.client.controllers";
    private static final String FRAMEWORK_CONTROLLER_LOCATION   = "com.example.Praz.framework.controllers";

    private static final String DEFAULT_CONTROLLER              = "HomeController";
    private static final String DEFAULT_METHOD                  = "index";

    private static final String PAGE_NOT_FOUND_CONTROLLER       = "PageNotFoundController";
    private static final String PAGE_NOT_FOUND_METHOD           = "index";

    public static String getClientControllerLocation() {
        return CLIENT_CONTROLLER_LOCATION;
    }

    public static String getFrameworkControllerLocation() {
        return FRAMEWORK_CONTROLLER_LOCATION;
    }

    public static String getControllerLocation(String className) {
        return CLIENT_CONTROLLER_LOCATION + "." + className;
    }

    public static String getFrameworkControllerLocation(String className) {
        return FRAMEWORK_CONTROLLER_LOCATION + "." + className;
    }

    public static String getDefaultController() {
        return DEFAULT_CONTROLLER;
    }

    public static String getDefaultControllerLocation() {
        return getControllerLocation(getDefaultController());
    }

    public static String getDefaultMethod() {
        return DEFAULT_METHOD;
    }

    public static String getPageNotFoundController() {
        return PAGE_NOT_FOUND_CONTROLLER;
    }

    public static String getPageNotFoundMethod() {
        return PAGE_NOT_FOUND_METHOD;
    }

    public static String getPageNotFoundControllerLocation() {
        return getFrameworkControllerLocation(getPageNotFoundController());
    }
}
