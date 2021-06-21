package com.example.demo.bridge;

import com.example.demo.bridge.devices.Device;
import com.example.demo.bridge.devices.Radio;
import com.example.demo.bridge.devices.Tv;
import com.example.demo.bridge.remotes.AdvancedRemote;
import com.example.demo.bridge.remotes.BasicRemote;

public class Demo {
    public static void main(String[] args) {
        testDevice(new Tv());
        testDevice(new Radio());
    }

    public static void testDevice(Device device) {
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }
}
