package com.example.demo.Controller;

import com.example.demo.Domain.Dto.BoardDto;
import com.example.demo.Domain.Dto.CommentDto;
import com.example.demo.Domain.Entity.BoardEntity;
import com.example.demo.Service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Controller
사용자의 요청을 처리 한 후 지정된 뷰에 모델 객체를 넘겨주는 역할
즉 사용자의 요청이 진입하는 지점이며 요청에 따라 어떤 처리를 할지 결정을 Service에 넘겨줍니다.
그후 Service에서 실질적으로 처리한 내용을 View에게 넘겨줍니다.
*/

@RestController
//restful 웹 서비스를 구축하는 맥락에서 사용 컨트롤러는 들어오는  http 요청을 처리하고 적절한 http응답을 반환하는 클래스를 나타냄
/*@RestController 클래스 내의 메서드는 일반적으로 HTTP 메서드 주석 (@GetMapping, @PostMapping, @PutMapping, @DeleteMapping 등)으로
주석을 달아 해당 메서드가 처리할 수 있는 HTTP 요청 유형을 정의합니다.
 */
@CrossOrigin("*") // 기본적으로 '모든 도메인, 모든 요청방식' 에 대해 허용 한다는 뜻
@Slf4j
@RequiredArgsConstructor  //@Autowired final이 붙거나 @NotNull이 붙은 필드의 생성자를 자동 생성해주는 롬복어노테이션
@RequestMapping("/board")
public class BoardController {


    private final BoardService boardService;

///////////////////// 게시글 불러오기
    @GetMapping("/list")
    public List<BoardEntity> getBoardList() {
        System.out.println("호출이 성공적입니다.");

        return boardService.getBoardList();
        //url로 /list를 요청시 return호출(service의 getBoardList()를 호출)
        //controller의 getBoardList()의 이름만 같을뿐  --> 이름을 달리 만들기!!
    }

    //parentIdx 댓글에 대한 게시판 id (idx : 게시판 번호)

    //////////////게시글 상세보기 -> 해당 번호 게시글과 댓글보기
    @GetMapping("/reply/{parentIdx}") //해당 URL요청 처리 선언
    public CommentDto.comment getCommentList(@PathVariable(name = "parentIdx") Integer idx) {
        log.info("id=" + idx);

        return boardService.getCommentList(idx);
    }
    ///////////////////게시글 신규, 저장
    @PostMapping("/save")
    public void savePost(@RequestBody BoardDto boardDto){
        boardService.savePost(boardDto);
    }


    //////////////////게시글 수정
    //path variable로 넘어온 id를 PostVo 타입의 객체의 id로 설정
    /*PUT request가 올 때 Body에 담겨온 데이터를 PostVo 타입의 객체에 담아
    postService의 updatePost 메서드에 넘겨주어 id로 검색한 Post 데이터를 수정하는 로직을 수행*/
    @PutMapping("/update/{idx}")
    //@RequestBody 수정사항을 받기
    public void updatePost(@PathVariable Integer idx, @RequestBody BoardDto boardDto){
        boardDto.setIdx(idx);
        boardService.savePost(boardDto);
    }

    ////////////////게시글 삭제
    @DeleteMapping("/delete/{idx}")
    public void deletePost(@PathVariable Integer idx){
        boardService.deletePost(idx);
    }

    ////////////////////댓글 등록
    @PostMapping("/saveReply")
    public void saveReply(@RequestBody CommentDto.replyComment replyComment){
        boardService.saveReply(replyComment);
    }

}
