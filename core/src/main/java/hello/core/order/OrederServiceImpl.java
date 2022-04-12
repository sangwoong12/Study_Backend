package hello.core.order;

import hello.core.annotion.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrederServiceImpl implements OrderService{

    private final MemberRepository memberRepository;// = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //위와 같은경우는 추상과 구현을 모두 참조하기 때문에 DIP 와 OCP를 모두 위반한다. 올바른 것은 추상에만 의존해야한다.
    private final DiscountPolicy discountPolicy;
 //RequiredArgsConstructor가 생성자를 만들어 준다.
    @Autowired
    public OrederServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy ){//@Qiaulifier 를 통해 2개 등록되어 있는 빈중 하나 선택
        //구현체를 받아오는 구조이다. 이를 통해 DIP OCP를 지키게 된다.
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;}
    //ctrl e 최근열어본 파일열어보기

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    //테스트 용도

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
