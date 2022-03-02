Spring 예제공부 1
# *jpashop*

사용 환경 : java 11 ,springboot '2.6.4'
사용 기능: web, thymeleaf, jpa, h2, lombok, validation

#도메인 설계

도메인 모델, 테이블 설계

![도메인모델](https://user-images.githubusercontent.com/88434960/156334045-4ee86c9b-3414-4262-b70a-4fd05610bdcc.JPG)

엔티티 설계

![엔티티설계](https://user-images.githubusercontent.com/88434960/156336389-8d95af47-4cd2-4a5f-80e3-a9df3cdc6dc7.JPG)

테이블 설계

![테이블 분석](https://user-images.githubusercontent.com/88434960/156334219-465eadd9-217a-4b7c-ac53-fda82a2d6a49.JPG)
0e-553c92c22c5f.JPG)

엔티티들은 domain디렉토리 목록에 구성했고 단순 비즈니스로직 트랜잭션 처리는 service계층에서 설계했습니다. 웹과 관련된 계층은 controller에서 구성했고 repository 계층에서는 엔티티 매니저를 사용하여 영속성을 부여해주었습니다.

실제 동작화면

## 회원기능
![회원가입_관리](https://user-images.githubusercontent.com/88434960/156340226-a4e9bb09-63a1-478e-926e-06207dff1f43.gif)
MemberService 코드에서는 @Transactional(readOnly = true)로 걸어놓고 실제 변경되는 save같은 부분 매서드에만 쓰기도 가능하도록 변경하여 성능을 향상시켰습니다. 의존관계주입은 롬복 라이브러리기능을 활용하여 @RequiredArgsConstructor로 주입하여 다른 곳에서 변경불가능하게 작성했습니다.

## 상품기능
![아이템관리](https://user-images.githubusercontent.com/88434960/156340241-7786c9ee-414c-44e4-92a9-79eb331941ad.gif)
비즈니스 로직은 재고증감 매서드와 재고 부족시 예외가 발생하도록 설계했습니다.
또한 수정시 준영속엔티티는 병합처리 했습니다. (book상품만 등록했습니다)

## 주문기능
![주문관리기능](https://user-images.githubusercontent.com/88434960/156340250-98093063-a91b-44d6-932e-7177c5613a17.gif)
비즈니스 로직으로 주문, 주문취소, 주문내역 검색기능을 제공합니다.
또한 동적 쿼리는 jpa에서제공하는 Criteria을 사용해 해결했습니다.

