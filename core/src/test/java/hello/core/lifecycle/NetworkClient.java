package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {//implements InitializingBean, DisposableBean { 이는 2003년도로 오래된 방식이고 이제 사용하지 않는다.

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url ="+ url);
    }

    public void setUrl(String url) {
        this.url = url;
    }
    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect: " + url);
    }
    public void call(String message){
        System.out.println("call = " + url+ " message = "+message);
    }
    //서비스 종료시 호출
    public void disConnect(){
        System.out.println("close : " + url);
    }
    @PostConstruct
    public void init() {//의존관계 주입이후 동작
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() {//빈이 종료될때 호출
        System.out.println("NetworkClient.close");
        disConnect();
    }
//    @Override
//    public void afterPropertiesSet() throws Exception {//의존관계 주입이후 동작
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메세지");
//    }
//
//    @Override
//    public void destroy() throws Exception {//빈이 종료될때 호출
//        System.out.println("NetworkClient.destroy");
//        disConnect();
//    }
}
