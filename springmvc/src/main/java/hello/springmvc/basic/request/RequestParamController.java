package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}",username, age);

        response.getWriter().write("ok");
    }
    @ResponseBody//RestController와 같은 효과 위에 Controller이기 때문에 써줘야 한다.
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
        @RequestParam("username") String memberName,
        @RequestParam("age") int memberAge){

        log.info("username={}, age={}",memberName, memberAge);
        return "ok";//안쓸경우 view에서 ok를 찾는다.
    }
    @ResponseBody//RestController와 같은 효과 위에 Controller이기 때문에 써줘야 한다.
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age){

        log.info("username={}, age={}",username, age);
        return "ok";//안쓸경우 view에서 ok를 찾는다.
    }

    @ResponseBody//RestController와 같은 효과 위에 Controller이기 때문에 써줘야 한다.
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,int age){

        log.info("username={}, age={}",username, age);
        return "ok";//안쓸경우 view에서 ok를 찾는다.
    }
    @ResponseBody//RestController와 같은 효과 위에 Controller이기 때문에 써줘야 한다.
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age){//required가 false일 경우 꼭 값이 안들어와도 된다. Integer는 Null 값을 가질수있다.

        log.info("username={}, age={}",username, age);
        return "ok";//안쓸경우 view에서 ok를 찾는다.
    }
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,//값이 없을경우 넣어는주는 값 defaultValue
            @RequestParam(required = false, defaultValue = "-1") int age){

        log.info("username={}, age={}",username, age);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMao(
            @RequestParam Map<String, Object> paramMap){

        log.info("username={}, age={}",paramMap.get("username"),paramMap.get("age"));
        return "ok";
    }
    /*
    @ResponseBody
    @RequestMapping("/mode-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        log.info("helloData={}",helloData);
        return "ok";
    }*/
    //위에처럼 쓴것을 생략가능
    @ResponseBody
    @RequestMapping("/mode-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){//hellodata 객체의 프로퍼터를 찾고 해당 프로퍼터의 setter를 호출해서 파라미터 값을 입력한다.
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/mode-attribute-v2")
    public String modelAttributeV2(HelloData helloData){//ModelAttribute 생략가능
        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }


}
