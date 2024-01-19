package com.encore.basic.servletjsp;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//jsp를 통한 get/post
//controller가 아닌 webservlet을 통해 라우팅을 하고 있다.
@WebServlet("/hello-servlet-jsp-get")
public class HelloServletJspGet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //기본패턴 Req를 받아서 Res를 리턴해주는 형식
        resp.getHeader("Content-Type");
        req.setAttribute("myData","jsp test data");
        req.getRequestDispatcher("/WEB-INF/views/hello-jsp.jsp").forward(req, resp);
    }



    //service() 메서드는 servlet에 들어오는 모든요청 (get,post,put,delete)을 처리한다.
    //다만, 구체적으로 doGet, doPost등의 메서드를 쓰는게 더 좋은 문법.


//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        req.setAttribute("myData","jsp test data");
//        req.getRequestDispatcher("/WEB-INF/views/hello-jsp.jsp").forward(req, resp);
//    }

}
