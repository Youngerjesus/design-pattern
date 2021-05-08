package com.example.demo.state;

public class Demo {
    public static void main(String[] args) {
        Player player = new Player();
        Ui ui = new Ui(player);
        ui.init();;
    }
}
