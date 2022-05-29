package io.github.kale_ko.gighm.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation meaning an object could be null
 * 
 * @author Kale Ko
 * 
 * @version 1.8.0
 * @since 1.8.0
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.CLASS)
@Inherited
@Documented
public @interface Nullable {}