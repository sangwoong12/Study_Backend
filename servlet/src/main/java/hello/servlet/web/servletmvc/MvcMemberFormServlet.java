package hello.servlet.web.servletmvc;

import hello.servlet.basic.HelloServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HelloServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher Dispatcher = request.getRequestDispatcher(viewPath);//controller -> view
        Dispatcher.forward(request, response);
        //redirect 와 다르게 redirect의 경우 실제 클라이언트에 응답이 나갔다가, 클라이언트가 redirect 경로로 다시 요청해서 클라이언트가 인지하는데 forword는 서부 내부에서 일어나는 호출이라서 클라이언트는 알수없다.
    }
}
