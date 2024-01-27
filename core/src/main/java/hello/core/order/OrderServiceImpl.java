package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// 의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작한다.
@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
// @Autowired private MemberRepository memberRepository;
// @Autowired private DiscountPolicy discountPolicy;
    // 외부에서 변경이 불가능해서 테스트 하기 힘듬. (필드 주입), DI 프레임워크가 없으면 아무것도 할 수 없음.

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    // @Autowired는 *타입 매칭을 시도하고 이때 여러 빈이 있으면 *필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭.
    // @Qualifier는 @qualifier(상세하게 동작)를 찾는 용도로만 사용하는게 명확하고 좋음. @Quilifier 끼리 매칭, 빈이름 매칭, 안되면 NoSuchBeanDefinitionExcption 예외 발생
    // @Autowired 시에 여러 빈이 매칭이 되면 @Primary(기본값 처럼 동작) 가 우선 매칭됨. Primary보다 Qualifier가 우선 순위
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {

        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    } // lombok이 알아서 생성자 만들어줌.

//    @Autowired(required = false) //선택적일 때, (필수가 아닐 때)
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    // 생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입된다.
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }


//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); 둘다 의존성 DIP를 지키지않았음

//    @Autowired // 일반 메서드 주입, 한번에 여러필드를 주입 받을 수 있다. (잘사용안함)
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

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
