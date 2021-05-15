package com.example.demo.abstract_factory;

import com.example.demo.abstract_factory.app.Application;
import com.example.demo.abstract_factory.factories.GUIFactory;
import com.example.demo.abstract_factory.factories.MacOSFactory;
import com.example.demo.abstract_factory.factories.WindowsFactory;

public class Demo {
    public static Application configureApplication() {
        Application application;
        GUIFactory factory;
        String osName = System.getProperty("os.name").toLowerCase();
        if(osName.contains("mac")){
            factory = new MacOSFactory();
            application = new Application(factory);
        } else{
            factory = new WindowsFactory();
            application = new Application(factory);
        }
        return application;
    }

    public static void main(String[] args) {
        Application app = configureApplication();
        app.paint();
    }
}
