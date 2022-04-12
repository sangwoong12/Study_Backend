package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j // 로그선언 코드를 이걸로 해결
@RestController
public class LogTestController {
    //로그선언
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name);
        //등급 순서대로..
        log.trace("trace log="+name);
        //위와같이 할경우 문자 더하기 연산이 이루어지기 때문에 하면 안좋다.
        log.trace("trace log={}",name);
        log.debug("debug log={}",name);
        log.info(" info log={}",name);
        log.warn(" warn log={}",name);
        log.error("error log={}",name);


        return "ok";//RestController는 ok를 반환
    }
}
