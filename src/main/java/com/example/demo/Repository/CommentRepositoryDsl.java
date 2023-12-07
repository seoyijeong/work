package com.example.demo.Repository;

import com.example.demo.Domain.Dto.CommentDto;
import com.example.demo.Domain.Entity.BoardEntity;

import java.util.List;

public interface CommentRepositoryDsl {
    CommentDto.comment findComment(Integer idx);
}
