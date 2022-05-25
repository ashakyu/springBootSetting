package hello.hellospring.domain;

import javax.persistence.*;

//ORM(object Relational Mapping) 객체와 릴레이셔널 데이터베이스를 매핑한다는 뜻이다 .
//JPA가 관리하는 엔티티가 된다.
@Entity
public class Member {
    
    //PK등록(DB에서 값 등록)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //컬럼명과 도메인 값을 일치시켜 수도 있다.
    @Column(name="name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
