# Bridge

브릿지 패턴은 하나의 계층 구조로 가는게 아니라, 각각 나눠서 독립적인 계층 구조로 만들어서 각각 발전을 시키도록 하는 패턴

- 서로 나눠서 연결시킨다는 뜻.
- 서로 성격이 다른 것들을 나누고 연결한다는 뜻.
    - 성격이 다른 것들이 하나의 클래스에 모이면 겉잡을 수 없이 클래스가 커질 수 있기 때문에 사용한다.

하나의 계층으로 간다면 이런식으로 갈 것이다. (리그오브레전드의 챔피언을 가지고 예시를 보겠다.)

```java
public interface Champion {
	void move(); 

	void skillQ(); 

	void skillW(); 

	void skillE(); 

	void skillR(); 
}
```

```java
public class KDA아리 implements {
	
	@Override
	public void move() {
		... // 아리 + KDA 스킨과 합쳐진 움직임 로직 
	}

	@Override 
	public void skillQ() {
		... // 아리 + KDA 스킨과 합쳐진 스킬 Q  
	}

	@Override
	public void skillW() {
		... // 아리 + KDA 스킨과 합쳐진 스킬 W
	}

	@Override 
	public void skillE() {
		... // 아리 + KDA 스킨과 합쳐진 스킬 E
	}

	@Override
	public void skillR() {
		... // 아리 + KDA 스킨과 합쳐진 스킬 R
	}
}
```

- 이런식으로  Champion 의 추상화된 행동과 스킨과 관련된 로직이 섞여 있게 된다면  클래스가 방대해질 수 있다.
- 만약에 정복자 아리와 같은 다른 스킨이 나왔다고 해보자. 그러면 오버라이딩 메소드에 정복자 아리 스킨과 관련된 로직이 들어갈 것이므로 확장성이 좋지 않다.
- 이렇게 하지말고 스킨과 관련된 부분, Champion 의 행동과 관련된 부분을 나눠서 발전시키는게 더 확장성 측면에서 좋다.

이 경우 브릿지 패턴을 적용해보자.

```java
public class DefaultChampion implements Champion {
	private Skin skin; 
	
	private String name;

	public DefaultChampion(Skin skin, String name) {
		this.skin = skin; 
		this.name = name; 
	}
	...
}
```

```java
public class 아리 extends DefaultChampion {

	public 아리(Skin skin) {
		super(skin, "아리"); 
	}
}
```

- 이렇게 스킨이 할 수 있는 부분을 분리시킴으로써 각각이 다른 계층 구조에 영향을 주지 않고 확장을 할 수 있다.
- (SRP 를 지킬려고 생각하다 보면 자연스럽게 브릿지 패턴을 사용하는거 같긴하다.)

### 장점과 단점

- 추상적인 코드를 구체적인 코드에 의존하지 않고 확장하는게 가능하다. (OCP (Open-closed principle), SRP 를 만족한다.)
- 계층 구조가 늘어나서 복잡도가 증가할 수 있다.

### Applicability

- 하나의 모놀리식 클래스가 기능별로 나눠져야 한다면 브릿지 패턴을 써서 클래스를 나눠라.
    - 하나의 거대한 클래스보다 각각 역할별로 독립적으로 나눠져있는 경우가 더 코드를 변경하기 쉽다.
- 클래스를 여러차원으로 확장해야한다면 브릿지 패턴을 써라.
    - 브릿지패턴을 쓰면 각각의 클래스에게 위임하면 되므로 하나의 게층구조를 고수하지 않아도 된다.
- 런타임 시점동안에 기능이 달라져야 한다면 브릿지 패턴을 써서 해결하는 방법도 있다.
    - 이는 Strategy 패턴과 유사하다. 하지만 푸는 문제가 다르다.

> **[Bridge](https://refactoring.guru/design-patterns/bridge)**, **[State](https://refactoring.guru/design-patterns/state)**, **[Strategy](https://refactoring.guru/design-patterns/strategy)** (and to some degree **[Adapter](https://refactoring.guru/design-patterns/adapter)**) have very similar structures. Indeed, all of these patterns are based on composition, which is delegating work to other objects. However, they all solve different problems. A pattern isn’t just a recipe for structuring your code in a specific way. It can also communicate to other developers the problem the pattern solves.
>

### 실제 예

```java
try (Connection conn = DriveManager.getConnection(url, user, password)) {
	String sql = ~
	Statement statement = conn.createStatement(); 
	statement.execute(sql); 
}
```

```java
// DriverManager.gerConnection 본문 중 일부 
for (DriverInfo aDriver : registeredDrivers) {
    // If the caller does not have permission to load the driver then
    // skip it.
    if (isDriverAllowed(aDriver.driver, callerCL)) {
        try {
            println("    trying " + aDriver.driver.getClass().getName());
            Connection con = aDriver.driver.connect(url, info);
            if (con != null) {
                // Success!
                println("getConnection returning " + aDriver.driver.getClass().getName());
                return (con);
            }
        } catch (SQLException ex) {
            if (reason == null) {
                reason = ex;
            }
        }

    } else {
        println("    skipping: " + aDriver.getClass().getName());
    }

}
```

- DriverManager 와 각 DB 와 연결하는 Driver 는 브릿지 패턴의 관계
- DataSource 와 연결해주는 DriverManager 와 실제 DB 와 connect 해주는 Driver 는 브릿지 패턴 관계다.
    - 하나의 DrvierManager 와 각 Driver 들이 있고 이것들이 연결된 관계.
    - 이로인해 새로운 데이터베이스 Driver 가 나온다고 해서 DriverManager 가 변경되지 않는다.
        - 새로운 DriverManager 를 만들어야 한다거나 그러지 않는다.
        - **즉 새로운 Driver 에 의해서 DriverManager 코드가 달라지지 않는다.**
    - DriverManager 가 내부적으로 가지고 있는 registerDrivers 에 새로운 Driver 를 추가시켜놓으면 된다. (객체를 생성하는 시점에) 그러면 새로운 Driver 도 DB 와 연결되면 Connection 을 리턴해줄 것이다.