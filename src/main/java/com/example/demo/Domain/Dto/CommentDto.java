package com.example.demo.Domain.Dto;

import lombok.Data;

import java.util.List;


public class CommentDto {

    @Data // 최종적으로 나와야 할 모양
    public static class comment{   //게시판정보
        private Integer idx;
        private String title;
        private String content;


        private List<replyComment> reply;  //댓글 정보리스트
    }

    @Data // 댓글 테이블 정보들
    public static class replyComment{   //댓글정보
        private Integer idx;
        private Integer parentIdx;
        private String comment;
    }
}
