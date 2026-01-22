package com.sist.web.manager;

import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.web.vo.RealFindDataVO;

public class DataCollection {
	public static List<RealFindDataVO> dataCollect() 
	{
		List<RealFindDataVO> list = new ArrayList<>();

		try {
			Document doc = Jsoup.connect("https://rank.ezme.net").get();
			Elements words = doc.select(".rank_word");
			Elements images = doc.select(".rank_img");

			for (int i = 0; i < words.size(); i++) {
				String w = words.get(i).text();
				String img = images.get(i).attr("data-pagespeed-lazy-src");
				RealFindDataVO vo = new RealFindDataVO();
				vo.setWord(w);
				vo.setImages(img);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
