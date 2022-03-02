package jpabook.jpashop.repository;


import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null){  //item은 jpa에 저장전까지 Id값이없음.. 새로 생성하는객체
                                    //그니까 신규로 등록하는거임
            em.persist(item);
        } else {
            em.merge(item); //merge --> update랑 비슷한거임
        }
    }
    //단건검색
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    //전부찾기
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
