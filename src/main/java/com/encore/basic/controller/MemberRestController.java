package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/rest")
public class MemberRestController {
    private final MemberService memberService;
    @Autowired
    public MemberRestController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("members")
    public List<MemberResponseDto> members(){
        return memberService.findAll();
    }

    //회원가입

    @GetMapping("members/create")
    public String memberCreate(@RequestBody MemberRequestDto memberRequestDto){
        memberService.createMember(memberRequestDto);
        return "ok";
    }
    @GetMapping("member/find/{id}")

    public ResponseEntity<Map<String, Object>> findMember(@PathVariable int id) {
        try {
            MemberResponseDto memberResponseDto = memberService.findById(id);
            return ResponseEntityController.responseMessage(HttpStatus.OK, memberResponseDto);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return ResponseEntityController.errorResponseMessage(HttpStatus.NOT_FOUND,e.getMessage());// or another custom method for NOT_FOUND
        }
    }


    @DeleteMapping("member/delete/{id}")
    public String deleteMember(@PathVariable int id){
            memberService.deleteMember(id);
            return "ok";
    }
    @PatchMapping("member/update/{id}")
    public MemberResponseDto updateMember(@RequestBody MemberRequestDto memberRequestDto){
            memberService.updateMember(memberRequestDto);
            return memberService.findById(memberRequestDto.getId());
    }
}