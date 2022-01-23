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
- [Strategy](references/behavioral_patterns/strategy.md)
- [State](#State)
- [Template Method](references/behavioral_patterns/template_method.md)
- [Visitor](references/behavioral_patterns/visitor.md)

***

## Behavioral patterns

### State 

객체는 다양한 상태를 객체화하여 객체가 상태에 따라 다른 행동을 할 수 있도록 하는게 가능하다. 

대상은 상태 객체를 참조하는게 가능하고 어떤 조건에 따라 상태를 바꾸는게 가능하고 그에따라 행동 양상이 바뀔 수 있다. 

상태 패턴을 이용하면 상태를 가지고 있는 객체를 상태 조건에 따라 분기해서 실행하지 않아도 되고 그냥 상태를 직접 사용하면 된다. 


Applicability

- 현재 객체가 상태에 따라 다르게 동작해야 하고 상태 수가 많고 상태가 자주 변경될 가능성이 있다면 상태 패턴을 사용해라.

- 클래스가 필드 값 조건에 따라 다르게 행동하고 있도록 설계되었다면 이 패턴을 통해서 개선할 수 있다. 

- 상태 기반으로 검사하는 조건문이 중복되는 경우가 많다면 이 패턴을 사용할 수 있다.