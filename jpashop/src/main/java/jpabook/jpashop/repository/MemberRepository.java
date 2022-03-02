package jpabook.jpashop.repository;


import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor //final붙은거 변경되지말라고 컨스트럭터 만들어주는거임
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }
    //단건 조회
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }
    //전부 조회
    public List<Member> findAll() {
        return em.createQuery("select  m from Member m", Member.class)
                .getResultList();
    }
    //특정이름 조회
    public List<Member> findByName(String name) {
        return  em.createQuery("select m from Member m where m.name = :name",Member.class).
                setParameter("name",name).getResultList();
    }
}
