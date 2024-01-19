package com.encore.basic.servletjsp;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet-rest-get")
public class HelloServletRestGet extends HttpServlet {

    //Hello객체 조립하기 //resp:
    //content type : json
    //out.print("hello") -> writeValueOfString (json형태로 만들어주는 직렬화)

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hello hello = new Hello();
        hello.setName("shin");
        hello.setEmail("shin@naver.cm");
        hello.setPassword("1234");

        ObjectMapper objectMapper = new ObjectMapper();
        String json_hello = objectMapper.writeValueAsString(hello);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(json_hello);

        out.flush();
        }
}
