package com.example.demo.comments.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.comments.dto.CommentsDTO;
import com.example.demo.comments.service.CommentsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommentsController {
	
	private final CommentsService commentsService;
	
	/* 댓글 전체 조회 */
	@ResponseBody
	@GetMapping("/comments/comments")
	public ResponseEntity<List<CommentsDTO>> getComments(CommentsDTO commentsDTO) {
	    List<CommentsDTO> allComments = commentsService.getCommentsByBoardNo(commentsDTO.getBoardNo());
	    
	    return ResponseEntity.ok(allComments);
	}
	
	/* 댓글 쓰기 */
	@ResponseBody
	@PostMapping("/comments/Comments")
	 public ResponseEntity<List<CommentsDTO>> addComment(CommentsDTO commentsDTO) {
		commentsService.commentsInsert(commentsDTO);
               
//		CommentsDTO newComment = commentsService.getCommentById(commentsDTO.getCommentNo());		
//		System.out.println("newComment : " + commentsService.getCommentById(commentsDTO.getCommentNo()));
//		return ResponseEntity.ok(newComment);        
		 
		List<CommentsDTO> allComments = commentsService.getCommentsByBoardNo(commentsDTO.getBoardNo());
		
		return ResponseEntity.ok(allComments); 
    }
	
	/* 댓글 삭제 */
	@ResponseBody
	@PostMapping("/comments/CommentsDelete")
	public int commentDelete(@RequestBody Map<String, Integer> request) {
	    int commentNo = request.get("commentNo");
	    int result = commentsService.commentDelete(commentNo);
	    return result;
	}
	
	/* 댓글 수정 */
	@ResponseBody
	@PostMapping("/comments/ModifyComment")
	public int modifyComment(@RequestBody Map<String, Object> request) {
	    String commentContent = (String) request.get("commentContent");
	    int commentNo = (Integer) request.get("commentNo");
	    
	    System.out.println("commentContent: " + commentContent);
        System.out.println("commentNo: " + commentNo);
	    
	    int result = commentsService.modifyComment(commentNo, commentContent);
	    
	    System.out.println("result : " + result); 
	    
	    return result;
	}

}
