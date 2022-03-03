package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class ProtoTypeTest {

    @Test
    void protoTypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find ptBean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find ptBean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        System.out.println("ptBean1 = " + bean1);
        System.out.println("ptBean2 = " + bean2);
        Assertions.assertThat(bean1).isNotSameAs(bean2);

        ac.close();
        //만들고 버려서 종료메서드같은거 실행안된다
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
