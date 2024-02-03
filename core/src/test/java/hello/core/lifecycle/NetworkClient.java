package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient /* implements InitializingBean, DisposableBean */ {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 url = " + url);

    }


    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }
    public void disconnect() {
        System.out.println("close: " + url);
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//
//    }
    /* 초기화, 소멸 인터페이스 단점
     *  인터페이스가 스프링 전용이라 코드가 스프링 전용 인터페이스에 의존함.
     *  초기화, 소멸 메서드의 이름 변경 불가.
     *  코드를 고칠 수 없는 외부 라이브러리를 적용할 수 없음.
     *  현재는 잘 사용안함.
    *  */

//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    /* 컴포넌트스캔 이랑 잘어울림.
    *  단점 - 외부 라이브러리에는 적용 X, 초기화, 종료해야 하면 @Bean의 기능을 사용하자 (init method)
    *  얘를 사용하자.
    * */
    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");

    }
    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
