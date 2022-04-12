package hello.core.discount;

import hello.core.annotion.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
//@Qualifier("mainDiscountPolicy")
//@Primary // 넣으면 무조건 우선순위로 등록
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price){
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        } else{
            return 0;
        }
    }
}
