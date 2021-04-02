package com.jtest.NodesFactroy.Inject;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2016-04-02.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Documented
public @interface Inject {

   String filename() default "node.xml";

   String value() default "var";
}
