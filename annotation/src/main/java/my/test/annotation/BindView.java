package my.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by YiVjay
 * on 2020/4/25
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface BindView {
    String value();
}
