# Prototype

기존에 있는 인스턴스를 프로토타입으로 사용해서 객체를 복사하는 것

- 복사해서 일부 값만 변경해서 쓰는게 더 효율적인 경우에 쓴다. 새로 만들어서 다 세팅하는 것보다
- 기본적으로 Shallow copy 를 쓰기 때문에 더 효율적인듯. 변경될 부분만 Deep Copy 로 하고.
- 그 다음에 코드 적으로도 프로토타입 패턴을 쓰는게 더 효율적이다. 프로토타입 패턴을 쓰지 않는다면 new Operator 로 객체를 만들고 필요한 값을 setX() 메소드로 세팅해주는 작업이 필요하다.

객체 생성의 동일한 작업을 반복하지 않아도 된다.

- 리소스 사용이 특히 심한 경우. (객체 생성의 비용이 비싸거나, 네트워크를 타야하거나 (DB 등))
    - 복사는 Shallow copy, Deep copy 가 있는데 java 의 clone() 메소드는 Shallow Copy 를 지원한다.
    - Shallow copy 는 기존에 객체가 참조하고 있던 레퍼런스를 그대로 가지고 오는 걸 말하며 Deep copy 는 아예 새로운 복사를 말한다. (java 의 프로토타입핑을 지원하는 clone() 메소드는 Shallow copy 를 지원하며, 이 값을 바꾸면 원본에도 영향이 간다. 대신에 객체 생성의 비용을 줄일 수 있다.)
    - Deep copy 를 지원하고 싶다면 clone 메소드를 오버라이딩 하면 된다.

### 패턴 적용하기

- 자바에서는 clone() 메소드를 바로 객체에서 사용할 수 없다. 퍼블릭 메소드가 아니라서

### 장점과 단점

- 복잡한 객체를 만드는 과정을 숨길 수 있다.
- 기존 객체를 새로 생성해서 복사하는 것보다 비용이 저렴할 수 있다.
- 추상적인 타입을 리턴하는 것도 가능하다.
- Clone() 할 때 Shallow Copy 를 쓸 때 Deep Copy 를 쓸 지 이해하고 작업해야한다.

### Applicability

- 객체를 복사할 때 구체적인 타입에 의존하지 않고 인터페이스 타입에 의존하고 싶은 경우에 사용할 수 있다.
- 프로토타입 패턴을 사용하면 다양한 방식으로 구성된 사전 빌드된 객체 집합을 프로토타입으로 사용할 수 있다.
  - 클라이언트는 일부 구성과 일치하는 하위 클래스를 인스턴스화하는 대신 적절한 프로토타입을 찾아 복제할 수 있다.

### 실제로 사용하는 예

- [https://stackoverflow.com/questions/21969044/when-to-use-spring-prototype-scope](https://stackoverflow.com/questions/21969044/when-to-use-spring-prototype-scope)

### Opinion 1)

As the documentation says, creating a bean Foo with prototype scope is same as calling:

```java
Foo foo = new Foo(dependency1, dependency2, ...);
foo.initialize(dependency7, dependency8...);

```

The only good reason to use a prototype scope bean instead of `new` that is when the dependencies used for creation and initialization of the instance should be kept outside the code that needs a new instance.

As an example:

```java
// need to explicitly mention dependencies here
public void createdWithNew(Dependency dependency1, Dependency dependency2) {
  Foo foo = new Foo(dependency1, dependency2, ...);
  foo.doSomething();
}

// Dependencies managed in class Foo by Spring
public void createdWithSpring(Foo foo) {
  foo.doSomething();
}

```

### Opinion 2) 

There are some interesting use cases by using scope prototype you will build a better and reliable application design/architecture, for example, a real-time system.

Imagine that you must build a real-time system for vehicle tracking, and you will have 2.000.000 cars sharing information every 5 seconds, In the server side, you will work with two or more distinct group of configurations, one for Cars and another one for Trucks.

Based on this simple example, if you design your application to work with distinct configuration groups in memory through the prototype pattern you will achieve a better performance.

So, in this case, whenever the server receives a new message from a Truck, for example, the server will get the instance of the configuration in memory from a hashmap of instances of VehicleGrupConfiguration and then apply the configuration behavior that this message must have, e.g: like time-out, retry... and etc.

I would like to highlight that there are many ways to implement this situation, but this example shows that a prototype pattern is very powerful in matters of performance and design patterns.

- Prototype Registry 에서 미리 만들어 놓고 요청이 오면 요청 정보를 보고 적절한 프로토타입 객체를 찾아서 복사해서 전달해 주는 방식으로 메모리를 좀 더 효율적으로 쓰는게 가능.  