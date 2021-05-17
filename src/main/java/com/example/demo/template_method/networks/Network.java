package com.example.demo.template_method.networks;

public abstract class Network {
    String username;
    String password;

    Network(){}

    public boolean post(String message){
        if(login(username, password)){
            boolean result = sendData(message.getBytes());
            logOut();
            return result;
        }
        return false;
    }

    protected abstract boolean login(String username, String password);

    protected abstract boolean sendData(byte[] data);

    protected abstract void logOut();
}
