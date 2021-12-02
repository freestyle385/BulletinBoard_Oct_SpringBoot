package com.sbs.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Article 전체를 @Data를 통해 값을 가져옴(private도 가져옴)
@AllArgsConstructor
@NoArgsConstructor
public
class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private int boardId;
	private String title;
	private String body;
	private int hitCount;
	private int goodReactionPoint;
	private int badReactionPoint;
	
	private String extra__writerName;
	private boolean isUsrAuthorized;
	
	public String getForPrintType1RegDate() {
		return regDate.substring(2, 16).replace(" ", "<br>");
	}
	
	public String getForPrintType1UpdateDate() {
		return updateDate.substring(2, 16).replace(" ", "<br>");
	}

	public String getForPrintType2RegDate() {
		return regDate.substring(2, 16);
	}

	public String getForPrintType2UpdateDate() {
		return updateDate.substring(2, 16);
	}
}
