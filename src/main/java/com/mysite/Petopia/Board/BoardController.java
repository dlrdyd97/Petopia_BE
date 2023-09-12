package com.mysite.Petopia.Board;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class BoardController {
	
	private BoardService boardService;

	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	Long boardid;
	String thumbnail = "";
	// 게시글 작성
	@RequestMapping("/board/write")
	public Long insertBoard(@RequestBody BoardDTO board) {
		thumbnail = UUID.randomUUID().toString().replaceAll("-", "")+".jpg";
		boardid = boardService.insertBoard(board.getAuthor(), board.getTitle(), board.getContent(), thumbnail, board.getCategory());
		return boardid;
	}

	// 파일 업로드
	@RequestMapping("/board/uploadfiles")
	public void uploadfiles(@RequestBody MultipartFile[] uploadfiles) throws IOException {
		for (MultipartFile file : uploadfiles) {
			if (thumbnail.length() != 0) {
				File storedFilename = new File(thumbnail);
				file.transferTo(storedFilename);
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setId(boardid);
				boardService.saveuploadfiles(storedFilename.toString(), boardDTO);
				thumbnail = "";
				continue;
			}
			if (!file.isEmpty()) {
				File storedFilename = new File(UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
				file.transferTo(storedFilename);
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setId(boardid);
				boardService.saveuploadfiles(storedFilename.toString(), boardDTO);
			}
		}
	}
	
	// 게시글 상세
	@PostMapping("/board/detail")
	public BoardDTO selectBoard(@RequestBody BoardDTO board) {
		return boardService.selectBoard(board.getId(), board.getCategory());
	}
	
	@PostMapping("/board/detailimg")
	public List<BoardImgDTO> selectBoardImg(@RequestBody BoardImgDTO boardimg) {
		return boardService.selectBoardImg(boardimg.getPost().getId());
	}
	
	// 게시글 삭제
	@PostMapping("/board/delete")
	public void deleteBoard(@RequestBody BoardDTO board) {
		boardService.deleteBoard(board.getId());
	}
	
	Long updateboardid;
	// 게시글 수정
	@RequestMapping("/board/update")
	public void updateBoard(@RequestBody BoardDTO board) {
		updateboardid = board.getId();
		boardService.updateBoard(board.getId(), board.getTitle(), board.getContent());
	}
	
	// 게시글 수정 파일 업로드
	@RequestMapping("/board/uploadfilesupdate")
	public void updateuploadfiles(@RequestBody MultipartFile[] uploadfiles) throws IOException {
		for (MultipartFile file : uploadfiles) {
			if (!file.isEmpty()) {
				File storedFilename = new File(UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
				file.transferTo(storedFilename);
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setId(updateboardid);
				boardService.saveuploadfiles(storedFilename.toString(), boardDTO);
			}
		}
	}
	
	// 여행추천 Best3
	@PostMapping("/travelboard/travelbest")
	public List<BoardDTO> selectTravelBoardBest(@RequestBody BoardDTO board) {
		return (List<BoardDTO>)boardService.selectTravelBoardBest(board.getCategory());
	}
	
	// 여행추천 All
	@PostMapping("/travelboard/travelall")
	public List<BoardDTO> selectBoardAll(@RequestBody BoardDTO board) {
		return (List<BoardDTO>)boardService.selectBoardAll(board.getCategory());
	}
	
	// 여행추천 Best5
	@PostMapping("/travelboard/travelbest5")
	public List<BoardDTO> selectTravelBoardBest5(@RequestBody BoardDTO board) {
		return (List<BoardDTO>)boardService.selectTravelBoardBest5(board.getCategory());
	}
	
	@PostMapping("/travelboard/search")
	public List<BoardDTO> selectTravelBoardSearch(@RequestBody BoardDTO board) {
		return (List<BoardDTO>)boardService.selectTravelBoardSearch(board.getTitle(), board.getContent());
	}
	
	@GetMapping("/myPostCount")
	public int myPostCountByWriterEmail(@RequestParam("email") String email) {
		return boardService.myPostcountByAuthorEmail(email);
	}
}
