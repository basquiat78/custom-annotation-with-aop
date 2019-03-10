# custom-annotation-with-aop

이전 프로젝트하면서 기억나는 것들 하나씩 떠올리면서 적는 프로젝트이다.     

첫 회사에서 자체 프레임워크를 만들 당시에 수많은 어노테이션들을 보면서 처음에는 이게 다 존재하는 것들인가 의문을 가졌었다.    

하지만 대부분은 커스텀 어노테이션이라는 것을 알았을 때 나는 어노테이션도 만들수 있다는 사실을 알았다.     

그리고 언젠가는 이것을 써먹을 수 있겠구나 공부했던 때가 벌써 몇 년전이다.     

그리고 이 방식을 사용할 일이 생기면서 나름 참 재미가 있었다.    
     
    
[What is AOP?](https://docs.spring.io/spring/docs/2.0.x/reference/aop.html)
    
AOP와 관련해서는 그냥 링크로 대체한다.    

어째든 AOP와 관련해서 흔히 알고 있었던 것은 트랜잭션, 로그와 관련된 것들이라 예전에는 pointcut, joincut를 xml에 설정하고 사용하기만 했었는데 어떤 특수한 환경에서의 인증처리를 위해서
적용하면서 제대로 알게 된거 같다.


# this project Scenario?

심플하게 하나의 어노테이션을 작성한다.

그리고 그 어노테이션은 내부적으로 usage이라는 스트링값을 가지고 있으며 default값으로 free를 가진다.

만일 해당 API를 콜할 때 유료라면 pay값을 설정한다.

총 3개의 API가 존재하며 하나는 free, 하나는 pay, 그리고 해당 어노테이션이 없는 API로 작성한다.

유료 설정된 url호출

http://localhost:8080/api/1

```
2019-03-11 00:41:18 - GET "/api/1", parameters={}
2019-03-11 00:41:18 - Mapped to public java.lang.String io.basquiat.web.UsageController.one()
2019-03-11 00:41:18 - ################################################
2019-03-11 00:41:18 - ######  APIUsage Annotation  ######
2019-03-11 00:41:18 - ################################################
2019-03-11 00:41:18 - call UsageService
2019-03-11 00:41:18 - Usage(apiName=one, usage=pay)
2019-03-11 00:41:18 - Using 'text/html', given [text/html, application/xhtml+xml, image/webp, image/apng, application/xml;q=0.9, */*;q=0.8] and supported [text/plain, */*, text/plain, */*, application/json, application/*+json, application/json, application/*+json]
2019-03-11 00:41:18 - Writing ["Hi! one!"]
2019-03-11 00:41:18 - Completed 200 OK
```
무로 설정된 url 호출

http://localhost:8080/api/2

```
2019-03-11 00:41:54 - GET "/api/2", parameters={}
2019-03-11 00:41:54 - Mapped to public java.lang.String io.basquiat.web.UsageController.two()
2019-03-11 00:41:54 - ################################################
2019-03-11 00:41:54 - ######  APIUsage Annotation  ######
2019-03-11 00:41:54 - ################################################
2019-03-11 00:41:54 - call UsageService
2019-03-11 00:41:54 - Usage(apiName=two, usage=free)
2019-03-11 00:41:54 - Using 'text/html', given [text/html, application/xhtml+xml, image/webp, image/apng, application/xml;q=0.9, */*;q=0.8] and supported [text/plain, */*, text/plain, */*, application/json, application/*+json, application/json, application/*+json]
2019-03-11 00:41:54 - Writing ["Hi! two!"]
2019-03-11 00:41:54 - Completed 200 OK

```

설정되지 않은 url 호출

http://localhost:8080/api/3

```
2019-03-11 00:42:32 - GET "/api/3", parameters={}
2019-03-11 00:42:32 - Mapped to public java.lang.String io.basquiat.web.UsageController.three()
2019-03-11 00:42:32 - Using 'text/html', given [text/html, application/xhtml+xml, image/webp, image/apng, application/xml;q=0.9, */*;q=0.8] and supported [text/plain, */*, text/plain, */*, application/json, application/*+json, application/json, application/*+json]
2019-03-11 00:42:32 - Writing ["Hi! three!"]
2019-03-11 00:42:32 - Completed 200 OK
```

# At A Glance
관련 어노테이션의 설정값 usage에 들어갈 값 가령 free, pay등은 enum에 정의해서 사용할 수 있겠다.    

Around처리한 만큼 실행 전 후에 대한 구분을 하기 위해서는 실제 APIUsageAOP클래스에서 try~catch~finally를 통해서 joincut을 정할 수 있다.

좀 더 구체적으로 Around, Before, After외의 다양한 방식을 적용할 수 있겠지만 심플하게 Around로  처리하는 것을 선호한다. 