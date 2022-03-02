package jpabook.jpashop.domain.item;

import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
    //데이터를 가지고있는쪽에 비즈니스 로직이있는게 관리하기가 수월하다
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();

    //비즈니스 로직
    //재고 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    // 재고감소
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
