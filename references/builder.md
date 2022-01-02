# Builder 

빌더 패턴은 다양한 구성요소를 가진 인스턴스를 생성할 때 동일한 프로세스를 통해 안전하게 생성할 수 있도록 하는 패턴이다.

빌더 패턴을 사용하지 않는 경우에는 이런식으로 작업할 것이다.

```java
public class App {
	public static void main(String[] args) {
		TourPlan tourPlan = new TourPlan(); 
		tourPlan.setTitle("한국 여행");
		tourPlan.setNights(2);
		tourPlan.setDays(3); 
		...
	}
}
```

- 물론 이게 싫어서 생성자를 만들 수 있다. 근데 생성자에서는 파라미터의 순서를 맞추기 귀찮다는 문제가 있다.
- 그냥 생각나서 적는건데 코틀린에서는 생성자만 있어도 된다. 생성자 + 파라미터 기본 값 + 파라미터 순서 정하지 않아도 됨 (파라미터 이름=값 이런식으로 순서 필요없이 메소드 호출 가능)

빌더 패턴을 이용하면 다음과 같다.

```java
public interface TourPlanBuilder {
	TourPlanBuilder title(String title); 

	TourPlanBuilder nights(int nights);

	TourPlanBuilder days(int days); 

	TourPlan getPlan(); 
}
```

```java
public class DefaultTourPlanBuilder implements TourPlanBuilder {
	String title; 

	int nights; 

	int days; 

	@Override 
	public TourPlanBuilder title(String title) {
		this.title = title; 
	}

	@Override 
	public TourPlanBuilder nights(int nights) {
		this.nights = nights; 
	}

	@Override
	public TourPlanBuilder days(int days) {
		this.days = days; 
	}

	@Override
	public TourPlan getPlan() {
		return new TourPlan(titie, nights, days);  
	}
}
```

```java
public class TourDirector {

	private TourPlanBuilder tourPlanBuilder; 

	public TourDirector(TourPlanBuilder tourPlanBuilder) {
		this.tourPlanbuilder = tourPlanBuilder; 
	}

	public TourPlan cancunTrip() {
		return tourPlanBuilder
							.title("코쿤 여행")
							.nights(2)
							.days(3)
							.getPlan(); 
	}
}
```

- 객체를 만드는 빌더와 이 빌더의 조합을 가지고 한번에 만드는 Director 로 구성되어 있다.
- Builder 인터페이스에서는 값을 하나씩 세팅해줄 수 있는 메소드들을 제공해준다. (title, nights, days 등) 그리고 아직 완성되기 전이므로 TourPlanBuilder 를 리턴해주고 완성된 경우라면 getPlan() 을 호출해서 TourPlan 을 만든다.
- 객체를 완성시켜주는 getPlan() 메소드의 경우에는 validation 작업을 추가할 수도 있다.

### 장점과 단점

- 만들기 복잡한 객체를 순차적으로 만들 수 있다.
    - 여기서는 동일한 TourPlanBuilder 를 이용했지만 같은 타입이 아니라 다른 타입 빌더로 변환하면서 만들어 질수도 있다.
    - 즉 순서를 넣을 수 있다. (생성자가 가지지 못한것)
    - 그리고 마지막으로 생성하는 타입은 하위 타입이 나오도록 할 수도 있다.
- 불완전한 객체를 생성하지 못하도록 검증할 수 있다.
- 빌더 패턴을 이용할 때 Director 와 Builder 모두 만들어야 한다는 단점이 있다. 그리고 구조도 조금 복잡해진다.

### Applicability

- 생성자 매개변수가 많은 경우에 생성자 오버로딩을 통해서 생성자를 정의하는 것보다 빌더 패턴으로 step by step 으로 생성하는 게 더 편하다고 느끼는 경우에 빌더 패턴을 이용해라.
  - 근데 이 경우에는 setMethod() 를 넣어야해서 생각이 필요할 듯. 

