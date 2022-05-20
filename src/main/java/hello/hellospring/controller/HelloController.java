package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        //return = templates 하위의 파일들을 찾아 렌더링한다.('viewResolver' 작동)
        //정확한 위치 : 'resources:templates/ + viewName + .html'
        return "hello";
    }
}
