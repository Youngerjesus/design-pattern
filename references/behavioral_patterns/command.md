# Command 

이 패턴도 요청을 처리하는 쪽과 요청을 보내는 쪽의 결합도를 낮추는 유형의 패턴이다.

- 중간에 Command 라는 객체를 통해서 분리시킨다.
- 요청을 캡슐화해서 요청을 호출하는 호출자 (Invoker) 와 수신자 (Receiver) 를 분리하는 패턴.
    - 요청을 처리하는 코드와 클라이언트가 강하게 결합되어 있으면 클라이언트의 요구사항이 바뀌면 변경될 코드가 많아진다. 이 경우 커맨드 라는 요청을 처리해줄 수 있는 캡슐화된 객체에게 클라이언트가 요청을 하게된다면 클라이언트의 입장에선 느슨하게 결합되므로 변경되지 않는다.
    - Command 안에서는 요청을 처리하는데 도움이 되는 것들을 모두 필드로 가지고 있는다.

Command 패턴을 사용하지 않았을 때 생기는 문제는 어떤게 있을까? 예제로 보자.

```java
public interface Button {
	void press(); 
}
```

```java
public class SaveButton implements Button {
	...
	@Override
	public void press() {
		...
		// 저장하는 로직이 있을 것. 
	}
} 
```

```java
public class RevertButton implements Button {
	...
	@Override
	public void press() {
		...
		// 방금 한 작업을 되돌리는 로직. 
	}
}
```

```java
public interface Shortcut {
	void pressKey(); 
}
```

```java
public class SaveShortcut implements Shortcut {
	...
	@Override 
	public void pressKey() {
		...
		// 저장하는 로직으로 SaveButton 과 같을 것. 
	}
}  
```

```java
public class RevertShortcut implements Shortcut {
	...
	@Override
	public void pressKey() {
		...
		// 작업을 되돌리는 로직으로 RevertButton 과 같을 것. 
	}
}
```

- 이렇게 버튼과 단축키마다의 작업들을 SubClass 를 기반으로 나누면 코드의 중복이 생길 수 있다.
    - 여기서 저장하는 로직이 바뀐다고 생각해보면 바껴야 하는 클래스가 많아진다.
- 그리고 버튼마다, 단축키마다 만들어지는 SubClass 들이 너무나 많을 것.
    - SubClass 별로 나누지 않는다면 클래스와 비즈니스 로직간의 결합도가 너무나 높을듯.

Command 패턴을 사용하면 이렇게 된다.

```java
public interface Command {
	void execute(); 
} 
```

```java
public class SaveCommand implements Command {
	...
	@Override 
	public void execute() {
		...
		// 저장하는 로직 
	}
} 
```

```java
public class RevertCommand implements Command {
	...
	@Override
	public void execute() {
		...
		// 작업 되돌리기 로직 
	} 
}
```

```java
public class Button {
	Command command;

	public Button(Command command) {
		this.command = command; 
	}

	public void press() {
		command.execute(); 
	}
}
```

```java
public class Shortcut {
	Command command; 

	public Shortcut(Command command) {
		this.command = command; 
	}

	public void pressKey() {
		command.execute(); 
	}
}
```

- 핵심 로직을 담은 부분을 Command 로 빼내서 결합도를 낮췄다.
    - 기존에는 저장하는 로직이 바뀐다면 Button 쪽과 Shortcut 쪽 코드를 변경해야 하지만 지금은 Invoker 쪽은 코드 변경이 없다. 오로지 Receiver 쪽만 변경하면 된다.
- 여기서는 나오지 않은 부분으로 SaveCommand, RevertCommand 같은 경우는 내부에 필드로 해당 로직을 실행할 수 있는 모든 필드를 다 가지고 있어야한다.

### 장점과 단점

- 클라이언트의 코드를 변경하지 않고 새로운 요청을 처리해주는 커맨드를 만들 수 있다는 것.
- 커맨드 같은 경우는 요청을 처리하고 되돌리는 라이프사이클과 관련된 기능을 추가하기 쉽다.
    - 이를 Stack 과 연결시켜서 사용하는 것도 가능하다. 요청 처리가 필요할 때마다 Command 를 통해 요청을 처리하고 Stack 에 command 를 넣는다. 그리고 undo 가 필요할 땐 스택에서 Command 를 꺼내서 undo 연산을 실행시키는 것.

### Applicability

- 객체의 특정 메소드 작업이 복잡하거나 다양한 작업 할 수 있어야 한다면 이 패턴을 써라.
    - 메소드 아규먼트를 복잡하게 받아서 실행하는 것보다 커맨더 패턴이 더 나을 수 있다.
    - 메소드 operation 이 복잡하거나 다양한 작업을 커버할 수 있어야 한다면 커맨더 패턴으로 결합도를 낮추는게 나을 수 있다.
- 작업들이 큐에 넣어서 실행이 되거나 스케쥴링 되야하는 거라면 이 패턴을 써라.
- reversible operations 이 필요한 경우 이 패턴을 써라.
    - execute(), undo(), redo() 가 하나의 커맨더에 있는게 관리하기 쉽긴 할 듯.

### 실제로 사용하는 예

```java
Runnable runnable = new Ruunable() {
	@Override
	public void run() {
		...
	}
}
```

```java
public class Client {
	public static void main(String[] args) {
		ExecutorService executorService = ExecutorService.newFixedThreadPool(4);
		executorService.submit(runnable1); 
		executorService.submit(runnable2); 
		executorService.submit(runnable3); 
		executorService.submit(runnable4); 
	}
}
```

- Runnable 인터페아스의 run() 메소드를 통해 스레드가 실행되어야 작업을 넣고 이를 ExecutorService 에 전달하기만 하면 실행해준다.