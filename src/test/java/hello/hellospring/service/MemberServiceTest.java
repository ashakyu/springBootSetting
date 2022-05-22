package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    //test는 과감하게..한글로 해도 된다..

    MemberService memberService;
    //이렇게 new 객체 생성을 하면 MemberService의 MemoryMemberRepository의 인스턴스와 다른 인스턴스다.
    /*MemoryMemberRepository memberRepository = new MemoryMemberRepository();*/
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        /*
        내가 직접 new 하지 않고 = DI라고 한다.
         */
    }

    @AfterEach
    public void afterEach() {
        memberRepository.cleaStore();
    }


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //ctrl+Alt+v
        /*assertThrows(IllegalStateException.class, ()-> memberService.join(member2));*/
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(illegalStateException.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*        try{
            memberService.join(member2);
        }catch(IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}