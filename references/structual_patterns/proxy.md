# Proxy

Proxy 란 뜻은 대리인이다.

특정한 객체에게 내리는 명령을 직접적인 접근을 통해서 하는게 아니라 프록시를 통해서만 접근을 하도록 하는 패턴이다.

Proxy Pattern 은 활용도가 매우 높은 패턴이다.

- 프록시에서 객체의 지연로딩을 지원해주거나
- 프록시에서 권한을 통한 접근 제어를 제공해줄 수도 있고
- 프록시에서 로깅과 캐싱을 적용하는 것도 가능하다.

물론 프록시 없이 클라이언트에서 필요한 기능을 추가할 수도 있다. 이는 클라이언트 코드를 읽기 어려운, 의도를 알기 어렵도록 한다.

프록시 패턴을 통해서 캐싱을 제공해주는 Youtube Manager 예제를 소개하곘다.

```java
interface ThirdPartyYouTubeLib {
	List<Video> listVideos(); 
	Video getVideoInfo(long id); 
	Video downloadVideo(long id); 
}
```

```java
// Youtube 를 직접 API 로 호출해서 결과를 가지고 오는 클래스 
public class ThirdPartyYouTubeClass implements ThirdPartyYouTubeLib {

	@Override
	public List<Video> listVideos() {
		...
		// Send an API request to YouTube.
	}

	@Override
	public Video getVideoInfo(long id) {
		...
		// Get metadata about some video.
	}

	@Override 
	public Video downloadVideo(long id) {
		...
		// Download a video file from YouTube.
	}
}
```

```java
// ThirdPartyYoutubeClass 에서 캐싱을 제공해주는 프록시 객체 
public class CachedYouTubeClass implements ThirdPartyYouTubeLib {
	private ThirdPartyYoutubeLib thirdPartyYoutubeLib; 
	private List<Video> listCache; 
	private Map<Long, Video> videoCache; 
	private Map<Long, Video> downloadCache; 
	private boolean isNeedReset;

	public CacheYoutubeClass(ThirdPartyYoutubeLib thirdPartyYoutubeLib) {
		this.thirdPartyYoutubeLib = thirdPartyYoutubeLib; 
		listCache = new ArrayList<>(); 
		videoCache = new HashMap<>(); 
		isNeedReset = false; 
	}
	
	@Override 
	public List<Video> listVideos() {
		if (listCache.isEmpty() || isNeedReset) 
			listCache = thirdPartyYoutubeLib.listVideos(); 

		return listCache; 
	}

	@Override
	public Video getVideoInfo(long id) {
		if (videoCache.containseKey(id) || isNeedReset) {
			Video video = thirdPartyYoutubeLib.getVideoInfo(id); 
			videoCache.put(id, video); 
		}

		return this.videoCache.get(id); 
	}

	@Override 
	public Video downloadVideo(long id) [
		if (!downloadExists(id) || isNeedReset) {
			Video video = thirdPartyYoutubeLib.downloadVideo(id); 
			downloadCache.put(id, video);
		}

		return downloadCache.get(id); 
	}
	
	public boolean downloadExists(long id) {
		... 
		// 다운로드한 비디오 관리 정책은 다를 수 있음.
	}
}
```

```java
public class YoutubeManager {
	private ThirdPartyYoutubeLib thirdPartyYoutubeLib; 

	public YoutubeManger(ThirdPartyYoutubeLib thirdPartyYoutubeLib) {
		this.thirdPartyYoutubeLib = thirdPartyYoutubeLib; 
	}

	public void renderVideoPage(long id) {
		Video video = thirdPartyYoutubeLib.getVideoInfo(id);
		... 
		// 렌더링 하는 로직
	}

	public void renderListPanel(long id) {
		List<Video> videos = thirdPartyYoutubeLib.listVideos();
		...
		// 렌더링 하는 로직
	}

	public void reactOnUserInput() {
		renderListPanel(); 
		...
		renderVideoPage(id); 
	}
}
```

```java
public class Client {
	public static void main(String[] args) {
		init(); 
	}

	public void init() {
		ThirdPartyYoutubeLib aYouTubeService = new ThirdPartyYouTubeClass(); 
		ThirdPartyYoutubeLib aYouTubeProxy = new CachedYouTubeClass(aYouTubeService)
		YoutubeManager manager = new YouTubeManager(aYouTubeProxy)
		manager.reactOnUserInput()
	}
}
```

### 장점과 단점

- 기존 코드 변경없이 새로운 프록시 클래스를 통해서 기능을 추가할 수 있다는 것.
- 디양한 활용 방법을 제공해줄 수 있다.
- 클래스 구조가 복잡해질 수 있다.

### Applicability

- 다양한 추가 기능이 필요한 경우에 Proxy Pattern 을 사용하라.
    - Lazy Initialization 이 필요한 경우
    - Access-control 이 필요한 경우
    - Logging 이 필요한 경우
    - 캐싱이 필요한 경우 등등


### 실제로 사용하는 예

스프링 AOP

- [https://engkimbs.tistory.com/746](https://engkimbs.tistory.com/746)

Reverse Proxy

- [https://www.nginx.com/resources/glossary/reverse-proxy-server/](https://www.nginx.com/resources/glossary/reverse-proxy-server/)

Forward Proxy

- [https://www.ibm.com/docs/en/i/7.2?topic=concepts-proxy-server-types](https://www.ibm.com/docs/en/i/7.2?topic=concepts-proxy-server-types)

Envoy Proxy

- [https://gruuuuu.github.io/cloud/envoy-proxy/](https://gruuuuu.github.io/cloud/envoy-proxy/)