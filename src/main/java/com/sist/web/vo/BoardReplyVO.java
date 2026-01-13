package com.sist.web.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardReplyVO {
	private int no, bno;
	private String id, name, sex, msg, dbday;
	private Date regdate;
}
