package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)//이게중요함
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
