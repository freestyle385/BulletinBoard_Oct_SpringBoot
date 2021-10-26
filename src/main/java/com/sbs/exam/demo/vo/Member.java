package com.sbs.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Article 전체를 @Data를 통해 값을 가져옴(private도 가져옴)
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private String loginId;
	private String loginPw;
	private String name;
	private String nickname;
	private String cellPhoneNo;
	private String email;
}
