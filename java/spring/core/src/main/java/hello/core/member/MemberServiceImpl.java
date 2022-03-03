package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    //자동으로 의존관계 주입해줌 Autowired #생성자주입임
    @Autowired  //ac.getBean(MemberRepository.class) 같은느낌
    public MemberServiceImpl(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {

        return memberRepository.findById(memberId);
    }


    //test
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
