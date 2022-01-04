# Factory Method 

객체의 인스턴스를 생성하는 책임을 인터페이스에 설정하도록 한다. 그리고 실제로 객체를 생성하는 구현체는 하위 클래스에서 설정하도록 하는 것.

- 각각의 하위 클래스에서는 다른 객체를 생성한다.

이런 방식도 있고 인터페이스에서는 객체 생성에 template 을 제공해주고 하위 클래스마다 다른 부분은 추상 메소드로 선언해 놓는 방식도 있다.

```java
public interface ShipFactory {
	default Ship orderShip(String name, ShipType shipType) {
		validate(name, shipType);
		prepareFor(name); 
		Ship ship = createShip(name, shipType); 
		return ship; 
	}

	public void validate(String name, ShipType shipType); 

	public void prepareFor(String name); 
}
```

이 디자인 패턴을 안쓰는 상황에서는 어떻게 될까?

```java
// 클라이언트의 코드는 다음과 같다. 
ShipFactory.orderShip(shipName, ShipType.WHITE_SHIP)
ShipFactory.orderShip(shipName, ShipType.BLACK_SHIP)
```

```java
public class ShipFactory {
	public static Ship orderShip(String shipName, ShipType shipType) {
		if (shipType == ShipType.WHITE_SHIP) {
			...
			ship = new WhiteShip(); 	
		}
		else if (shipType == ShipType.BLACK_SHIP) {
			...
			ship = new BlackShip(); 
		}
	}
}
```
- 객체를 생성하는 코드가 지저분해진다. 여러 객체를 참조하며 각 객체에 맞는 세팅을 해줄려고. 

### 장점과 단점

- 팩토리 메소드를 사용하면 객체인 Product 와 객체를 생성하는 Creator 의 관계를 느슨하게 가져갈 수 있다.
  - 기존에는 하나의 Creator 가 여러 Product 를 참조해서 그에 맞는 객체를 생성하는 방식이라서 결합도가 높았다. 그리고 이로인해 객체 생성 코드가 복잡하다.
- 클래스가 많아져서 복잡해 보일수도 있다는 단점이 있다.

### Applicability

- 사전에 만들어야 할 객체의 정확한 타입이나 의존성을 알기 어려운 경우에 팩토리 메소드를 통해서 이후에 서브 클래스를 통해 생성을 하도록 만들 수 있다.

  - 팩토리 메소드 패턴을 적용하면 객체 생성 코드를 분리시킬 수 있어서 확장시키기 쉽다. 즉 미래에 추가되는 타입이 있는 경우에 확장 시키기 쉽다.

- 라이브러리나 프레임워크를 사용자에게 제공하고 사용자가 이를 확장해서 사용할 수 있도록 할려면 팩토리 메소드 패턴을 사용하라.

  - 팩토리 인터페이스를 상속하고 팩토리 메소드를 오버라이딩 해서 확장성을 더할 수 있다.
  
- 매번 기존 객체를 다시 빌드하지 않고 객체를 재사용해서 리소스를 절약하고 싶다면 팩토리 메소드 패턴을 사용해라 (Factory method + flyweight 패턴)

  - 리소스에 민감한 객체를 다루는 경우 (데이버테이스 커넥션, 네트워크 리소스 등) 미리 여러개 만들어놓고 재사용하는게 이점이 많다. 

### Real Practice 

Food Service: https://zorba91.tistory.com/306
