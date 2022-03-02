package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문시간
    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 order , cancel

    //==연관관계 편의 메서드==// ->양방향에는 있으면좋다
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }


    //==생성 매서드== (주문생성)// 생성할때부터 member delivery orderitems를 호출해야함
    public static Order createOrder(Member member, Delivery delivery,OrderItem... orderItems){
        ///...문법은 여러개 넘기는것
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER); //order의 처음상태를 Order로 강제해놓음
        order.setOrderDate(LocalDateTime.now()); //오더의 시간을 설정
        return order;
    }

    //==비즈니스 로직 ==//
    /**
     * 주문취소
     *
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            //배송완료가 되버린 상태이면
            throw new IllegalStateException("이미 배송완료된 상품은 취소 불가능합니다");
        }
        this.setStatus(OrderStatus.CANCEL); //위에 과정을 통과하면 status의 상태를 취소로바꿈
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel(); //order에도 캔슬을하면 orderitem에도 캔슬해줘야함
        }
    }
    //==조회 로직==전체주문가격//

    /**전체주문가격 조회**/

    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
