package com.springtest;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class test11 {
    public static void main(String[] args) throws UnknownHostException {

        ChartReceive chartReceive = new ChartReceive();
        chartReceive.start();

        InetAddress locAdd = InetAddress.getLocalHost(); //得到本地InetAddress对象
        ChartSend chartSend = new ChartSend(locAdd);
        chartSend.start();
    }

}
