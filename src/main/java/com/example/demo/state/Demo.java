package com.example.demo.state;

import com.example.demo.state.ui.Player;
import com.example.demo.state.ui.Ui;

public class Demo {
    public static void main(String[] args) {
        Player player = new Player();
        Ui ui = new Ui(player);
        ui.init();;
    }
}
