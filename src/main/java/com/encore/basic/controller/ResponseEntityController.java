package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//응답값을 주는 방법 //상태처리

@RestController
@RequestMapping("response/entity")
public class ResponseEntityController {
    //@ResponseStatus Annotation 방식
    @GetMapping("responsestatus")
    @ResponseStatus(HttpStatus.CREATED)
    public String ResponseStatus(){
        return "ok";
    }
    //@ResponseStatus Annotation 방식

    @GetMapping("responsestatus2")
    @ResponseStatus(HttpStatus.CREATED)
    public Member ResponseStatus2(){
        Member member = new Member("kim", "kim@naver.com", "1234");
        return member; //201
        }

    //ResponseEntity객체를 직접생성한 방식
    @GetMapping("custom1")
    public ResponseEntity<Member> custom1(){
        Member member = new Member("kim", "kim@naver.com", "1234");
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    //404 error
    @GetMapping("custom2")
    public ResponseEntity<String> custom2(){
        String html = "<h1>없는 ID입니다</h1>";
        return new ResponseEntity<>(html,HttpStatus.NOT_FOUND);
    }

    //Map형태의 메시지 커스텀

    public static ResponseEntity<Map<String, Object>> errorResponseMessage(HttpStatus status, String message){
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(status.value()));
        body.put("status message", status.getReasonPhrase());
        body.put("error message", message);
        return new ResponseEntity<>(body, status);
        // head에 value가 들어가지만 body에 한번 더 출력하면 보기 편하니까.
        //    "error message": "Internal Server Error",
        //    "status": "500"
    }

    //status 201
    //message : 객체,


    public static ResponseEntity<Map<String, Object>> responseMessage(HttpStatus status, Object object) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(status.value()));
        //핵심정보는 Body에!!!
        body.put("message", object);
        return new ResponseEntity<>(body, status);

        //{
        //    "message": {
        //        "id": 0,
        //        "name": "kim",
        //        "email": "kim@naver.com",
        //        "password": "12",
        //        "create_time": null,
        //        "updated_time": null
        //    },
        //    "status": "201"
        //}
    }

    //메서드 체이닝방식 : Response Entity의 클래스메서드 사용
    //
    @GetMapping("chaning1")
    public ResponseEntity<Member> chaning1(){
        Member member = new Member("kim", "kim@naver.com", "1234");
        return ResponseEntity.ok(member);
    }

    //예외를 던져보자
    @GetMapping("chaning2")
    public ResponseEntity<Member> chaning2(){
        return ResponseEntity.notFound().build();
    }
    @GetMapping("chaning3")
    public ResponseEntity<Member> chaning3(){
        Member member = new Member("kim", "kim@naver.com", "1234");
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }
}
