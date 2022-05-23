package hello.hellospring.controller;

public class MemberForm {
    //input 태그의 name 값을 보고 일치하면 setName을 호출 한 후 값을 넣어준다.
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
