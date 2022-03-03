# Spring 예제연습

환경: Java 11, Spring boot 2.6.3 ,IntelliJ Ultimate
사용기능 : lombok,H2

*비즈니스 요구사항 및 설계

# 회원
회원 가입, 회원 조회
회원은 일반 VIP 두가지 등급
회원데이터는 우선 로컬 DB구축(H2)
# 주문 , 할인정책
회원은 상품을 주문할수있다
회원 등급에 따라 할인정책 적용
할인정책은 VIP는 1000원할인해주는 할인해주는 정책과 구매액 10%할인해주는 정책 
또는 정책적용을 안할수도있다

![1](https://user-images.githubusercontent.com/88434960/156484780-8a5ac222-56bd-4b1c-84cd-c3f84a775edf.JPG)

협력관계 관계도

![2](https://user-images.githubusercontent.com/88434960/156484785-a9dd968f-26a5-441a-ba87-f8d73215d0ed.JPG)

의존관계 주입을위해 MemeberServiceImpl에서 @Autowired 어노테이션을 사용해 의존관계 주입을하고
연결은 MemeberService 인터페이스에서 이루어졌다

![3](https://user-images.githubusercontent.com/88434960/156485500-384f2c20-6237-4250-b3b9-fc5d832f918e.JPG)

OrderService의 경우에는 할인정책이 여러가지가 있기때문에 인터페이스만 의존하여 객체지향이
깨지지 않도록 설계했다

또한 최근에는 lombok을 활용해 생성자에 final키워드를 넣어서 간편하게 개발하는데 그에따라 
@RequiredArgsConstructor 어노테이션을 활용해 생성자를 자동으로 호출했다

또한 콜백에대한 이해를 위해 @PostContruct , @PreDestroy 어노테이션을활용해 생명주기를 확인했다
