package com.dominique.utadamusic.controller;

import com.alibaba.fastjson.JSONObject;
import com.dominique.utadamusic.domain.Collect;
import com.dominique.utadamusic.service.impl.CollectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Controller
public class CollectController {

    @Autowired
    private CollectServiceImpl collectService;

    //    添加收藏的歌曲
    @ResponseBody
    @RequestMapping(value = "/api/addCollections", method = RequestMethod.POST)
    public Object addCollections(HttpServletRequest req){

        JSONObject jsonObject = new JSONObject();
        String user_id = req.getParameter("userId");
        String song_id=req.getParameter("songId");

        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(user_id));
        collect.setSongId(Integer.parseInt(song_id));
        String date = new Date().toString();
        collect.setCreateTime(date);
        boolean res = collectService.addCollection(collect);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "收藏成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "收藏失败");
            return jsonObject;
        }
    }


//    删除收藏的歌曲
    @RequestMapping(value = "/api/deleteCollects", method = RequestMethod.GET)
    public Object deleteCollects(HttpServletRequest req){
        String id = req.getParameter("userId");
        String song_id = req.getParameter("songId");
        return collectService.deleteCollect(Integer.parseInt(id),Integer.parseInt(song_id));
    }

//    更新收藏
    @ResponseBody
    @RequestMapping(value = "/api/updateCollectMsgs", method = RequestMethod.POST)
    public Object updateCollectMsgs(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String user_id = req.getParameter("userId").trim();
         String song_id = req.getParameter("songId").trim();

        Collect collect = new Collect();
        collect.setId(Integer.parseInt(id));
        collect.setUserId(Integer.parseInt(user_id));
         collect.setSongId(Integer.parseInt(song_id));

        boolean res = collectService.updateCollectMsg(collect);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "修改成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "修改失败");
            return jsonObject;
        }
    }

//    返回所有用户收藏列表
    @RequestMapping(value = "/allCollects", method = RequestMethod.GET)
    public Object allCollects(){
        return collectService.allCollect();
    }

//    返回的制定用户ID收藏列表
    @RequestMapping(value = "/myCollection", method = RequestMethod.GET)
    public Object myCollection(HttpServletRequest req){
        String userId = req.getParameter("userId");
        return collectService.myCollectOfSongs(Integer.parseInt(userId));
    }
}
