# QueryDsl = sql query 문
1. 게시글 신규 저장 , 댓글 달기
* service 로직
  *         BoardEntity post= BoardEntity.builder()
                    .title(boardDto.getTitle())
                    .content(boardDto.getContent())
                    .replyCnt(boardDto.getReplyCnt())
                    .build();
* query 문
  *     INSERT INTO TB_BOARD(TITLE, CONTENT, REPLYCNT)
            VALUES(데이터값, 데이터값, 데이터값);
  *     INSERT INTO TB_BOARD
            VALUES(데이터값, 데이터값, 데이터값);
      * 컬럼명을 생략할 경우 순서대로 필드의 값이 자동 대입


2. 게시글 수정
* service 로직
  *         BoardEntity post= BoardEntity.builder()
                    .idx(boardDto.getIdx())
                    .title(boardDto.getTitle())
                    .content(boardDto.getContent())
                    .replyCnt(boardDto.getReplyCnt())
                    .build();
* query 문
  *     UPDATE TB_BOARD 
          SET TITLE = '제목 수정';
  *     UPDATE TB_BOARD
          SET TITLE = '제목수정2'
          WHERE IDX = 21;     

3. 게시글 삭제
* service로직
   *        @Transactional
              public void deletePost(Integer idx) {
              boardRepository.deletePost(idx);   
             }
* query 문
  *     DELETE FROM TB_TABLE;
   *     DELETE FROM TB_TABLE WHERE IDX=21;

4. 게시글 불러오기, 댓글 불러오기
* queryDslImpl
   *     @Override
         public List<CommentDto.replyComment> findBoardList(Integer parentIdx) {
          
          return jpqlQueryFactory.select(
          Projections.fields(
          CommentDto.replyComment.class,
          commentEntity.idx,
          commentEntity.parentIdx,
          commentEntity.comment
             )
          )
          .from(commentEntity)
          .where(commentEntity.parentIdx.eq(parentIdx))
          .fetch();   //list
          }
* query 문
  *      SELECT *
          FROM TB_TABLE;
  *      SELECT
          FROM TB_COMMENT 
          WHERE PARENTIDX= 21;