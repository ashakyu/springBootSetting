package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //localhost8080으로 요청했을 시
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
