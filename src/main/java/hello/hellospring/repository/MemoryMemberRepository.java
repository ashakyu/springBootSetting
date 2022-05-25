package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //null이 반환될 가능성 -> Optional.ofNullable로 감싸면 null이여도 반환이 가능하다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //roof로 store의 values값들을 돌린다.
        return store.values().stream()
                //member에서 member.getName이 파라미터의 name이랑 같은 지 확인한다. 같은 경우일 때만 필터링한다.
                .filter(member -> member.getName().equals(name))
                //찾으면 반환한다.
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //values = member
        return new ArrayList<>(store.values());
    }

    public void cleaStore(){
        store.clear();
    }
}
