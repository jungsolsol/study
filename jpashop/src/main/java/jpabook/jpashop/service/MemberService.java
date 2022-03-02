package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true) //읽기
@RequiredArgsConstructor //final 붙은것만 밑에 컨스트럭쳐 만들어주는거 lombok꺼임
public class MemberService {


    private final MemberRepository memberRepository;

//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }




    //회원가입
    @Transactional //쓰기 (데이터변경)
    public  Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //예외처리
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }


    //회원 전체 조회
//    @Transactional(readOnly = true) 성능 최적화(읽기모드)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    //단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
