package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    //Optional? java8~
    //메소드를 실행 후 값이 null일 수 있다. 그를 처리하기 위해 Optional를 쓴다.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
