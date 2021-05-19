package com.example.demo.proxy.some_cool_media_library;

import java.util.HashMap;
import java.util.Map;

public class ThirdPartyYouTubeClass implements ThirdPartyYouTubeLib {

    @Override
    public Map<String, Video> popularVideos() {
        connectToServer("https://www.youtube.com");
        return getRandomVideos();
    }

    private void connectToServer(String server) {
        System.out.println("Connecting to " + server + "... ");
        experienceNetworkLatency();
        System.out.println("Connected");
    }

    private void experienceNetworkLatency() {
        int randomLatency = random(5, 10);
        for (int i = 0; i < randomLatency; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private int random(int min, int max) {
        return min + (int) Math.random() * ((max - min) + 1);
    }

    private Map<String, Video> getRandomVideos() {
        System.out.print("Downloading populars... ");

        experienceNetworkLatency();
        Map<String, Video> map = new HashMap<String, Video>();
        map.put("catzzzzzzzzz", new Video("sadgahasgdas", "Catzzzz.avi"));
        map.put("mkafksangasj", new Video("mkafksangasj", "Dog play with ball.mp4"));
        map.put("dancesvideoo", new Video("asdfas3ffasd", "Dancing video.mpq"));
        map.put("dlsdk5jfslaf", new Video("dlsdk5jfslaf", "Barcelona vs RealM.mov"));
        map.put("3sdfgsd1j333", new Video("3sdfgsd1j333", "Programing lesson#1.avi"));

        System.out.print("Done!" + "\n");
        return map;
    }


    @Override
    public Video getVideo(String videoId) {
        connectToServer("https://www.youtube.com/" + videoId);
        return getSomeVideo(videoId);

    }

    private Video getSomeVideo(String videoId) {
        System.out.println("Downloading video...");
        experienceNetworkLatency();
        Video video = new Video(videoId, "Some video title");

        System.out.println("Done!");
        return video;
    }
}
