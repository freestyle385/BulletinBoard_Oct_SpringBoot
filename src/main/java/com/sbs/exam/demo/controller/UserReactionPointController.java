package com.sbs.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.service.ReactionPointService;
import com.sbs.exam.demo.vo.Rq;

@Controller
public class UserReactionPointController {
	@Autowired // 등록된 컴포넌트를 자동으로 연결 해줌
	private ReactionPointService reactionPointService;
	@Autowired
	private Rq rq;

	// 액션 메소드 시작

	@RequestMapping("/usr/reactionPoint/increaseGoodRp")
	@ResponseBody
	public int increaseGoodRp(int id) {
		reactionPointService.increaseGoodRp(id);
		int goodRp = reactionPointService.getGoodRpCount(id);

		reactionPointService.addGoodRpInfo(id, (int) rq.getLoginedMemberId());

		return goodRp;
	}

	@RequestMapping("/usr/reactionPoint/increaseBadRp")
	@ResponseBody
	public int increaseBadRp(int id) {
		reactionPointService.increaseBadRp(id);
		int badRp = reactionPointService.getBadRpCount(id);

		reactionPointService.addBadRpInfo(id, (int) rq.getLoginedMemberId());

		return badRp;
	}

	@RequestMapping("/usr/reactionPoint/decreaseGoodRp")
	@ResponseBody
	public int decreaseGoodRp(int id) {
		reactionPointService.decreaseGoodRp(id);
		int goodRp = reactionPointService.getGoodRpCount(id);

//		reactionPointService.addGoodRpInfo(id, (int) rq.getLoginedMemberId());

		return goodRp;
	}

	// 액션 메소드 끝
}