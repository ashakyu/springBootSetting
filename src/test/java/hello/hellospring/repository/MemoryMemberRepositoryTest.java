package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //test를 할 때 순서에 의존을 하면 안된다. 하지만 findAll()이 실행된 후 findByName()이 테스팅된다면
    //이미 저장된 spring1값이 존재하기 때문에 하나의 메소드가 끝난 후 date를 clear해줘야한다.
    @AfterEach
    public void afterEach() {
        repository.cleaStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        //Optinal로 설정했기 때문에 끄내기 위해 .get()을 해줘야한다.
        Member result = repository.findById(member.getId()).get();
        //Systemout.println으로 뽑아도 가능하지만..
        //1. Assertions.assertEquals(member, result); ->import Jupiter
        Assertions.assertThat(member).isEqualTo(result); //->import assertj
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
