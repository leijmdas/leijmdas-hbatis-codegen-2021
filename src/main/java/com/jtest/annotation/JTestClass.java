package com.jtest.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited
public @interface JTestClass {
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD})
    @Inherited
    public @interface author {
        String value() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Inherited
    public @interface no {
        String value() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Inherited
    public @interface title {
        String value() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @Inherited
    public @interface pre {
        String value() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @Inherited
    public @interface step {
        String value() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @Inherited
    public @interface exp {
        String value() default "";
    }
}