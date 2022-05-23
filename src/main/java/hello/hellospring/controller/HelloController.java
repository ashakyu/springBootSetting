package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        //return = templates 하위의 파일들을 찾아 렌더링한다.('viewResolver' 작동)
        //정확한 위치 : 'resources:templates/ + viewName + .html'

        /*
        MVC와 템플릿 엔진 View와 Controller, Model을 분리해 로직을 한다.
        기존 model1 방식은 View에 모든 로직을 처리해 반환을 했다면 이를 보완하기 위해 model2을 적용한다.*/

        return "hello";
    }

    /*
    localhost:8080/hello-mvc -> 내장 톰캣 서버 -> mapping되어있는 method 호출 -> return된 값을 viewResolver가 
    해당 return 값에 맞는 값을 찾아 html변환 후 웹 브라우저에게 보여준다.
     */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
}
