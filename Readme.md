# Design Pattern

https://refactoring.guru/design-patterns

***

## [목차]
[Creational patterns](#Creational-patterns) <br/>
 - [Factory Method](#Factory-Method)
 - [Abstract Factory](#Abstract-Factory)
 - [Builder](#Builder)  
 - [Prototype](#Prototype)
  
  <br/>
  
[Structural patterns](#Structural-patterns) <br/> 
   
 - [Adapter](#Adapter)
 - [Decorator](#Decorator)
 - [Composite](#Composite)
 - [Proxy](#Proxy)
 - [Flyweight](#Flyweight)
 - [Bridge](#Bridge)
 
  <br/>
  
[Behavioral patterns](#Behavioral-patterns)

- [Strategy](#Strategy)
- [State](#State)
- [Template Method](#Template-Method)

***

## Creational patterns

### Factory Method 

객체를 생성하기 위해 인터페이스를 정의하고 서브클래스에서 어떤 인스턴스를 생성할지 결정하는 방법

`new` operator 를 통해 객체를 생성하지 않는다. 객체 생성에 다형성을 부여하고 싶은 경우에 Factory Method 를 쓰면 된다. 

뭐 예를들면 운송수단(Transport)을 통해서 배송을 하는데 `ship` 을 통해서 해야하는지 `truck` 을 통해서 해야하는지 결정 지어줘야할 때. 

Applicability 

- 사전에 만들어야 할 객체의 정확한 타입이나 의존성을 알기 어려운 경우에 팩토리 메소드를 통해서 이후에 서브 클래스를 통해 생성을 하도록 만들 수 있다. 

  - 팩토리 메소드 패턴을 적용하면 객체 생성 코드를 분리시킬 수 있어서 확장시키기 쉽다. 즉 미래에 추가되는 타입이 있는 경우에 확장 시키기 쉽다.   
   
-  라이브러리나 프레임워크를 사용자에게 제공하고 사용자가 이를 확장해서 사용할 수 있도록 할려면 팩토리 메소드 패턴을 사용하라. 

  - 추상 팩토리 클래스를 상속하고 팩토리 메소드를 오버라이딩 해서 이를 해결해줄 수 있다. 사용자가 정의한 팩토리 클래스를 정의하고 그 팩토리 클래스를 표준으로 사용하도록 하고 (프레임워크 단에서 Single Factory Method 으로 해놓는 방법이 있다.) 
    
    타입에 맞는 객체를 반환해주기만 하면 된다.   
    
- 매번 기존 객체를 다시 빌드하지 않고 객체를 재사용해서 리소스를 절약하고 싶다면 팩토리 메소드 패턴을 사용해라 

  - 리소스에 민감한 객체를 다루는 경우 (데이버테이스 커넥션, 네트워크 리소스 등) 미리 여러개 만들어놓고 재사용하는게 이점이 많다. 

***

### Abstract Factory

추상 팩토리 패턴은 구체적인 클래스를 지정하지 않고 객체의 집합을 생성할 수 있는 디자인 패턴이다.  

주로 추상 팩토리 패턴은 팩토리 메소드 패턴이랑 비교되는데 차이점은 명확하다. 

- 팩토피 메소드 패턴

  - 조건에 따른 객체 생성을 팩토리 클래스에 위임한다. 팩토리 클래스에서 객체를 생성한다.
  
- 추상 팩토리 패턴

  - 서로 관련이 있는 객체를 통째로 묶어서 팩토리 클래스로 만들고 이 팩토리를 조건에 따라 생성하고 팩토리가 객체를 생성하는 패턴
  
추상 팩토리 패턴은 팩토리 메소드 패턴을 좀 더 캡슐화한 방식이라고 생각하면 된다. 

Applicability 

- 추상 팩토리 패턴은 다양한 종류의 객체 집합을 생성하면서 이런 객체의 구체적인 클래스는 필요 없는 경우 (미래에 확장을 신경쓰고 싶은 경우)에 사용해라.

  - 팩토리 메소드 패턴을 집합 형태로 가지고 있는 클래스가 있다면 추상 팩토리 패턴을 고려해봐라.
  
  - 여러 객체들 중 조건에 따라 객체가 만들어지고 각 객체는 서로 대체 가능할 때 이 패턴을 쓰는 것을 고려해보면 좋다. 
  
- 객체가 생성되는 방식이 시스템과 독립적으로 만들고자 할 때 이 패턴을 사용하라 
***

### Builder

빌더 패턴을 이용해서 복잡한 Constructor 를 한단계 한단계씩 순서와 상관없이 값을 넣음으로써 객체를 만들 수 있다. 

복잡한 객체를 생성할 때 생성자에 많은 변수를 넣어서 생성하고 클래스 자체가 생성에 관해서 많은 변경사항이 있을 수 있다. 이를 분리시키는 목적으로 빌더 패턴들 이용하는 것. 

빌더를 통해서 객체 생성에 필요한 값을 넣을 수 있도록 인터페이스를 정의하고 각각의 메소드를 직접 호출해서 객체를 생성할 수 있지만  

Director 를 이용해서 실제적으로 생성에 필요한 값을 순서대로 넣어주도록 해줄 수 있다. 그리고 Builder 클래스의 build() 메소드를 통해서 실제 객체를 생성한다. 
 
Applicability

- 생성자 매개변수가 많은 경우에 생성자 오버로딩을 통해서 생성자를 정의하는 것보다 빌더 패턴으로 step by step 으로 생성하는 게 더 편하다고 느끼는 경우에 빌더 패턴을 이용해라. 

- 같은 Product 를 약간씩 다르게 표현해야 한다면 빌더 패턴을 이용해서 객체를 생성해라 

  - 빌더패턴을 이용해서 약간씩 다른 다양한 생성자들을 정의하고 Director 를 이용해서 약간씩 차이나는 부분에 대해서 값을 넣어주도록 하면 된다. 
  
- Composite 패턴의 객체를 생성하거나 다른 복잡한 객체를 생성할 때 빌더 패턴을 사용하라.

  - 빌더 패턴을 이용하면 단계별로 객체를 생성하고 일부 단계의 실행을 잠시 미룰수도 있기 떄문에 복잡한 객체를 만드는데 적합하다. 그리고 빌더 패턴을 이용하면 완성된 시점에 객체를 반환할 수 있기 때문에 클라이언트가 불완전한 객체를 쓰도록 하지 않을 수 있다. 
  
***

### Prototype

새로운 객체를 복사하고 싶다면 new 키워드를 통해서 객체를 생성하고 객체의 모든 필드를 복사해야 하는 번거로움이 있다. 

게다가 필드가 private 하다면 복사하기도 어렵다는 단점도 있다. 

하지만 Prototype 패턴을 이용하면 객체와 결합해서 복사하지 않고 clone 이라는 메소드를 정의해서 복사할 수 있도록 할 수 있다. 

Applicability 

= 프로토타입 패턴을 사용하면 다양한 방식으로 구성된 사전 빌드된 객체 집합을 프로토타입으로 사용할 수 있다. 
  
  - 클라이언트는 일부 구성과 일치하는 하위 클래스를 인스턴스화하는 대신 적절한 프로토타입을 찾아 복제할 수 있다. 


***

## Structural patterns

### Adapter 

어댑터 패턴은 호환되지 않는 인터페이스를 가진 두 객체가 서로 협력할 수 있도록 하는 패턴이다. 

뭐 예를들면 주식 앱에서 데이터를 XML 형식으로 다운받았지만 데이터 분석 라이브러리는 JSON 형식이 필요한 경우다. 이런 경우에 어댑터 페턴을 사용할 수 있다.

Applicability 

- 기존의 존재하는 클래스를 이용하지만 호환가능하지 않을때 어댑터 패턴을 사용하라. 

  - 이런 방식의 사용은 중간 계층의 레이어를 통해 변환해주는 역할을 어댑터가 한다고 생각하면 된다.
  
- 기존의 기능이 부족한 서브 클래스를 재사용 하고싶고 공통적인 기능을 슈퍼 클래스에 넣기 힘들때 어댑터 패턴을 사용하면 해결할 수 있다.

  - 슈퍼 클래스에 기능을 넣기 어렵고 서브 클래스에 기능을 추가해 확장해서 사용하기에는 코드가 중복되고 지저분한 경우에 이런 어댑터 패턴을 사용한다. 
  
  - 어댑터 패턴을 이용해 객체를 감싸고 공통 인터페이스를 통해 공통된 기능을 추가한다. 이런 패턴은 런타임 시점에 동적으로 결정이 가능하며 데코레이터 패턴과 유사하디.   

***

### Decorator

데코레이터 패턴은 객체를 감싼 새로운 객체를 통해서 기존 객체의 새로운 동작을 부여하는 방식이다.

이를 통해 객체는 동적으로 새로운 책임을 추가할 수 있다. 기능을 추가하는 면에서 서브 클래스를 생성하는 것보다 더 융통성이 있다.

가끔 전체 클래스에 새로운 기능을 추가할 필요는 없지만 기존의 몇 클래스에는 새로운 책임을 부여할 필요가 있다. 

뭐 예를들면 GUI 툴킷에서 모든 사용자 인터페이스에서는 스크롤링이 필요하지 않지만 몇몇에는 필요하던가. 

이렇게 기존의 기능에 새로운 기능이 필요한 경우에는 상속을 이용하는게 일반적이다. 상속을 통해서 새로운 속성을 부여받는게 일반적이지만 

이런 상속 클래스를 미리 만들어놔야 한다는 정적인 특성이 한계가 있다. 데코레이터 패턴을 이용하면 다른 객체를 계속 중첩해서 둘러싸는게 가능하기 떄문에 

동적으로 이를 제어하는게 가능하고 추상화를 해서 제공하는 겐 가능하다. 

Applicability

- 런타임에 객체를 추가 동작을 할당하는 경우 데코레이터 패턴이 적합하다. 

  - 런타임에 로직의 다양한 조합을 사용해서 개체를 구성하는게 가능하다. 이는 다 공통된 인터페이스를 가지고 있으니까 동일한 방식으로 처리할 수 있다.
  
- 상속을 이용해서 객체의 동작을 상속하는게 어렵거나 어색한 경우에 적합하다. 
  
  - 많은 프로그래밍 언어에서는 추가 상속을 막도록 하는 final 키워드가 존재할 수도 있다. 
 
***

### Composite

컴포지트 패턴은 트리 구조로 작성된 복합 객체를 단일 객체로 취급할 수 있는 것을 목적으로 한다. 

뭐 예를 들면 박스안에 Product 가 여러개 있을 수 있고 박스안에 박스가 있을 수 있다.   

컴포지트 패턴을 사용하는 클라이언트느 복합 객체인지 단일 객체인지 구별하지 않고 동일한 인터페이스를 통해서 사용하는 것이 가능하다. 

Applicability 

- 너가 구현하는 객체 구조가 트리와 같다면 컴포지트 패턴을 사용해라 

  - 컴포지트 패턴은 동일한 인터페이스를 제공해줘서 하나의 싱글 객체에서 수행할 수 있고 복합 객체에서도 동일한 메소드로 여러개의 싱글 객체들을 실행할 수 있다. 
  
  - 복합 객체는 또 다른 복합 객체를 가지고 있을 수도 있고 싱글 객체를 가지고 있을 수 있다. (트리 구조와 유사하다.)
  
- 하나의 객체를 실행하던 복합 객체를 실행하던 일관성 있게 처리하도록 하고 싶다면 컴포지트 패턴을 사용하라. 

  - 계속 말하지만 컴포지트 패턴은 복합 객체던 싱글 객체던 동일한 인터페이스를 사용한다. 그러므로 실행하는데 걱정하지 않아도 된다.   

***

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

### Flyweight

동일한 객체를 자주 생성해서 사용할 때 매번 다시 생성하지 않고 객체풀에 저장해놓고 재활용하는 패턴이다. 메모리 절약과 객체 생성시 소요되는 시간을 줄일 수 있어서 성능에 이점을 줄 수 있다. 

예를들면 게임을 만드는데 게임 맵에 있는 객체들을 매번 만들어줘야 하는 것보다 재사용하는게 더 낫다. 

Applicability 

- 너의 프로그램이 사용 가능한 RAM 량보다 생성해야 할 객체의 수가 많다면 Flyweight 패턴을 사용하는게 좋다. 

***

### Bridge

브릿지 패턴은 대규모 클래스나 관련이 깊은 클레스 집항을 추상화와 구현이라는 두 개의 개별 계층 구조로 분할하는 패턴이다.

예를들면 Shape 라는 클래스가 있고 그 서브 클래스로 Circle 과 Square 이 있다고 하자. 이때 Color 라는 정보를 기반으로 Red 와 Blue 로 기존의 클래스를 확장시켜 나가기 위해서는 
Shape 아래에 RedCircle, RedSquare, BlueCircle, BlueSquare 이렇게 만들어야 한다. 

하지만 이런식으로 클래스를 확장시켜나간다면 나중에 새로운 컬러가 추가되거나 새로운 도형이 추가되면 만들어야 하는 클래스가 지수적으로 많아진다.

이 부분을 브릿지 패턴을 이용한다면 상속구조를 이용해서 클래스를 확장해나가는게 아니라 Composite 구조로 Shape  Color 를 포함하도록해서 두 개의 계별 구조로 클래스를 독립적으로 진화해나가면 이 문제를 해결하기 쉽다. 

즉 상속구조로 뭔가 기능을 추가하기 보다 새로운 클래스를 포함하도록 해서 기능을 추가하는 방법도 있다.  

Applicability 

- 하나의 모놀리식 클래스가 기능별로 어떤 변수에 따라 약간식 달라져야 한다면 브릿지 패턴을 써서 클래스를 나눠라. (예를들면 하나의 클래스가 여러 데이터 서버에 따라서 동작해야 한다면)

  - 하나의 거대한 클래스는 동작을 이해하기가 어렵다. 브릿지 패턴은 이 거대 클래스를 분리할 수 있도록 도와준다. 
  
- 클래스를 확장할 때 여러 차원으로 확장해야 하는 경우에 이 패턴을 사용하면 좋다. 

  - 원래 클래스는 모든 작업을 스스로 수행하도록 하고 다른 계층에 속한다면 그 계층에 이 클래스의 동작을 위임하도록 한다. 

- 만약 런타임시점에 구현이 바껴야한다면 브릿지 패턴을 사용하라. 

  - 이 부분은 선택적으로 받아들이면 된다. 브릿지 패턴을 이용하면 구현 객체를 바꿀 수 있다. 런타임시점에. 

***

## Behavioral patterns

### Strategy

하나의 결과를 만드는 목적(메소드)는 동일하나 그 목적을 달성할 수 있는 전략이 다양한 경우에 사용되는 패턴으로 동적으로 알고리즘을 교체할 수 있다. 

Applicability 
  
- 전략 패턴은 하나의 오브젝트에서 다양한 알고리즘을 사용해서 문제를 해결할 수 있고 이를 런타임 시점에 결정할 수 있기를 바란다면 사용하는게 좋다. 
  
- 클래스가 유사한 것이 많고 행동하는 방식이 조금씩 다르다면 전략 패턴을 사용하는 것이 좋다. 
  
- 이 전략 패턴을 사용함으로써 비즈니스 로직에서 구현의 디테일을 숨길 수 있다. 
  
- 만약 하나의  클래스에서 조건에 따라 동일한 알고리즘을 조금씩 변형해서 사용한다면 이 패턴을 사용하는 것이 좋다. 

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
  




