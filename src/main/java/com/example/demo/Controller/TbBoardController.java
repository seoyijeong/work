package com.example.demo.Controller;


import com.example.demo.Controller.Entity.TbBoardEntity;
import com.example.demo.Controller.Repository.TbBoardRepository;
import com.example.demo.Controller.Service.TbBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/board")
public class TbBoardController {

    @Autowired
    private TbBoardService tbBoardService;

    @GetMapping("/list")
    public List<TbBoardEntity> getBoardList() {
        System.out.println("호출이 성공적입니다.");
        return tbBoardService.getBoardList();
    }

//    List<Long> tbboardInx = Arrays.asList(1L, 2L, 3L);
//    List<TbBoardEntity> products = TbBoardService.findTbBoardByInx(tbboardInx);
//commit 확인
}
