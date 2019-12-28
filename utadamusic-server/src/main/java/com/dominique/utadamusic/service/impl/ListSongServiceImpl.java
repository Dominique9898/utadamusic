package com.dominique.utadamusic.service.impl;


import com.dominique.utadamusic.dao.ListSongMapper;
import com.dominique.utadamusic.domain.ListSong;
import com.dominique.utadamusic.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public List<ListSong> allListSong()
    {
        return listSongMapper.allListSong();
    }

    @Override
    public boolean updateListSongMsg(ListSong listSong) {
        return listSongMapper.updateListSongMsg(listSong) >0 ?true:false;
    }

    @Override
    public boolean deleteListSong(Integer songId) {
        return listSongMapper.deleteListSong(songId) >0 ?true:false;
    }

    @Override
    public boolean ifAdd(ListSong listSong)
    {

        return listSongMapper.insertSelective(listSong) > 0?true:false;
    }

    @Override
    public List<ListSong> listSongsOfSingers(Integer songListId)
    {
        return listSongMapper.listSongsOfSingers(songListId);
    }

}
