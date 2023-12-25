package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}
 // 스프링은 스프링 컨테이너에 스프링 빈을 등록할때, 기본으로 싱글톤으로 등록한다.
}
