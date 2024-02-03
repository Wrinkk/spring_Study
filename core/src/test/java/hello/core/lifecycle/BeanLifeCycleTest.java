package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    // 객체 생성 -> 의존관계 주입 (생성자 주입은 예외. 생성자는 캑체를 만들 떄 이미 스프링빈이 같이 들어와야 하기때문에)
    // 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화콜백 -> 사용 -> 소멸전 콜백 -> 스프링종료 (싱글톤)
    /* 객체의 생성과 초기화를 분리하자.
    *  빈 생명주기 콜백지원 3가지
    *  인터페이스
    *  설정 정보에 초기화 메서드 종료 메서드 지정
    *  @PostConstruct, @PreDestory 애노테이션 지원
    * */

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        /* 설정 정보 사용 특징
        *  메서드 이름을 자유롭게 설정
        *  스프링 빈이 스프링 코드에 의존 X
        *  코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드 적용
        *  사용 안할시 "" 공백으로 냅두면 됨.
        * */
//        @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
