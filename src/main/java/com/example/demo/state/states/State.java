package com.example.demo.state.states;

import com.example.demo.state.ui.Player;

/*
 8 Common interface for all states
 */
public abstract class State {
    Player player;

    public State(Player player) {
        this.player = player;
    }

    public abstract String onLock();
    public abstract String onPlay();
    public abstract String onNext();
    public abstract String onPrevious();
}
