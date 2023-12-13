package com.example.demo.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data // 게시판 내용들
public class BoardDto {

        private int idx;
        private String title;
        private String content;
        private int replyCnt;

    }

