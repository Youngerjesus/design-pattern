# Facade

퍼사드란 뜻부터 말하자면 건물의 입구쪽 전경을 말한다.

- 건물의 입구쪽 전경만 보면 건물의 내부를 알기는 힘들다. 퍼사드는 이런 내부를 숨기는 용도로 사용하는 패턴이다.
- 클라이언트의 복잡한 의존성을 최소화하기 위해서 사용한다. 클라이언트가 여러가지 서브 시스템에 의존성을 가지면 즉 강하게 결합되어있다면 변경될 이유가 많기도하고, 코드를 이해하기도 어렵다. 이런 경우에 클라이언트는 Fascade 에만 의존하고 Fascade 가 여러 서브 시스템을 가지고 클라이언트가 원하는 기능을 제공해주기만 한다면 이런 결합도를 낮출 수 있다.
- **이 패턴은 클라이언트의 코드에 결합도를 낮추서 변경을 주지 않을 수 있다는 장점이 있다.**



파사드 패턴을 사용하지 않는다면 내부 구현이 드러날 것이다.

다음은 파사드 패턴을 사용하지 않고 자바에서 메일을 보내는 코드다.

```java
public class Client {
	
	public static void main(String[] args) {
		String to = "test@gmail.com";
		String from = "from@gmail.com";
		String host = "127.0.0.1"; 

		Properties properties = System.getProperties(); 
		properties.setProperty("mail.smtp.host", host);

		Session session = Session.getDefaultInstance(properties);

		try {
			MimeMessage message = new MimeMessage(session); 
			message.setFrom(new InternetAddress(from));
			message.addRecipent(Message.RecipentType.TO, new InternetAddress(to)); 
			message.setSubject("Test");
			message.setText("message"); 
			
			Transport.send(message); 
		} catch (MessagingException e) {
			e.printStackTrace(); 
		}
	}
}
```

- 단순히 메일을 보내는 것인데 MimeMessage 가 무엇인지 알아야하고 Session 을 연결한다는 것이 어떤 의미인지 이해를 해야만 한다.

Facade 패턴을 이용하면 이렇게 변경될 것이다.

```java
public class Client {
	public static void main(String[] args) {
		EamilSettings emailSettings = new EmailSettings();
		emailSettings.setHost("127.0.0.1"); 
		EmailMessage emailMessage = new EmailMessage(); 
		emailMessage.setFrom("from@gmail.com"); 
		emailMessage.setTo("to@gmail.com");
		emailMessage.setSubject("test");
		emailMessage.setText("text"); 

		EmailSender emailSender = new EmailSender(emailSettings); 
		emailSender.sendEmail(emailMessage);
	}
}
```

- Facade 패턴을 통해 필요한 부분만 결합하도록 바꿨고 이로인해 코드를 이해하기가 더 쉬워졌다.

### 장점과 단점

- 서브 시스템에 대한 의존성을 한 곳으로 모을 수 있다. 이를 통해 클라이언트의 코드는 변경에 영향을 주지 않는다.
    - 하지만 Fascade 자체가 서브 시스템에 대한 의존성을 피할 순 없다. 어짜피 똑같은 거 아닌가? 라고 생각할 수 있다.
    - 그치만 추상화를 어떻게 하느냐에 따라서 코드의 이해 측면에서 더 쉬울 수있고 변경해야 할 곳이 명확해진다.

### Applicability

- 복잡한 하위 시스템에 간단한 인터페이스가 필요한 경우 Facade 패턴을 써라.
    - 주로 클라이언트와 가장 먼저 맞닿아있는 클래스의 경우 유용하다. 클라이언트의 요구사항을 바로 해결해주는 방식으로.
- 시스템을 layer 로 나눌 경우 각 레이어끼리의 요청에 Facade 패턴을 써라.
    - layer 끼리 각 진입점을 Facade 패턴을 써라 는 뜻. 각 레이어 끼리는 결합도를 낮춰야 하고 단순해야 이해하기 쉬우니까.

### 실제로 사용하는 예

slf4j 의 Logger 가 Facade 패턴을 이용했다.

로그를 가져올 때 LoggerFactory.getLogger() 를 통해서 로그를 가져온다.

근데 slf4j 의 구현을 보면 다양한 곳에서 로그를 가져온다.

- log4j
- log4j2
- logback

클라이언트 입장에서는 로거만 가지고 오면 된다. logback 을 쓰던, log4j 를 쓰던 log4j2 를 쓰던 그건 중요하지 않다.

그래서 Facade 패턴을 써서 slf4j 의 LoggerFactory.getLogger() 를 쓰면 된다.

이런 논리 아닌가 싶다.  “다 됐고! 너 이거 어떻게든 해와!”