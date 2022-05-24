package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    //필드주입
    @Autowired
    DataSource dataSource;

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        /*
        기존 MemoryMemberRepository -> JDBC로 변경하는 과정에서 Config만을 통해 손쉬운 변경이 가능하다.
        Spring을 왜 쓸까?
        다형성을 활용하는 OOP방식을 Spring에서 지원해주기 때문이다.

        OCP, 개방-폐쇄의 원칙
        -확장에는 열려있고, 수정에는 닫혀있다.(객체기향에서 다형성을 기반으로 활용을 한다면 동작 구현 코드는 수정하지 않고
        기능 추가는 가능하다)
         */
        return new JdbcMemberRepository(dataSource);
    }
}

