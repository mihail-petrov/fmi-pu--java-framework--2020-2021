package com.example.Praz.framework.controllers;

import com.example.Praz.framework.anotations.PrazMethodRequest;
import com.example.Praz.framework.config.Locator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(value = "/*")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        this.requestProcessor(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        this.requestProcessor(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        this.requestProcessor(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        this.requestProcessor(req, resp);
    }

    private FrontControllerRouter getRouterCollectionArtifactData(String requestPath) {

        String[] requestPathCollection  = requestPath.substring(1).split("/");
        String controllerId;
        String methodId;

        try {
            controllerId =  requestPathCollection[0];
            controllerId =  controllerId.substring(0, 1).toUpperCase()    +
                            controllerId.substring(1)                     +
                            "Controller";
            controllerId = Locator.getControllerLocation(controllerId);
        }
        catch (Exception exception) {
            controllerId = Locator.getDefaultControllerLocation();
        }

        try {
            methodId    = requestPathCollection[1];
        }
        catch (Exception exception) {
            methodId    = Locator.getDefaultMethod();
        }

        return new FrontControllerRouter(controllerId, methodId);
    }

    private void routeLoader(FrontControllerRouter router,
                             HttpServletRequest req,
                             HttpServletResponse resp)
    throws  ClassNotFoundException      ,
            NoSuchMethodException       ,
            IllegalAccessException      ,
            InvocationTargetException   ,
            InstantiationException  {

        String controllerId         = router.getController();

        // URL address
        String methodId             = router.getMethod();

        String httpRequestMethod    = req.getMethod();

        Class controllerReference   = Class.forName(controllerId);
        Object controllerInstance   = controllerReference.getDeclaredConstructor().newInstance();

        // # get all methods and there annotations
        Method[] controllerMethodCollection = controllerReference.getMethods();
        for(int i = 0; i < controllerMethodCollection.length; i++) {

            Method methodReference =  controllerMethodCollection[i];
            PrazMethodRequest annotationReference = methodReference.getAnnotation(PrazMethodRequest.class);
            if(annotationReference == null) continue;

            String urlPath                      = annotationReference.url();
            String httpMethod                   = annotationReference.method();
            boolean isRequestToHttpMethodValid  = urlPath.equals(methodId) &&
                                                  httpMethod.equals(httpRequestMethod);

            if(isRequestToHttpMethodValid) {

                methodReference.invoke(controllerInstance);
                return;
            }
        }

        throw new NoSuchMethodException();

        //
        // Method controllerMethod     = controllerReference.getDeclaredMethod(methodId);
        // controllerMethod.invoke(controllerInstance);
    }

    private void loadPageNotFound(HttpServletRequest req, HttpServletResponse resp) {

        String controllerId = Locator.getPageNotFoundControllerLocation();
        String methodId     = Locator.getPageNotFoundMethod();

        FrontControllerRouter pageNotFoundRouter = new FrontControllerRouter(controllerId, methodId);

        try {
            this.routeLoader(pageNotFoundRouter, req, resp);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void requestProcessor(HttpServletRequest req, HttpServletResponse resp) {

        String requestPath              = req.getPathInfo();
        FrontControllerRouter router    = this.getRouterCollectionArtifactData(requestPath);

        try {
            this.routeLoader(router, req, resp);
        } catch (   ClassNotFoundException      |
                    InstantiationException      |
                    InvocationTargetException   |
                    NoSuchMethodException       |
                    IllegalAccessException e    ) {
            this.loadPageNotFound(req, resp);
        }
    }

    class FrontControllerRouter {

        private String controller;
        private String method;

        public FrontControllerRouter(String controller, String method) {
            this.controller = controller;
            this.method = method;
        }

        public String getController() {
            return this.controller;
        }

        public String getMethod() {
            return this.method;
        }
    }
}
