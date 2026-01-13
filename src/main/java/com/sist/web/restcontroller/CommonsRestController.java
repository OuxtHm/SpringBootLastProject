package com.sist.web.restcontroller;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.CommonsReplyService;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommonsRestController {
	private final CommonsReplyService cService;

	public Map commonsData(int page, int cno) {
		Map map = new HashMap();
		// DB 연동
		List<CommonsReplyVO> list = cService.commonsReplyListData(cno, (page - 1) * 10);
		int totalPage = cService.commonsReplyTotalPage(cno);
		final int BLOCK = 5;
		int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((page - 1) / BLOCK * BLOCK) + BLOCK;

		if (endPage > totalPage)
			endPage = totalPage;

		map.put("list", list);
		map.put("curpage", page);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalPage", totalPage);
		map.put("cno", cno);
		map.put("count", list.size());

		return map;
	}

	@GetMapping("/commons/list_vue/")
	public ResponseEntity<Map> commons_list_vue(@RequestParam("page") int page, @RequestParam("cno") int cno) {
		Map map = new HashMap();

		try {
			map = commonsData(page, cno);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PostMapping("/commons/insert_vue/")
	public ResponseEntity<Map> commons_insert_vue(@RequestBody CommonsReplyVO vo, HttpSession session) {
		Map map = new HashMap();

		try {
			String id = (String) session.getAttribute("userid");
			String name = (String) session.getAttribute("username");
			String sex = (String) session.getAttribute("sex");
			vo.setId(id);
			vo.setName(name);
			vo.setSex(sex);

			cService.commonsReplyInsert(vo);
			map = commonsData(vo.getPage(), vo.getCno());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@DeleteMapping("/commons/delete_vue/")
	public ResponseEntity<Map> commons_delete_vue(@RequestParam("no") int no, @RequestParam("page") int page, @RequestParam("cno") int cno) 
	{
		Map map = new HashMap();
		try {
			// 처리
			cService.commonsDelete(no);
			map = commonsData(page, cno);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
