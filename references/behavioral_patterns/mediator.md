# Mediator

객체간의 의사소통을 추상화 해서 결합도를 낮추기 위해 사용하는 패턴이다.

- 서로 직접적으로 알면 안되는 객체에서 적용할 수 있다.
    - 서로 다른 모듈인 경우에 해당한다.
- 이 패턴에서는 Colleague 는 다른 Colleague 를 직접적으로 참조하지 않고 Mediator 를 통해서 참조한다.
- 층간소음을 해결할 때 Mediator 패턴을 쓴다. (직접 참조가 아니라 경비실 참조를 하니까)

Mediator 패턴을 쓰지 않는 경우는 어떻게 될까?

```java
public class Hotel {
	public static void main(String[] args) {
		Guest guest = new Guest(); 
		guest.getTowel(3);
		guest.dinner(); 

		Restaurant restaurant = new Restaurant(); 
		restaurant.clean(); 
	}
}
```

```java
public class Guest {
	Restaurant restaurant = new Restaurant(); 
	
	CleaningService cleaningService = new CleaningService(); 

	public void dinner() {
		restaurant.dinner(this); 
	}

	public void getTower(int numberOfTower) {
		cleaniningService.getTower(this, numberOfTower); 
	}
}
```

```java
public class Restaurant {
	CleaningService cleaningService = new CleaningService(); 

	public void dinner(Guest guest) {
		// 저녁 제공 로직 
	}

	public void clean() {
		cleaningService.clean(this); 
	}
}
```

- 이 클래스들을 보면 결합이 엄청 많다.
    - Guest 는 서비스를 직접적으로 이용하기 위해서 다양한 객체들을 알아야한다.
    - 그리고 호텔안에 다른 서비스들이 생겨나면 Guest 는 또 다른 객체를 알아야한다.

여기서 Mediator Pattern 을 사용하면 어떻게 될까?

```java
// Mediator 역할을 해주는 클래스 
public class FrontDesk {
	Restaurant restaurant = new Restaurant(); 

	CleaningService cleaningService = new CleaningService(); 

	public void getTower(Guest guest, int numberOfTowers) {
		return cleaningService.getTower(guest.getId(), numberOfTowers); 
	}

	public void dinner(Guest guest) {
		restaurant.dinner(guest.getId()); 
	}
}
```

```java
public class Guest {
	Integer id;

	FrontDesk frontDesk = new FrontDesk(); 

	public void dinner() {
		frontDesk.dinner(this); 
	}

	public void getTower(int numberOfTowers) {
		frontDesk.getTower(this,numberOfTowers); 
	}
}
```

```java
public class Restaurant {
	
	public void dinner(int guestId) {
		// 저녁 제공 로직 
	}
}
```

- 객체안에서 필요한 결합도만 만들고 불필요한 결합도는 모두 지웠다.

### 장점과 단점

- Mediator 를 인터페이스로 사용하는 경우 Colleague 의 코드가 변경되지 않고 확장이 가능하다.
- 각각의 Colleague 의 코드가 참조하는 코드가 적어지므로 간결해진다.
- Mediator 의 코드가 복잡해질 수 있다.  결합도가 높아지므로.

### Applicability

- 클래스들간에 불필요한 결합이 있는 경우에 이 패턴을 써서 결합도를 낮춰라.
- 다른 프로그램이나 클래스에서 동작을 하는데 컴포넌트들의 결합이 너무 많이 필요한 경우 이 패턴을 사용해서 결합을 낮출 수 있다.

### 실제로 사용하는 예

스프링 MVC 에 있는 DispatcherServlet 을 Mediator 로 볼 수 있다.

- DisppatcherServlet 만이 여러개의 컴포넌트를 참조하고 중재해줌으로써 다른 컴포넌트들끼리 서로의 참조를 없앴다. (그래서 더 이해하기 쉽다.)
- Controller 와 View 들, 그리고 Controller 는 Request Context 에 대해서 몰라도 된다.

또 다른 예) 전쟁

- 대장의 지시만 따르면 되는 경우.
- [https://dzone.com/articles/mediator-pattern-1](https://dzone.com/articles/mediator-pattern-1)