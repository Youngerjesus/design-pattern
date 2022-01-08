# Decorator

기존에 있는 코드를 변경하지 않으면서 부가 기능을 추가할 수 있는 패턴.

- 특징으로는 부가 기능을 런타임 시점에 다이나믹하게 추가할 수 있다는 것.
- 부거적인 기능은 일반적으로 상속을 통해서 해결할려고 하는게 기본적이다. 하지만 상속은 유연하지는 않다. 클라이언트의 요구사항이 런타임 시점에 다양하게 요구할 수 있는 경우에는 Decorator 패턴을 이용해보는 것도 좋다.
- 상속의 한계를 느낄 때, 여러 서브 클래스의 기능을 더한 클래스가 필요할 때 데코레이터 패턴을 이용하면 좋겠다.이렇게 하면 새로운 클래스를 만들지 않아도 될 수도 있다. 기존의 클래스를 조합하는 식으로 사용이 가능하기 떄문에.

기존에는 이메일로만 메시지를 보내면 되었다면 현재시점은 SMS, Facebook, Slack 등 다양한 곳으로 메시지를 보내야하고 각 조합들을 다양하게 조합할 수 있어야 한다고 생각해보자.

Decorator Pattern 을 이용하지 않으면 각 조합마다의 서브 클래스를 생성할 것이다.

```java
public interface Notifier {
	void send(Message message); 
}
```

```java
public class PhoneNotifier implements Notifier {
	... 
	@Override
	public void send(Message message) {
		// do Something
	}
}
```

```java
public class SMSNotifier implements Notifier {
	...
	@Override 
	public void send(Message message) {
		// do Something
	}
}
```

```java
public class FacebookNotifier implements Notifier {
	...
	@Override
	public void send(Message message) {
		// do Something
	}
}
```

```java
public class SlackNotifier implements Notifier {
	...	
	@Override 
	public void send(Message message) {
		// do Something
	}
}
```

```java
public class SMSAndFacebookNotifier implements Notifier {
	...
	@Override
	public void send(Message message) {
		// do Something
	}
}
```

```java
public class FacebookAndSlackNotifier implements Notifier {
	...
	@Override
	public void send(Message message) {
		// do Something 
	}
}
```

- 이런식으로 가능한 조합마다 모든 서브 클래스들을 만들어줘야한다.
- 만들어야 하는 클래스의 수도 많고 코드 중복도 많이 생길 것.
- 클라이언트 코드쪽에서도 다중 if-else 문으로 모든 조합들을 커버해야한다.

데코레이퍼 패턴을 쓰면 이렇게 될 것이다.

```java
public interface Notifier {
	void send(Message message); 
}
```

```java
public class DefaultNotifier implements Notifier {
	...
	@Override 
	public void send(Message message) {
		// do Something
	}
}
```

```java
public class BaseNotifierDecorator implements Notifier {
	Notifier notifier; 

	public BaseNotifierDecorator(Notifier notifier) {
		this.notifier = notifier; 
	}

	@Override
	public void send(Message message) {
		// do Something
	}
}
```

```java
public class SMSNotifierDecorator extends BaseNotifier {
	
	public SMSNotifierDecorator(Notifier notifier) {
		super(notifier); 
	}

	@Override 
	public void send(Message message) {
		super.send(message);
		// do Something
	}
}
```

```java
public class FacebookNotifierDecorator extends BaseNotifier {
	public FacebookNotifierDecorator(Notifier notifier) {
		super(notifier); 
	}

	@Override 
	public void send(Message message) {
		super.send(message);
		// do Something
	}
}
```

```java
public class SlackNotifierDecorator extends BaseNotifier {
	public SlackNotifierDecorator(Notifier notifier) {
		super(notifier); 
	}

	@Override 
	public void send(Message message) {
		super.send(message);
		// do Something
	}
}
```

- 이렇게 데코레이터 패턴을 이용한 경우 감싸는 것만으로 다양한 조합을 할 수 있다.

```java
// Default 
new BaseNotifierDecorator(); 

// Facebook + Default
new FacebookNotifierDecorator(new BaseNotifierDecorator()); 

// Facebook + Slack + Default 
new FacebookNotifierDecorator(new SlackNotifierDecorator(new BaseNotifierDecorator()));)
```

데코레이터 페턴을 쓰면 클라이언트의 코드는 이렇게 될 것이다.

```java
public class Client {

	public static void main(String[] args) {
		...
		Notifier notifier = new BaseNotifierDecorator(new DefaultNotifier()); 

		if (isNeedFacebookNotifier) {
			notifier = new FacebookNotifierDecorator(notifier); 
		}

		if (isNeedSlackNotifier) {
			notifier = new SlackNotifierDecorator(notifier); 
		}

		if (isNeedSMSNotifier) {
			notifier = new SMSNotifierDecorator(notifier); 
		}
		
		notifier.send(message); 
	}
}
```

- spring boot 를 쓰고 있다면 isNeed* 와 같은 플래그 변수는 properties 값에 따라서 설정하면 된다.

### 장점과 단점

- 새로운 클래스를 만들지 않고 기존의 클래스를 조립하는 식으로 기능을 추가할 수 있다.
    - SRP 를 지킬 수 있다.
- 컴파일 타임이 아닌 런타임 시점에 동적으로 기능을 부여하는게 가능하다.
    - 데코레이터 패턴을 사용하지 않는 경우 기능을 부여할 때 모든 경우의 수를 생각해서 해당 기능을 가진 클래스를 줘야한다.
    - 하지만 데코레이터 패턴은 데코레이팅 할 지 여부만 판단하면 되므로 상대적으로 더 간단하다.
- 동적으로 기능을 부연하는 코드가 추가된다는 점이 있다.

### Applicability

- 런타임에 객체를 추가 동작을 할당하는 경우 데코레이터 패턴이 적합하다.
    - 런타임에 로직의 다양한 조합을 사용해서 개체를 구성하는게 가능하다. 이는 다 공통된 인터페이스를 가지고 있으니까 동일한 방식으로 처리할 수 있다.
- 상속을 이용해서 객체의 동작을 상속하는게 어렵거나 어색한 경우에 적합하다.
    - 많은 프로그래밍 언어에서는 추가 상속을 막도록 하는 final 키워드가 존재할 수도 있다.
    - 아니면 두 가지 이상의 클래스 상속이 필요한 경우에도 데코레이터 패턴으로 바꿔서 해결할 수도 있다.

### 실제로 사용하는 예

```java
Collections.synchronizedList(list)

Collections.unmodifiableList(list)
```

- 컬렉션에 있는 여러가지 메소드들은 기존의 리스트에 여러 부가적인 기능을 제공해주는 데코레이터 패턴이 적용된 예이다.

또 이외에 HttpServletRequestWrapper 가 있다. 기본적인 HttpServlerRequest 의 구현체인 ServletRequest 에다가 부가적인 편리한 기능을 추가했다.

- 만약에 HttpServletRequestWrapper 에다가도 부가적인 기능을 더 추가하고 싶다면  HttpServletRequestWrapper 를 인터셉터에서 받아서 새롭게 감싼 Wrapper 를 만들면 된다.