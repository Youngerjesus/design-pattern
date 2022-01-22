# Memento

객체 내부의 상태를 외부에 저장해놓고 이후 필요하면 복원을 하는 방식을 이용하는 패턴이다.

중요한 건 외부에 저장을 하지만 캡슐화를 통해 외부에서 보이도록 하면 안된다. 클라이언트가 이 정보를 알아서 강하게 결합이 되면 안된다.

즉 캡슐화를 유지하면서 객체 내부 상태를 외부에 저장하는 방법이다. 이를 통해 필요할 때 다시 복구할 수 있다.

에디터 예로 보면 이해하기 쉽다. ctrl + z 키를 누르면 undo 연산을 통해서 이를 복원하는게 가능하다.

또 다른 예로 게임을 중지시키고 다시 재개하는게 가능한데 이것도 외부에 상태를 저장해놓고 복원하는 패턴이다.

![memento](./images/memento.png
)

- 이 패턴의 구성요소는 Originator, Memento, Caretaker 이렇게 있다.
- Originator 객체는 기존의 상태를 가지고 있고 Memento 는 Originator 객체의 상태를 스냅샷 형태로 가지고 있다.
- 그리고 Caretakers 는 Memento 의 객체를 가지고 있다가 복구가 필요한 시점에 Originator 에게 줘서 복구시키는 객체이다.
- 주의할 건 Memento 는 **Immutable** 해야한다. 이 상태가 바뀌면 안된다. 그래서 setter 가 있으면 안된다.
- Memento 클래스도 그냥 Originator 의 데이터를 저장할 목적이니, 상속할 필요가 없으니까 final 을 붙이면 된다.

자 그럼 예시로 보자. Memento 패턴을 사용하지 않는 경우 클라이언트 코드 스타일은 어떻게 될까?

```java
@Getter
@Setter
public class Game {
	private int redTeamScore;
	private int blueTeamScore; 
}
```

```java
public class Client {
	public static void main(String[] args) {
		Game game = new Game(); 
		game.setRedTeamScore(10);
		game.setBlueTeamScore(20);
		
		int blueTeamScore = game.getBlueTeamScore();
		int redTeamScore = game.getRedTeamScore();

		// 복구가 필요할 때. 
		Game restoredGame = new Game();
		restoredGame.setBlueTeamScore(blueTeamScore);
		restoredGame.setRedTeamScore(redTeamScore); 		
	}
}
```

- 클라이언트 코드가 Caretaker 역할을 해줘야 할 것이다.

Memento 패턴을 쓰면 이렇게 되지 않을까?

```java
// Memento 역할을 하는 클래스 
@Getter
public final class GameSave {
	private final int redTeamScore; 
	private final int blueTeamScore; 

	public GameSave(int redTeamscore, int blueTeamScore) {
		this.redTeamScore = redTeamScore; 
		this.blueTeamScore = blueTeamScore; 
	}
}
```

```java
@Getter
@Setter
public class Game {
	private int redTeamScore; 
	private int blueTeamScore; 

	public GameSave save() {
		return new GameSave(redTeamScore, blueTeamScore);  
	}

	public void restore(GameSave gameSave) {
		redTeamScore = gameSave.getRedTeamScore();
		blueTeamScore = gameSave.getBlueTeamScore(); 
	}
}
```

```java
public class Client {
	public static void main(String[] args) {
		Game game = new Game(); 
		game.setRedTeamScore(10);
		game.setBlueTeamScore(20);
		
		GameSave save = game.save(); 
		CareTakers.push(save); 		

		game.setRedTeamScore(12);
		game.setBlueTeamScore(22);

		game.restore(CareTakers.pop()); 
	}
}
```

- Memento 역할을 하는 GameSave 객체를 만들었고 값의 불변성을 주기 위해서 setter 와 final 키워드를 붙였다.
- Originator 객체인 Game 은 현재 상태를 저장하기 위해서 save() 라는 메소드를 만들었고, 이후 Memento 객체를 통해 다시 상태를 복원하기 위해서 restore() 이라는 메소드를 만들었다.
- 클라이언트 사이드에서 현재의 Game 상태를 저장하기 위해서 CareTakers 를 통해서 저장하도록 했고 이를 이용해서 불러오도록 했다.

### 장점과 단점

- 객체의 내부 상태를 드러내지 않고 객체 상태의 스냅샷을 저장하고 불러올 수 있도록 할 수 있다. 이를 통해서 클라이언트의 결합도를 많이 줄였다.
    - Game 의 상태는 **추후 게임이 발전함에 따라서 코드의 수정이 많아질 것이다.** Memento 를 적용하지 않았더라면 클라이언트의 코드도 변경될 여지가 많았을 것.
- Memento 객체를 이용한다는 것 자체가 추가적인 메모리 사용을 한다는 것. 이를 염두해두고 코딩을하자. Memento 를 일정량만 가지도록 한다던지.

### Applicability

- 객체의 상태를 저장하고 복구하는 작업이 필요할 때 Memento 패턴을 사용해라.
    - 트랜잭션이 실패해서 rollback 할 때 이 패턴을 사용한다.

### 실제로 사용하는 예

Editor 예제 - CareTaker 를 Stack 으로 구현하는.

[https://medium.com/design-patterns-with-java/understanding-memento-pattern-3abd869a286d](https://medium.com/design-patterns-with-java/understanding-memento-pattern-3abd869a286d)