- 같은 Product 를 약간씩 다르게 표현해야 한다면 빌더 패턴을 이용해서 객체를 생성해라
  - 생성자가 약간씩 다른 것 보다는 이게 나을듯.  
  - 빌더패턴을 이용해서 약간씩 다른 다양한 생성자들을 정의하고 Director 를 이용해서 약간씩 차이나는 부분에 대해서 값을 넣어주도록 하면 된다.

- Composite 패턴의 객체를 생성하거나 다른 복잡한 객체를 생성할 때 빌더 패턴을 사용하라.

    - 빌더 패턴을 이용하면 단계별로 객체를 생성하고 일부 단계의 실행을 **잠시 미룰수도 있기** 떄문에 복잡한 객체를 만드는데 적합하다. 
    - 그리고 빌더 패턴을 이용하면 완성된 시점에 객체를 반환할 수 있기 때문에 클라이언트가 불완전한 객체를 쓰도록 하지 않을 수 있다.


### 실제로 사용하는 예

- [https://cheese10yun.github.io/spring-builder-pattern/](https://cheese10yun.github.io/spring-builder-pattern/)
- lombok 을 이용한 builder 패턴으로 객체를 생성하는 경우에 값이 없으면 예외를 던지도록 하는게 맞다. 불완전한데 만들도록 하는게 아니라 (당연한 소리 ㅇㅇ)

```java
@Entity 
public class Order {

	@Builder
  public Order(Address address, List<Product> products) {
    Assert.notNull(address, "address must not be null");
    Assert.notNull(products, "products must not be null");
    Assert.notEmpty(products, "products must not be empty");

    this.address = address;
    this.products = products;
  }
}
```

- 그래서 lombok @Builder 를 이용할 땐 테스트 코드에서 불완전하게 생성하는 경우 예외가 던져지는지 테스트를 짜보자.

```java
	@Test(expected = IllegalArgumentException.class)
  public void Account_bankName_비어있으면_exception() {
    Account.builder()
        .accountHolder("홍길동")
        .accountNumber("110-22345-22345")
        .bankName("")
        .build();
  }
```

- Builder 클래스에 이름을 부여해서 각 Builder 가 하는 일에 책임을 명확하게 나누자.
    - 주문에 대한 환불이 있을 경우 환불에 대한 금액을 신용 카드 취소, 계좌 기반 환불이 있을 수 있다.
    - 신용카드 환불인 경우에는 신용카드 정보를 입력받게 하고, 계좌 환불인 경우에는 계좌 환불을 입력받게 해야한다.
    - 이를 이름에 따라서 구별하자.

```java
	@Builder(builderClassName = "ByAccountBuilder", builderMethodName = "ByAccountBuilder") // 계좌 번호 기반 환불, Builder 이름을 부여해서 그에 따른 책임 부여, 그에 따른 필수 인자값 명확
  public Refund(Account account, Order order) {
    Assert.notNull(account, "account must not be null");
    Assert.notNull(order, "order must not be null");

    this.order = order;
    this.account = account;
  }

  @Builder(builderClassName = "ByCreditBuilder", builderMethodName = "ByCreditBuilder")  // 신용 카드 기반 환불, Builder 이름을 부여해서 그에 따른 책임 부여, 그에 따른 필수 인자값 명확
  public Refund(CreditCard creditCard, Order order) {
    Assert.notNull(creditCard, "creditCard must not be null");
    Assert.notNull(order, "order must not be null");

    this.order = order;
    this.creditCard = creditCard;
  }
}
```

```java
	@Test
  public void ByAccountBuilder_test() {
    final Refund refund = Refund.ByAccountBuilder() // 빌더 이름으로 명확하게 그 의도를 드러 내고 있습니다.
        .account(account)
        .order(order)
        .build();

    assertThat(refund.getAccount()).isEqualTo(account);
    assertThat(refund.getOrder()).isEqualTo(order);
  }

  @Test
  public void ByCreditBuilder_test() {
    final Refund refund = Refund.ByCreditBuilder() // 빌더 이름으로 명확하게 그 의도를 드러 내고 있습니다.
        .creditCard(creditCard)
        .order(order)
        .build();

    assertThat(refund.getCreditCard()).isEqualTo(creditCard);
    assertThat(refund.getOrder()).isEqualTo(order);
  }
```