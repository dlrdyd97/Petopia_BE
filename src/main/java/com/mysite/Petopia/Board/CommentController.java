package com.mysite.Petopia.Board;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	// 댓글 작성
	@RequestMapping("/write")
	public void insertComment(@RequestBody CommentDTO comment) {
		commentService.insertComment(comment.getPost(), comment.getAuthor(), comment.getContent());
	}
	
	// 댓글 리스트
	@RequestMapping("/findall")
	public List<CommentDTO> findallComment(@RequestBody CommentDTO comment) {
		return commentService.findallComment(comment.getPost().getId());
	}
}
