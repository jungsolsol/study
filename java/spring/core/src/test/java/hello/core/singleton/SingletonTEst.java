package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTEst {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //1 조회 호출 할때마다 객체생성
        MemberService memberService = appConfig.memberService();
        //2 조회 호출 할때마다 객체생성
        MemberService memberService1 = appConfig.memberService();

        System.out.println("memberservice1 = " + memberService);
        System.out.println("memberservice2 = " + memberService1);
//1번 memberservice1 = hello.core.member.MemberServiceImpl@2de23121
//2번 memberservice2 = hello.core.member.MemberServiceImpl@63475ace
        // 즉 요청이 계속와서 객체가 졸라쌓인다

        Assertions.assertThat(memberService).isNotSameAs(memberService1);
    }



    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonservice1 = SingletonService.getInstance();
        SingletonService singletonservice2 = SingletonService.getInstance();

        System.out.println("1 + " + singletonservice1);
        System.out.println("2 + " + singletonservice2);


        Assertions.assertThat(singletonservice1).isSameAs(singletonservice2);

    }
    //1 + hello.core.singleton.SingletonService@c0c2f8d
    //2 + hello.core.singleton.SingletonService@c0c2f8d


    @Test
    @DisplayName("스프링컨테이너와 싱글톤")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberservice1 = " + memberService1);
        System.out.println("memberservice2 = " + memberService2);


        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }

}


