package com.example.demo.state;

import com.zaxxer.hikari.util.FastList;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private boolean playing = false;
    private int currentTrack = 0;
    private List<String> playlist = new ArrayList<>();
    private State state;

    public Player() {
        this.state = new ReadyState(this);
        setPlaying(true);
        for (int i = 0; i < 12; i++) {
            playlist.add("Track " + i);
        }
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;    
    }

    public boolean isPlaying() {
        return playing;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public String startPlayBack() {
        return "Playing " + playlist.get(currentTrack);
    }

    public void setCurrentTrackAfterStop() {
        currentTrack = 0;
    }

    public String nextTrack() {
        currentTrack++;
        if(currentTrack > playlist.size() - 1){
            currentTrack = 0;
        }
        return "Playing " + playlist.get(currentTrack);
    }

    public String previousTrack() {
        currentTrack--;
        if(currentTrack < 0){
            currentTrack = playlist.size() - 1;
        }
        return "Playing " + playlist.get(currentTrack);
    }

    public String playing() {
        return state.onPlay();
    }

    public String stopping() {
        return state.onLock();
    }

    public String nextPlaying() {
        return state.onNext();
    }

    public String prevPlaying() {
        return state.onPrevious();
    }
}
