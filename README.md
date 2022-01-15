# Design Pattern

- https://refactoring.guru/design-patterns
- https://www.inflearn.com/course/%EB%94%94%EC%9E%90%EC%9D%B8-%ED%8C%A8%ED%84%B4
 
***

## [목차]
Creational patterns

 - [Factory Method](references/creational_patterns/factory_method.md)
 - [Abstract Factory](references/creational_patterns/abstract_factory.md)
 - [Builder](references/creational_patterns/builder.md)  
 - [Prototype](references/creational_patterns/prototype.md)
 - [Singleton](references/creational_patterns/singleton.md)
    
Structural patterns 
   
 - [Adapter](references/structual_patterns/adapter.md)
 - [Decorator](references/structual_patterns/decorator.md)
 - [Composite](references/structual_patterns/composite.md)
 - [Proxy](#Proxy)
 - [Flyweight](references/structual_patterns/flyweight.md)
 - [Bridge](references/structual_patterns/bridge.md)
 - [Facade](references/structual_patterns/facade.md)
 
  <br/>
  
[Behavioral patterns](#Behavioral-patterns)

- [Strategy](#Strategy)
- [State](#State)
- [Template Method](#Template-Method)
- [Visitor](#Visitor)

***

## Structural patterns

### Proxy 

프록시 패턴은 다른 객체에 대한 대체자를 제공해주고 이 객체에 접근하기 전에 또는 이 객체의 로직 수행 후에 어떠한 작업을 수행하도록 하는 패턴이다. 

주로 사용하는 방식은 어떤 기능을 바로 실행하기 보다 모았다가 일괄적으로 처리하도록 하거나 캐싱을 하는 역할을 하도록 한다. 

또 다른 사용 예는 객체를 생성할 때 시스템 상의 많은 리소스가 필요한 경우 lazy initialization 을 통해 성능상의 이점을 보기 위해서도 사용한다. 
 
Applicability 

- Lazy initialization 을 위해서 프록시 패턴을 사용한다.

  - 어플리케이션이 시작할 때 모든 객체를 초기화 하는 것보다 시스템 리소스를 많이 먹는 무거운 객체가 있다면 Lazy initialization 을 통해 필요할 때 초기화가 되도록 할 수 있다.
  
- Access control 이 필요할 때 프록시 패턴을 사용한다. 

  - 뭐 예를 들면 특정 클라이언트만 이 객체를 사용할 수 있도록 설정한다던지. 이 프록시 패턴에서 credential 이 있는 클라이언트만 서비스를 받을 수 있도록 할 수 있다.
  
- 요청을 전달할 서비스 객체가 다른 서버에 있는 경우 프록시 패턴을 사용한다. 

  - 이 경우 프록시는 네트워크를 통해 클라이언트 요청을 전달화고 네트워크 작업에 대한 모든 처리는 프록시가 한다. 
  
- 어떤 객체의 로직에 추가로 로그를 남기는 작업을 할 때 프록시 패턴을 사용한다. 

- 요청 처리에 캐싱을 적용할 때 프록시 패턴을 사용한다.

- 프록시 패턴을 이용해서 클라이언트를 헬스 체크하도록 하고 클라이언트가 살아있지 않다면 리스트에서 지우도록 하면서 동작하도록 할 수 있다. 

***

## Behavioral patterns

### Strategy

하나의 결과를 만드는 목적(메소드)는 동일하나 그 목적을 달성할 수 있는 전략이 다양한 경우에 사용되는 패턴으로 동적으로 알고리즘을 교체할 수 있다.

그리고 알고리즘 자체는 언제든지 클라이언트의 요구사항에 맞게 교체될 수 있어야 한다. 

주로 이 패턴을 사용할 시점은 알고리즘 자체가 메인인 어플리케이션에서 하나의 클래스의 핵심 동작이 주요 비즈니스를 결정하는데 많은 다른 동작으로 인해 클래스가 비대해지는 문제를 만나는 경우에 이를 적용할 수 있겠다. 
 
즉 확장성을 염두해둔 설계가 아닌 경우 Multiple if/else 로 인해 Large Class 가 되고 이로 인해 이 클래스를 이해하기 어려워지고 Merge Conflict 로 인해 클래스 수정도 어려워지는 상황에서 알고리즘들을 별개의 클래스로 빼서 문제를 해결할 수 있겠다.  

사용할 때 전략 패턴은 원래의 클래스, Context 라고 불리는 클래스가 전략 클래스를 참조하도록 하고 핵심 동작을 전략 클래스로 위임하도록 한다. 

그리고 Context 는 전략 클래스를 직접 선택하는게 아니라 클라이언트의 요구에 맞춰져서 전략이 선택되서 Context 가 만들어져야 한다. 

이 점을 주의하자. 전략 클래스를 직접 선택한다는 건 그만큼 또 많은 역할을 가지는 것이다. 

Applicability 
  
- 전략 패턴은 하나의 객체에서 다양한 알고리즘을 사용해서 문제를 해결할 수 있고 이를 런타임 시점에 결정할 수 있기를 바란다면 사용하는게 좋다. 
  
- 클래스가 유사한 것이 많고 행동하는 방식이 조금씩 다르다면 전략 패턴을 사용하는 것이 좋다. 

  - 전략 패턴으로 각각의 다른 행동을 별개의 클래스로 뽑고 하나의 Original 클래스와 이를 조합하는 식으로 사용할 수 있다. 이로 인해 중복 코드를 제거하는게 가능해진다. 
  
- 이 전략 패턴을 사용함으로써 비즈니스 로직에서 구현의 디테일을 숨길 수 있다. 
  
- 만약 하나의 클래스에서 조건문에 따라 동일한 알고리즘을 조금씩 변형해서 사용한다면 이 패턴을 사용하는 것이 좋다. 

***

### State 

객체는 다양한 상태를 객체화하여 객체가 상태에 따라 다른 행동을 할 수 있도록 하는게 가능하다. 

대상은 상태 객체를 참조하는게 가능하고 어떤 조건에 따라 상태를 바꾸는게 가능하고 그에따라 행동 양상이 바뀔 수 있다. 

상태 패턴을 이용하면 상태를 가지고 있는 객체를 상태 조건에 따라 분기해서 실행하지 않아도 되고 그냥 상태를 직접 사용하면 된다. 


Applicability

- 현재 객체가 상태에 따라 다르게 동작해야 하고 상태 수가 많고 상태가 자주 변경될 가능성이 있다면 상태 패턴을 사용해라.

- 클래스가 필드 값 조건에 따라 다르게 행동하고 있도록 설계되었다면 이 패턴을 통해서 개선할 수 있다. 

- 상태 기반으로 검사하는 조건문이 중복되는 경우가 많다면 이 패턴을 사용할 수 있다. 


***

### Template Method

템플릿 메소드 패턴은 슈퍼 클래스에서 수행해야할 주요 알고리즘의 구조를 짜놓고 서브 클래스에서는 이들의 구현 내용을 채우는 방식의 디자인 패턴이다.

즉 전체적으로는 동일한 구조를 가지면서 부분적으로 조금씩 다르다면 이 패턴을 사용해 코드 중복을 최소화 할 수 있다. (겹치는 부분은 슈퍼 클래스에서 정의하면 되므로)

Applicability

- 전체 알고리즘의 구조는 바뀌진 않지만 각각의 클라이언트가 세부 구현을 채워넣을 수 있도록 하고싶다면 템플릿 메소드 패턴을 사용하라.

  - 템플릿 메소드 패턴을 사용해서 전체의 큰 모놀리식 알고리즘을 각각의 스텝들로 이뤄진 알고리즘으로 바꾸고 그 알고리즘을 서브 클래스에서 정의하도록 해라.
  
- 몇몇의 클래스가 있고 각각의 알고리즘이 약간씩만 차이가 있다면 이 디자인 패턴을 사용하라. 이로인해 변경을 줄일 수 있다. 

  -  유사한 코드를 슈퍼 클래스로 옮겨서 코드 중복을 제거할 수 있고 하위 클래스에서는 달라지는 부분만 채워 넣을 수 있다. 
  
***

### Visitor 

Visitor 패턴을 사용하는 의도는 객체와 객체의 동작에 사용하는 알고리즘을 분리시키는 용도이다. 

알고리즘을 분리시키고 Visitor 라는 구별된 객체를 통해서 동작을 수행하도록 한다.

주로 이런 새로운 동작과 기존 객체의 역할과 크게 맞지 않거나 새로운 동작의 역할이 너무 크고 확장성이 있는 구조일 때 사용한다. 

Visitor 패턴으로 이뤄진 클래스들의 메소드들은 실제 구현객체에 맞게 설계되어 있는 경우가 많아서 Visitor 객체의 메소드에서는 다형성을 이용하지 않는다. 

그래서 실제 구현 객체에 맞는 메소드를 이용해서 실행해야하는데 인터페이스 타입의 객체가 Visitor 객체로 전달되는 경우에는 자신의 구현체를 가지고 실행하는 Visitor 메소드를 실행해야 하는 문제가 생긴다. 

이런 문제를 해결하기 위해서 Visitor 객체은 Double Dispatch 라는 방법을 사용하는데 말 그대로 Dispatch 가 두 번 일어난다.

처음 Visitor 객체에게 인터페이스 타입인 객체가 들어와서 메소드를 실행하는 것이 필요하므로 Dynamic Dispatch 가 실행이 되고 

Dynamic Dispatch 후에 Visitor 객체의 메소드를 실행하기 위해 자신의 구현체를 이용한 메소드가 실행되므로 Static Dispatch 가 실행이 된다. 이렇게 두 번 Dispatch 가 실행되는 방법을 이용한다. 

이런 방식으로 사용되기 떄문에 Visitor 이라는 이름이 붙었는데 실제 구현 객체에 방문해서 자신의 메소드를 실행하는 것이므로 이름이 Visitor 패턴이다. 

Applicability

- 하나의 복잡한 객체가 많은 객체를 가지고 있고 그 객체마다 어떠한 동작을 수행하도록 하는 걸 원한다면 Visitor 패턴을 고려해봐라.

  - 물론 다형성을 이용한 방법도 있겠지만 특정 동작이 요구사항에 따라 변경될 여지가 많다면 Visitor 패턴을 쓰는게 좋을 것 같다. 인터페이스의 메소드를 하나만 추가해도 일일히 모두 구현해야한다. 
  
  - 그리고 여기서는 구현 객체마다 동작하는 방식이 약간씩 다름을 전제하고 있다. 
  
- Visitor 패턴을 이용해서 비즈니스 로직을 정리할 수 있다면 사용해라.

  - Visitor 클래스를 이용해서 핵심 비즈니스 로직만 빼서 정리하는게 가능하다. 
  
- 클래스 계층에서 모든 계층에 필요한 기능이 아니라 특정한 계층에만 필요한 동작이 있다면 Visitor 패턴을 사용하라. 

  - Visitor 패턴을 이용하면 필요한 구현 객체만 가지고 메소드를 만들 수 있다.



