package com.sist.web.vo;

import java.util.*;

import lombok.Data;

@Data
public class BoardVO {
	private int no, hit, replycount;
	private String name, subject, content,  dbday, pwd;
	private Date regdate;
}
