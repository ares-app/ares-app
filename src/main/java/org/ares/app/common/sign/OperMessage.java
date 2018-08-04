package org.ares.app.common.sign;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 消息显示
 * @author ly
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface OperMessage {

	String value() default "";
	String key() default "current_not_key";
	boolean record() default true;
	String[] properties() default {};
	
}
