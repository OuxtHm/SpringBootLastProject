package com.sist.web.vo;

import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NoticeVO {
	private int no, hit, filecount;
	private String type, name, subject, content, dbday, filename;
	private Date regdate;
	private List<MultipartFile> files;
}
