package com.example.demo.Controller.Service;

import com.example.demo.Controller.Entity.TbBoardEntity;
import com.example.demo.Controller.Repository.TbBoardRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TbBoardService {

    @Autowired
    private TbBoardRepository tbBoardRepository;

    public List<TbBoardEntity> getBoardList() {
        System.out.println("서비스 도착!");
        //모든 로직의 결과를 리턴
        return tbBoardRepository.findAll();
    }

//    public List<TbBoardEntity> findTbBoardByInx(List<Integer> tbboardInx) {
//        return tbBoardRepository.findAllByIdIn(tbboardInx);
//    }



}
