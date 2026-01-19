package com.sist.web.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
// 추상화 => ChatModel => GPT / GEN / En / Oll 
public class CommonsReplyRestController {
   private final CommonsReplyService cService;
   // 공통 모듈 => 반복 제거 
   public Map commonsData(int page,int cno)
   {
	   Map map=new HashMap();
	   // db연동 
	   List<CommonsReplyVO> list=
			cService.commonsReplyListData(cno, (page-1)*10);
	   int totalpage=cService.commonsReplyTotalPage(cno);
	   final int BLOCK=5;
	   int startPage=((page-1)/BLOCK*BLOCK)+1;
	   int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
	   
	   if(endPage>totalpage)
		   endPage=totalpage;
	   
	   map.put("list", list);
	   map.put("curpage", page);
	   map.put("startPage", startPage);
	   map.put("endPage", endPage);
	   map.put("totalpage", totalpage);
	   map.put("cno", cno);
	   map.put("count", list.size());
	   return map;
   }
   
   // 목록 
   @GetMapping("/commons/list_vue/")
   public ResponseEntity<Map> commons_list_vue(
      @RequestParam("page") int page,
      @RequestParam("cno") int cno
   )
   {
	   Map map=new HashMap();
	   try
	   {
		   map=commonsData(page, cno);
	   }catch(Exception ex)
	   {
		   return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	   return new ResponseEntity<>(map,HttpStatus.OK);
   }
   @PostMapping("/commons/insert_vue/")
   public ResponseEntity<Map> commons_insert_vue(
     @RequestBody CommonsReplyVO vo,
     HttpSession session
   )
   {
	   Map map=new HashMap();
	   try
	   {
		   String id=(String)session.getAttribute("userid");
		   String name=(String)session.getAttribute("username");
		   String sex=(String)session.getAttribute("sex");
		   vo.setId(id);
		   vo.setName(name);
		   vo.setSex(sex);
		   cService.commonsReplyInsert(vo);
		   map=commonsData(vo.getPage(), vo.getCno());
	   }catch(Exception ex)
	   {
		   return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	   return new ResponseEntity<>(map,HttpStatus.OK);
   }
   
   @DeleteMapping("/commons/delete_vue/")
   public ResponseEntity<Map> commons_delete_vue(
		      @RequestParam("no") int no,
		      @RequestParam("page") int page,
		      @RequestParam("cno") int cno
   )
   {
	 System.out.println("no:"+no);
	 System.out.println("cno:"+cno);
	 System.out.println("page:"+page);
	 Map map=new HashMap();
	 try
	 {
		// 처리 
		
		cService.commonsDelete(no);
	    map=commonsData(page, cno);
	 }catch(Exception ex)
	 {
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 return new ResponseEntity<>(map,HttpStatus.OK);
   }
   @PutMapping("/commons/update_vue/")
   public ResponseEntity<Map> commons_update(
	 @RequestBody CommonsReplyVO vo	   
   )
   {
	   Map map=new HashMap();
	   try
	   {
		   cService.commonsMsgUpdate(vo);
		   map=commonsData(vo.getPage(), vo.getCno());
	   }catch(Exception ex)
	   {
		 return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	    return new ResponseEntity<>(map,HttpStatus.OK);
	   
   }
   @PostMapping("/commons/reply_reply_insert_vue/")
   public ResponseEntity<Map> commons_reply_reply(
     @RequestBody CommonsReplyVO vo,HttpSession session
   )
   {
	   Map map=new HashMap();
	   try
	   {
		   String id=(String)session.getAttribute("userid");
		   String name=(String)session.getAttribute("username");
		   String sex=(String)session.getAttribute("sex");
		   vo.setId(id);
		   vo.setName(name);
		   vo.setSex(sex);
		   cService.commonsReplyReplyInsert(vo);
		   map=commonsData(vo.getPage(), vo.getCno());
	   }catch(Exception ex)
	   {
		 return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	    return new ResponseEntity<>(map,HttpStatus.OK);
	    
   }
}