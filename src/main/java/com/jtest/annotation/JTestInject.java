package com.jtest.annotation;

import com.jtest.NodesFactroy.Inject.Inject;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Documented
@Inject
public @interface JTestInject {
    @AliasFor(
            annotation = Inject.class
    )
    String filename() default "node.xml";

    @AliasFor(
            annotation = Inject.class
    )
    String value() default "var";
}

