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
 - [Proxy](references/structual_patterns/proxy.md)
 - [Flyweight](references/structual_patterns/flyweight.md)
 - [Bridge](references/structual_patterns/bridge.md)
 - [Facade](references/structual_patterns/facade.md)
 
Behavioral patterns

- [Chain of responsibility](references/behavioral_patterns/chain_of_responsibility.md)
- [Command](references/behavioral_patterns/command.md)
- [Iterator](references/behavioral_patterns/iterator.md)
- [Mediator](references/behavioral_patterns/mediator.md)
- [Memento](references/behavioral_patterns/memento.md)
- [Observer](references/behavioral_patterns/observer.md)
- [Strategy](#Strategy)
- [State](#State)
- [Template Method](#Template-Method)
- [Visitor](references/behavioral_patterns/visitor.md)

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
