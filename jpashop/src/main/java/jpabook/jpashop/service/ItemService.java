package jpabook.jpashop.service;


import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional //readonly 라서 저장이안되니까 이건 따로 해줘야함
    public void saveItem(Item item) {
        itemRepository.save(item);
    }
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
       //findItem.change(name,price,stockQuantity) 실제로는 이렇게 메소드만들어서 넘기는게 훨씬
        //낫다 set으로 해놓으면 찾기 힘듬
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }
    public List<Item> findItems() {
        return itemRepository.findAll();
    }
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
