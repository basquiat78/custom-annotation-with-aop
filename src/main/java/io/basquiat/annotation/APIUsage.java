package io.basquiat.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 
 * created by basquiat
 *
 * controller마다 호출될 경우  해당 어노테이션이 있으면 AOP로 처리한다.
 *
 */
@Component
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface APIUsage {
	
	public String usage() default "free";
	
}
