# Adapter

- 실생활 예) 110V ↔ 220V 로 꼽을 때 즉 변환이 필요한 경우에 사용하는게 어댑터
- 클라이언트가 사용하는 인터페이스가 다를 때 원하는 인터페잇로 변환시키는 것.
- 자세하게 설명하자면 클라이언트가 사용하는 인터페이스는 이미 만들어져있고, 내가 개발한 인터페이스도 이미 정해져있다. 이렇게 확정이 나있는 상황에서 클라이언트가 사용하고 있는 인터페이스로 변환해주고 싶을 때 사용한다.
    - 기존 코드의 수정없이 변환할 수 있도록 해주는 것.

예시로보자.

스프링 시큐리티에서는 UserDetails 와 UserDetailsService 를 이용해서 인증을 수행한다.

```java
public interface UserDetails {
	String getUsername(); 
	String getPassword(); 	
}
```

```java
public interface UserDetailsService {
	UserDetails loadUser(String username); 
}
```

그리고 실제 로그인 처리는 loginHandler 에서 일어난다.

```java
public class LoginHandler {
	UserDetailsService userDetailsService; 

	public LoginHandler(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService; 
	}	

	public String login(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUser(username); 
		if (userDetails.getPassword().equals(password)) {
			return userDetails.getUsername(); 
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}
```

스프링 시큐리티는 UserDetails 라는 형식(?) 을 통해서 인증을 수행하도록 프로세스를 정의해놨지만 우리 서비스에서의 인증 객체는 이것과 다르다.

이건 서비스마다 다를 수 있다. 이걸 어떻게 해결할까?

다음은 우리 서비스에서 정의한 유저 객체와 서비스이다.

```java
@Data
public class Account {
	private Long id; 
	private String username;
	private String email; 
}
```

```java
@Service 
public class AccountService {
	AccountRepository accountRepository; 

	public Account findAccountByUsername(String username) {
		return accountRepository.findByUsername(username); 
	}
}
```

이 경우 해결 방법은 스프링 시큐리티의 타입으로 변환해주는 작업이 필요하다.

- Account → AccountUserDetails
- AccountService → AccountUserDetailsService

```java
public class AccountUserDetails implements UserDetails {

	Account account;

	public AccountUserDetails(Account account) {
		this.account = account; 
	}

	@Override
	public String getUsername() {
		return this.account.getUsername(); 
	}

	@Override
	public String getPassword() {
		return this.account.getPassword(); 
	}
}

```

```java
@Service 
public class AccountUserDetailsService implements UserDetailsService {

	@Autowired AccountService accountService; 

	@Override
	public UserDetails loadUser(String username) {
		Account account = accountService.findAccountByUsername(username); 
		return new AccountUserDetails(account); 
	}
}
```

### 장점과 단점

- 기존 클래스를 수정하지 않고 원하는 인터페이스로 만들 수 있다. 즉 기존 클래스가 하던 일은 하던 일대로 하고 변환하는 작업만 새로 만드는 것. (OCP 를 지킬 수 있다.)
- 새 클래스가 생겨서 복잡도가 증가할 수 있다. 경우에 따라서는 어댑터를 만드는 게 아니라 기존의 클래스가 클라이언트의 인터페이스에 맞추도록 변경하는게 더 나을수 있다.

### Applicability

- 기존의 존재하는 클래스를 이용해야하지만 인터페이스가 호환가능하지 않을때 어댑터 패턴을 사용하라.
    - Adapter Pattern 은 중간 계층을 하나 더 만드는 것.
- 기존의 기능이 부족한 서브 클래스를 재사용 하고싶지만 기능이 부족할 때, 이 부족한 기능을 슈퍼 클래스에 넣기 힘들때 어댑터 패턴을 사용하면 해결할 수 있다.
    - 물론 서브 클래스의 자식 클래스를 또 만들어서 해결할 수도 있다. 대신 이 경우는 코드 중복이 발생할 확률이 있음.
    - 데코레이터 패턴, 프록시 패턴과 유사한 방법이긴 함.

### 실제 예

spring-mvc 에 있는 HandlerAdapter

- 스프링에서는 요청을 처리해줄 수 있는 다양한 핸들러를 만들 수 있다.
- 그 중 가장 기본적으로 사용하는게 @Controller 를 이용해서 처리하도록 만드는 것.
- 이렇게 핸들러가 다양하다면 생길 수 있는데 각각의 핸들러의 반환 타입도 다 다르다는 문제가 생긴다.
- 그래서 HandlerAdapter 를 이용해서 각각의 핸들러를 실행하고 모두 반환 타입을 ModelAndView 로 맞추도록 했다.

```java
public interface HanlderAdatper {
	...

	@Nullable
	ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}
```