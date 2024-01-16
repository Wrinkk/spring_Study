package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) // 스프링 빈을 자동으로 끌어올려주는 것 (동기화?를 말하는것같다) 기존예제 코드를 살리기위해서 excludeFilters 넣어줌.
public class AutoAppConfig {

// 생성자가 많아도, 타입을 찾아 자동으로 주입을 함.
}
