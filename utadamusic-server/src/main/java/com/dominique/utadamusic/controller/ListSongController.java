package com.dominique.utadamusic.controller;

import com.alibaba.fastjson.JSONObject;
import com.dominique.utadamusic.domain.ListSong;
import com.dominique.utadamusic.service.impl.ListSongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class ListSongController {

    @Autowired
    private ListSongServiceImpl listSongService;

//    给歌单添加歌曲
    @ResponseBody
    @RequestMapping(value = "/api/addListSong", method = RequestMethod.POST)
    public Object addListSong(HttpServletRequest req){
    JSONObject jsonObject = new JSONObject();
    String song_id = req.getParameter("songId").trim();
    String song_list_id = req.getParameter("songListId").trim();

    ListSong listsong = new ListSong();
    listsong.setSongId(Integer.parseInt(song_id));
    listsong.setSongListId(Integer.parseInt(song_list_id));

    boolean res = listSongService.ifAdd(listsong);
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

//    删除歌单里的歌曲
    @RequestMapping(value = "/api/deleteListOfSong", method = RequestMethod.GET)
    public Object deleteListOfSong(HttpServletRequest req){
        String songId = req.getParameter("songId");
        return listSongService.deleteListSong(Integer.parseInt(songId));
    }

//    更新歌单里面的歌曲信息
    @ResponseBody
    @RequestMapping(value = "/api/updateListSongMsgs", method = RequestMethod.POST)
    public Object updateListSongMsgs(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String song_id = req.getParameter("songId").trim();
        String song_list_id = req.getParameter("songListId").trim();

        ListSong listsong = new ListSong();
        listsong.setId(Integer.parseInt(id));
        listsong.setSongId(Integer.parseInt(song_id));
        listsong.setSongListId(Integer.parseInt(song_list_id));

        boolean res = listSongService.updateListSongMsg(listsong);
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

//    返回歌单里包含的所有歌曲
    @RequestMapping(value = "/allListSongs", method = RequestMethod.GET)
    public Object allListSongs(){
        return listSongService.allListSong();
    }

//    返回歌单里指定歌单ID的歌曲
    @RequestMapping(value = "/listSongOfSingers", method = RequestMethod.GET)
    public Object toSongList(HttpServletRequest req){
        String songListId = req.getParameter("songListId");
        return listSongService.listSongsOfSingers(Integer.parseInt(songListId));
    }
}
