package com.encore.basic.servletjsp;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello-servlet-rest-post")
public class HelloServletRestPost extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        BufferedReader br = req.getReader();
//        StringBuilder sb = new StringBuilder();
//        String line = null;
//
//        while ((line = br.readLine()) != null) {
//            sb.append(line);
//        }
        ObjectMapper objectMapper = new ObjectMapper();
        Hello hello = objectMapper.readValue(req.getReader(), Hello.class);
        System.out.println(hello);

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8"); //설정 맞춰주기.
        //응답 - body를 setting한 것.
        PrintWriter out = resp.getWriter();
        out.print("ok");
        out.flush();
    }
}
