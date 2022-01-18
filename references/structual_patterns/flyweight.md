# Flyweight 

flyweight 뜻은 가볍다 라는 뜻이다.

주로 인스턴스를 많이 생성하는 어플리케이션에서 메모리의 효율성을 위해 인스턴스를 재사용할 수 있도록 하는 패턴이다.

- 이 패턴을 쓸 땐 자주 변하는 속성과 (extrinsit) , 변하지 않는 속성 (intrinsit) 을 분리해서 재사용할 수 있는 부분을 찾는다.
    - 주의할 점은 자주 변하지 않는 속성은 Immutable 해야한다. 
    - (당연한 소리. 객체를 공유해서 사용하는 것이기 때문에 Immutable 하지 않아서 객체의 값이 예상과 다르다면 문제를 찾기 힘들다.)
- Flyweight Factory 를 통해서 캐싱을 해두고 제공함으로써 재사용하는 방식을 말한다.
- 이걸 보니 자바 성능 튜닝에 있는 Heap Memory Best Practice 도 보면 좋지 않을까? 라는 생각이든다.
    - 메모리 효율성을 제공하는 측면에서 같은 이야기를 하는 것이니.

플라이웨이트 패턴을 사용하지 않는 경우라면 다음과 같이 객체가 필요한 순간마다 new 연산자를 통해서 만들 것이다.

```java
public class Character {
	private char value;
	private String color;
	private String fontFamily;
	private int fontSize; 

	public Character(char value, String color, String fontFamily, int fontSize) {
		this.value = value; 
		this.color = color; 
		this.fontFamily = fontFamily; 
		this.fontSize = fontSize; 
	}
}
```

```java
public static void main(String[] args) {
  // value: h, color: white, font-family: nanum, font-size: 12
	Character c1 = new Character("h", "white", "Nanum", 12);
	Character c2 = new Character("h", "white", "Nanum", 12);
	Character c3 = new Character("h", "white", "Nanum", 12);
	Character c4 = new Character("h", "white", "Nanum", 12);
}
```

플라이웨이트 패턴을 이용하면 다음과 같을 것이다.

```java
public final class Font {
	final String family; 
	final int size; 

	public Font(String family, int size) {
		this.family = family;
		this.size = size; 
	}

	public String getFamily() {
		return this.family;
	}

	public int getSize() {
		return this.size; 
	}
}
```

```java
public class Character {
	char value; 
	String color;
	Font font; 

	public Charactoer(char value, String color, Font font) {
		this.value = value; 
		this.color = color; 
		this.font = font; 
	}
}
```

```java
public class FontFactory {
	Map<String, Font> cache = new HashMap<>(); 

	public Font getFont(String font) {
		if (cache.containsKey(font)) {
			return cache.get(font); 
		}
		
		String[] split = font.split(":"); 
		Font newFont = new Font(split[0], Integer.parseInt(split[1]); 
		cache.put(font, newFont);
		return newFont; 
	}
}
```

- Intrinsic 과 extrinsic 을 구별해서 클래스를 나눴다. 즉 Font 는 변하지 않는 특성이라고 생각해서 이를 Character 와 분리시켰다.
- Intrinsic 특성은 재사용하기 위해서 FontFactory 를 만들었다.

이제 클라이언트 코드는 다음과 같을 것이다.

```java
public static void main(String[] args) {
	FontFactory fontFactory = new FontFactory(); 
	Charcter c1 = new Character('h', "white", fontFactory.getFont("nanum:12");
	Charcter c2 = new Character('h', "white", fontFactory.getFont("nanum:12");
	Charcter c3 = new Character('h', "white", fontFactory.getFont("nanum:12");
	Charcter c4 = new Character('h', "white", fontFactory.getFont("nanum:12");	
}
```

### 장점과 단점

- 애플리케이션에서 사용하는 메모리를 줄일 수 있다.
- 어플리케이션에서 복잡도가 증가한다.

### Applicability

- 내가 가진 램보다 많은 객체의 인스턴스를 만들 것 같다면, 메모리를 효율적으로 사용하고 싶다면 flyweight pattern 을 사용하라.

### 실제로 사용하는 예제

```java
Integer i1 = Integer.valueOf(10); 
Integer i2 = Integer.valueOf(10); 
```

- Integer 에서 valueOf() 메소드는 **자주 사용하므로** 캐싱을 해놓고 사용한다.
    - 캐싱의 범위는 -128 ~ 127 이다.
- 그러므로 캐싱을 뛰어넘는 범위 (예: 10000) 에서의 비교 (예: i1 == i2) 는 false 가 나온다. equals() 메소드를 쓰면 문제없지만.

또 다른 예제

- [https://howtodoinjava.com/design-patterns/structural/flyweight-design-pattern/](https://howtodoinjava.com/design-patterns/structural/flyweight-design-pattern/)
- Flyweight Factory 에서 extrinsic 을 매개변수로 받아서 한번에 객체를 리턴해주는게 위의 예제와는 좀 다른 점이었음.