# Chain of responsibility

책임들이 체인 형태로 이어진 것을 떠올리면 된다.

- 여기서 책임은 SOLID 의 SRP 를 떠오르면 된다. 클래스가 특정한 책임을 가지고 있다. 그리고 이것들이 연결된 구조.
- 요청을 처리하는 쪽과 요청을 보내는 쪽을 구별시키고 (인터페이스 타입에 의존하도록 하면서 = Decoupling) 하나의 클래스가 너무 많은 책임을 가지고 요청을 처리할 때, 책임마다 클래스를 분리시켜서 처리하도록 만드는 패턴이다.

Chain of Reponsibility 를 적용하지 않았다면 다음과 같이 될 것이다.

```java
public class Client {
	public static void main(String[] args) {
		Request request = new Request("messgae"); 
		RequestHandler handler = new RequestHandler(); 
		handler.handle(request); 
	}
} 
```

```java
public class RequestHandler {
	
	public void handle(Request request) {
		...
		// 현재 작업 request 의 body 를 가지고와서 적절하게 처리하는 로직 

		// 미래에는 이 코드에서 인증/인가 작업을 추가하거나 요청이 유효한지 등을 추가해야한다.
	}
}
```

- 현재 RequestHandler 는 단순히 request body 를 가지고와서 요청을 처리하면 되는 로직이지만 미래에 인증과 인가, 요청의 유효함, 로깅 등을 체크한다고 생각해보자.
- 이 경우에는 단순히 RequestHandler 의 handle() 메소드에 추가한다면 코드가 방대해지고 많은 책임을 떠안을 것이다.
- 다른 방법으로 RequestHandler 를 상속한 AuthenticationRequestHandler 같은 걸 추가한다고 하면 SRP 는 지킬 수 있으나 이렇게 되면 클라이언트의 코드가 변경될 여지가 많다.

Chain of reponsibility 를 적용하면 다음과 같이 될 것이다.

```java
public abstract class RequestHandler {
	private RequestHandler nextHandler; 

	public RequestHandler(RequestHandler nextHandler) {
		this.nextHandler = nextHandler; 
	}

	public void handle(Request request) {
		if (nextHandler != null) {
			nextHandler.hanlde(request); 
		}
	}
}
```

```java
public class PrintRequestHandler extends RequestHandler {
	
	public PrintRequestHandler(RequestHanlder nextHandler) {
		super(nextHandler); 
	}

	@Override 
	public void handle(Request request) {
		System.out.println(request.getBody()); 
		super.handle(request); 
	}	
}
```

```java
public class AuthenticationRequestHandler extends RequestHandler {

	public AuthenticationRequestHandler(RequestHandler nextHandler) {
		super(nextHandler); 
	}

	@Override
	public void handle(Request request) {
		// 인증 작업 처리 로직
		... 
		super.handle(request); 
	}
}
```

```java
public class Client {
	private RequestHandler requestHandler; 

	public Client() {
		// 실제로는 Dependency Injection 으로 이뤄질 작업.
		requestHandler = new AuthenticationRequestHandler(new PrintRequestHandler(null))); 
	}

	public static void main(String[] args) {
		Request request = new Request("message"); 
		requestHandler.handle(request);
	}
} 
```

- Chain of reponsibility 에서 처리할 때 유효하지 않다고 판단되면 다음 체인으로 넘기지 않도록 한다.

### 장점과 단점

- 클라이언트의 코드가 변경하지 않고 요청을 처리하는 코드를 확장하는게 가능하다.
- 체인의 순서가 중요한 경우가 있다. 이를 고려해야할 수도 있다.
- 디버깅이 쉽지 않다.

### Applicability

- 우리의 프로그램이 다양한 종류의 요청을 다양한 방식으로 순서대로 처리할 수 있을 때 이 패턴을 사용하라.
    - 요청을 처리하는 하나의 체인부터 시작하고 요청에 따라서 다양한 체인으로 이어질 수 있도록 하는 것. (like 고객센터 문의할 때 숫자 패드 누르는 것.)
    - 런타임 시점에 순서가 바뀔 수 있는 경우.
- 요청을 특정한 순서대로 처리하는게 핵심적일 때 이 패턴을 사용하라.

### 실제로 사용하는 예

```java
Filter filter = new Filter() {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
		chain.doFilter(request, response, chain);
	}
}
```

- Java 에서 ServletFilter 가 Chain of responsibility 패턴을 이용한 경우다.  서블릿 필터는 각 필터마다 자신의 책임을 가지고 요청을 처리한 후 다음 필터에게 넘긴다.

또 다른 예

- employee → Manager
- [https://www.programmingwithwolfgang.com/chain-responsibility-pattern/](https://www.programmingwithwolfgang.com/chain-responsibility-pattern/)