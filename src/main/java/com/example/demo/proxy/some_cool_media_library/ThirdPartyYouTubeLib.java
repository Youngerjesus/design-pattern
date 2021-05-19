package com.example.demo.proxy.some_cool_media_library;

import java.util.Map;

public interface ThirdPartyYouTubeLib {
    Map<String, Video> popularVideos();

    Video getVideo(String videoId);
}
