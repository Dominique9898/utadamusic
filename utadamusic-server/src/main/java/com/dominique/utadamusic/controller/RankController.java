package com.dominique.utadamusic.controller;

import com.alibaba.fastjson.JSONObject;
import com.dominique.utadamusic.domain.Rank;
import com.dominique.utadamusic.service.impl.RankServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class RankController {

    @Autowired
    private RankServiceImpl rankService;

//    提交评分
    @ResponseBody
    @RequestMapping(value = "/api/pushRank", method = RequestMethod.POST)
    public Object signup(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String songListId = req.getParameter("songListId").trim();
        String consumerId = req.getParameter("consumerId").trim();
        String score = req.getParameter("score").trim();

        Rank rank = new Rank();
        rank.setSongListId(Long.parseLong(songListId));
        rank.setConsumerId(Long.parseLong(consumerId));
        rank.setScore(Integer.parseInt(score));

        boolean res = rankService.insert(rank);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评价成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评价失败");
            return jsonObject;
        }
    }

//    获取指定歌单的评分
    @RequestMapping(value = "/api/getRank", method = RequestMethod.GET)
    public Object ranks(HttpServletRequest req){
        String songListId = req.getParameter("songListId");
        return rankService.selectAverScore(Long.parseLong(songListId));
    }
}
