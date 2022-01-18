# Iterator

집합 객체를 순회할 때 안에 있는 내부 구조를 드러내지 않고 순회하는 패턴을 말한다.

- 즉 OCP 를 지원해줄 수 있고 결합도를 낮출 수 있다.
- 프로그래밍 언어를 사용하는 개발자라면 익숙할 것.

Iterator 패턴을 안쓰는 경우는 어떻게 될까? 바로 예시로 보자.

```java
public class Client {
	public static void main(String[] args) {
		// 기본 세팅: 원래는 Repository 에서 가지고 오겠지. 
		Board board = new Board(); 
		board.addPost("디자인 패턴 리뷰"); 
		board.addPost("리팩터링 리뷰"); 
		board.addPost("스프링 AOP 리뷰"); 
		
		// 처음 요구사항 
		List<Post> posts = board.getPosts(); 
		for (Post post : posts) {
			System.out.println(post.getTitle()); 
		}

		// 변경된 요구사항: 정렬해서 출력하자
		Collections.sort(posts, (p1,p2) -> p2.getCreatedDateTime().compareTo(p1.getCreatedDateTime()));
		for (Post post : posts) {
			System.out.println(post.getTitle()); 
		}
	}
} 
```

- 클라이언트 코드는 board 의 내부 요소들을 알고있다.
    - Board 안에 Post 가 있고 이것들을 List 형식으로 가지고온다는 것.
- 이 경우에 변경될 여지는 많다.
    - List 가 아니라 다른 자료구조를 사용하는 경우.

Iterator 패턴을 사용하면 다음과 같이 될 것이다.

```java
public class Client {
	public static void main(String[] args) {
		Board board = new Board(); 
		board.addPost("디자인 패턴 리뷰"); 
		board.addPost("리팩터링 리뷰"); 
		board.addPost("스프링 AOP 리뷰"); 

		Iterator<Post> iter = board.getDefaultIterator();  
		while (iter.hasNext()) {
			System.out.println(iter.next().getTitle()); 
		}
	}
}
```

```java
public class Board {
	...
	// 처음 요구사항 
	public Iterator<Post> getDefaultIterator() {
		return posts.iterator(); 
	}
} 
```

```java
public class Board {
	...
	// 변경된 요구사항 
	public Iterator<Post> getRecentIterator() {
		return new RecentPostIterator(this); 
	}
}
```

```java
public class RecentPostIterator implements Iterator<Post> {
	Iterator<Post> internalIterator; 

	public RecentPostIterator(Board board) {
		List<Post> posts = board.getPost(); 
		Collections.sort(posts, (p1,p2) -> p2.getCreatedDateTime().compareTo(p1.getCreatedDateTime()));
		internalIterator = posts.getIterator(); 	
	}

	@Override 
	public boolean hasNext() {
		return internalIterator.hasNext(); 
	}

	@Override
	public Post next() {
		return internalIterator.next(); 
	}
}

```

- 순회하는 자료구조가 변경된다고 해도 순회를 하는 클라이언트 측의 코드는 변경되지 않는다.
- 여기서 Board 가 인터페이스까지 있고 Override 메소드 중 iterator 를 리턴하는게 있다면 더욱 확장에 열려있을 것.


### 장점과 단점 

- 집합 객체가 어떠한 구조인지 몰라도 된다. Iterator 만으로 순회가 가능하다.
- 클래스 구조가 복잡해진다.

### Applicability 

- 집합 객체가 복잡하고 변경될 가능성이 있을 때 이 패턴을 사용하라.
  - 사전에 어떤 자료구조를 쓸 지 알지 못하는 경우도 쓰면 될 듯.
- 순회하는 로직을 분리하고 싶다면 이 패턴을 사용하라.
  - 앱의 비즈니스 로직과 순회하는 로직이 같이 있고 순회하는 로직이 제법 복잡핟면 이를 분리하는게 더 나을듯. 

### 실제로 사용하는 예

실제로 자바에서는 Iterator 를 제공해준다.

```java

public interface Iterator<E> {

    boolean hasNext();

    E next();

    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
```

- forEachRemaining 을 통해서 순회에 편의성을 줄 수 있다.
    - 이전에는 while 문을 통해서 hasNext(), next() 를 통해 직접 순회했지만 그러지 않아도 된다.
    - 사용할 때 매개변수에 Consumer 를 받는다는 것만 알자.