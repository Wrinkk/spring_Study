package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","spring!!");
        return "hello";
    }  // localhost8080 -> 내장 톰켓서버 -> helloController -> viewResolver -> 내장 톰켓서버 -> hello.html(변환 후) -> 웹브라우저
}

