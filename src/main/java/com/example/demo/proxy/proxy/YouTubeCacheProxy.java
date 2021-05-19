package com.example.demo.proxy.proxy;

import com.example.demo.proxy.some_cool_media_library.ThirdPartyYouTubeClass;
import com.example.demo.proxy.some_cool_media_library.ThirdPartyYouTubeLib;
import com.example.demo.proxy.some_cool_media_library.Video;

import java.util.HashMap;
import java.util.Map;

public class YouTubeCacheProxy implements ThirdPartyYouTubeLib {
    private ThirdPartyYouTubeLib youtubeService = new ThirdPartyYouTubeClass();
    private Map<String, Video> cachePopular = new HashMap<>();
    private Map<String, Video> cacheAll = new HashMap<>();

    @Override
    public Map<String, Video> popularVideos() {
        if(cachePopular.isEmpty()){
            cachePopular = youtubeService.popularVideos();
        } else {
            System.out.println("Retrived list from cache.");
        }
        return cachePopular;
    }

    @Override
    public Video getVideo(String videoId) {
        Video video = cacheAll.get(videoId);
        if(video == null){
            video = youtubeService.getVideo(videoId);
            cacheAll.put(videoId, video);
        } else {
            System.out.println("Retrived video " + videoId + " from cache.");
        }
        return video;
    }

    public void reset(){
        cachePopular.clear();
        cacheAll.clear();
    }
}
