package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {


    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService stateService1 = ac.getBean(StatefulService.class);
        StatefulService stateService2 = ac.getBean(StatefulService.class);

        //Thread A 사용자가 만원주문
        stateService1.order("userA",10000);
        //Thread B 사용자가 2만원줌ㄴ
        stateService2.order("userB",20000);

        //Thread A 사용자A 주문 금액 조회회
       int price = stateService1.getPrice();
        System.out.println("price = "+ price);
        //2만원이 나와버림(인스턴스는 같은걸 써버려서그럼)

        org.assertj.core.api.Assertions.assertThat(stateService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();

        }
    }

}