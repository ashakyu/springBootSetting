package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        /*
        persist
        처음 엔티티 객체를 생성했다면, 이 객체는 Transient/New 상태라고 불린다.
        이 상황에서 persist없이 곧바로 Transaction을 종료한다면 db상에는 아무런 변화가 일어나지 않는다.
        이 엔티티를 Persistence Context가 관리하도록 하기 위해서는 persist메서드를 호출해야한다.
        이 메서드가 호출되면 article은 managed(영속) 상태로 진입하게 된다.
        이 managed되고 있는 엔티티들은 EntityManager에 의해 변경감지가 적용되어 Persistence Context내의 내용을
        DB에 반영하는 flush가 호출되었을 때 변경된 필드에 대해 자동으로 update/insert문이 발행된다.

        새로운 상태 : persist()
        이미있는 상태 : merge()
         */
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // : name = 파라미터 정의
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                //파라미터 바인딩
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        /*
        JPQL : 테이블이 아닌 엔티티 객체를 대상으로 검샛하는 객체 지향 쿼리
        SQL 을 추상화해서 특정 데이터 베이스 SQl에 의존하지 안흔다.
        1. 대소문자 구분(엔티티 O , JPQL 키워드는 X)
        2. 테이블 명 대신 엔티티 명을 사용(지정하지 않을 시 클래스 명을 기본값으로 사용[추천])
        3. 별칭은 필수

        Member 클래스에서 모든 멤버 리스트를 조회하는 쿼리이다.
        EntityManager 객체에서 createQuery() 메서드를 호출하면 쿼리가 생성된다.
        TypedQuery는 반환되는 엔티티가 정해져 있을 때 사용하는 타입이며,
        em.createQuery 메서드를 호출할 때 두 번째 인자로 엔티티 클래스를 넘겨준다.
         */
        //엔티티를 조회하고 select 의 대상 : 엔티티 자체(객체 자체)
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
