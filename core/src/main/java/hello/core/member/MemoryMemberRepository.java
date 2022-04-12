package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component //memoryMemberRepository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //concurrenthashmap 나중에 이해하기
    // 저장소

    @Override
    public void save(Member member) {
            store.put(member.getId(), member); // put 넣고
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId); // get 빼고
    }
}
