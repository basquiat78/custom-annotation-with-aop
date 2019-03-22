package io.basquiat.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.basquiat.annotation.APIUsage;
import io.basquiat.service.UsageService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * created by basquiat
 *
 */
@Slf4j
@Component
@Aspect
public class APIUsageAOP {

	@Autowired
	private UsageService usageService;

	/**
	 * 
	 * advice는 4가지를 한번에 제공하는 around로 처리한다.
	 * pointcut설정은 다음과 같이 해당 annotation으로 설정하고 해당 어노테이션 객체를 파라미터로 사용하기 위해 around에 
	 * 다음과 같은 형식으로 설정한다.
	 * <pre>
	 * 시나리오는 이렇다.
	 * 
	 * APIUsage가 나의 관심사항이고 해당 어노테이션에 설정한 usage값을 체크하기 위해 around에 해당 어노테이션에 대한 아규먼트를 준다.
	 * 이것은 파라미터에 포함시켜 객체로부터 usage 설정값을 체크할수 있다.
	 * 
	 * joinPoint로부터 시그니처의 네임, 즉 호출된 메소드 명과, 해당 메소드에 설정된 어노테이션의 usage값을 가져와서 usageService에 값을 넘겨서 처리한다.
	 * </pre>
	 * @param joinPoint
	 * @return Object
	 * @throws Throwable
	 */
	@Around("@annotation(io.basquiat.annotation.APIUsage) && @annotation(apiUsage)")
	public Object APIUsage(ProceedingJoinPoint joinPoint, APIUsage apiUsage) throws Throwable {
		log.info("################################################");
		log.info("######  APIUsage Annotation  ######");
		log.info("################################################");
		usageService.measureUsage(joinPoint.getSignature().getName(), apiUsage.usage());
		return joinPoint.proceed();
	}

	
}
