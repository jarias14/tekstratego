package com.jarias14.tekstratego.common;

import com.ib.controller.ApiController;

import java.util.ArrayList;

/**
 * Created by jarias14 on 4/12/2015.
 */
public class InteractiveBrokersConnectionHandler implements ApiController.IConnectionHandler {

    @Override
    public void connected() {
        System.out.println("connected!");

    }

    @Override
    public void disconnected() {
        System.out.println("disconnected!");

    }

    @Override
    public void accountList(ArrayList<String> list) {

    }

    @Override
    public void error(Exception e) {
        System.out.println("error" + e.getMessage());
    }

    @Override
    public void message(int id, int errorCode, String errorMsg) {
        System.out.println("id: " + id + ", errorCode:" + errorCode + ", errorMsg: " + errorMsg);
    }

    @Override
    public void show(String string) {
        System.out.println("show: " + string);

    }
}

