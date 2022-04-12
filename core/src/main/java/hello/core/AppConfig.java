package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository; // 이와같이 import하는것은 정적인 클래스 의존관계
import hello.core.order.OrderService;
import hello.core.order.OrederServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration// 이걸 주석처리할경우 싱글톤을 보장할수 없다.
//객체의 생성과 연결을 당담 IoC(inversion of Control) 당담
public class AppConfig { //Di 컨데이너 (Dependency injection)
    //memberService -> MemoryMemberRepository
    //orderService -> MemoryMemberRepository

    @Bean// spring bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());//생성자 주입 이라고 한다.
    }
    @Bean
    public MemberRepository memberRepository() { // 하나로 묶으면서 이것만 수정하면 전체적으로 바꿀수있다. 중복도 제거
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy(){ // 이처럼 무엇을 고를지 선택하는것을 동적인 객체 인스턴스 의존 관계라고한다.
        //return new FixDiscountPolicy(); // 이거하나로 바꿀수있다.
        return new RateDiscountPolicy();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrederServiceImpl(memberRepository(),discountPolicy());
        //return null;
    }

}
