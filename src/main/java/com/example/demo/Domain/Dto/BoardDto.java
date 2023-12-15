package com.example.demo.Domain.Dto;

import lombok.Data;

@Data // 게시판 내용들
public class BoardDto {

        private int idx;
        private String title;
        private String content;
        private int replyCnt;

    }

