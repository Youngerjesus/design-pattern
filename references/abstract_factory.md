# Abstract Factory 

서로 관련있는 여러 객체를 만들어주는 인터페이스를 제공해주는 패턴이다.

서로 관련있는 여러 객체를 구현 객체를 기반으로 생성하면 변경에 닫혀있지 않다. 그래서 그 부분을 인터페이스로 만들어서 변경에 열려있도록 만드는 패턴이다.

추상 팩토리 패턴을 사용하지 않는다면 어떨까?

```java
public class WhiteShipFactory implements ShipFactory {
	
	@Override
	public Ship createShip() {
		Ship ship = new Ship(); 
		ship.setWheel(new WhiteWheel()); 
		ship.setAnchor(new WhiteAnchore()); 
		return ship; 
	}
} 
```

- 하나의 ship 을 생성하기 위해서 ship 과 연관된 객체를 생성해줘야 하는데 이 연관된 객체가 언제든지 확장이 가능하다.

추상 팩토리 패턴을 적용하면 다음과 같다.

```java
public interface ShipPartsFacotry {
	Wheel createWheel(); 
	Anchor createAnchor(); 
}
```

```java
public class WhiteShipPartsFactory implements ShipPartsFactory {
	@Override
	public Wheel createWheel() {
		return new WhiteWheel(); 
	}

	@Override
	public Anchor createAnchor() {
		return new Anchor(); 
	}
}
```

```java
public class WhiteShipFactory implements ShipFactory {
	ShipPartsFactory shipPartsFactory; 

	public WhiteShipFactory(ShipPartsFactory shipPartsFactory){
		this.shipPartsFactory = shipPartsFactory; 
	}

	public Ship createShip() {
		Ship ship = new Ship(); 
		ship.setWheel(shipPartsFactory.createWheel()); 
		ship.setAnchor(shipPartsFactory.createAnchor()); 
		return ship; 
	}
}
```

- 이렇게 하면 Ship 을 만드는 구성품이 달라지는 경우 WhiteShipPartsFactory → WhiteProShipPartsFactory 로 바껴도 Ship 을 만드는 코드는 전혀 달라지지 않는다. 확장에는 열려있고 변경에는 닫혀있다.

### 추상 팩토리 패턴 vs 팩토리 메소드 패턴

- 추상 팩토리 패턴은 연관된 객체를 생성하는 팩토리 클래스를 만들어서 클라이언트가 변경없이 사용할려는 목적에 좀 더 초점을 맞췄다면
- 팩토리 메소드 패턴은 서브 클래스에서 객체를 생성하는 과정에 좀 더 초점을 맞췄다.

### 장점과 단점

- 추상 팩토리 패턴을 통해서 만들어진 객체는 서로 양립이 가능하다는 정보를 제공해준다.
- OCP 를 지원해준다.
- 느슨한 결합을 지원해준다. (Ship 을 생성하는 ShipFactory 에서는 Ship 과 연관된 객체인 Wheel 과 Anchor 과 결합하지 않고 그 객체들을 생성해주는 ShipPartsFactory 와 결합하기 떄문에)

### Applicability

- 추상 팩토리 패턴은 다양한 종류의 객체 집합을 생성하면서 이런 객체에 미래에 확장을 신경쓰고 싶은 경우에 사용해라.
    - 팩토리 메소드 패턴을 집합 형태로 가지고 있는 클래스가 있다면 추상 팩토리 패턴을 고려해봐라.
    - 여러 객체들 중 조건에 따라 객체가 만들어지고 각 객체는 서로 대체 가능할 때 이 패턴을 쓰는 것을 고려해보면 좋다.


### 실제로 사용되는 예

- Pizza: [https://springframework.guru/gang-of-four-design-patterns/abstract-factory-design-pattern/](https://springframework.guru/gang-of-four-design-patterns/abstract-factory-design-pattern/)
