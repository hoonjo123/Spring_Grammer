package com.encore.basic.servletjsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet-jsp-post")

public class HelloServletJspPost extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        //콘솔에 출력

        System.out.println("name :" + name);
        System.out.println("email :" + email);
        System.out.println("password :" + password);

        //응답 - header를 setting한 것.
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8"); //설정 맞춰주기.
        //응답 - body를 setting한 것.
        PrintWriter out = resp.getWriter();
        out.print("ok");
        out.flush();
        //flush 는 ? 버퍼를 통해 조립이 이루어지므로, 버퍼를 비우는 과정.
    }
}
