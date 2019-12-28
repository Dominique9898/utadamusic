package com.dominique.utadamusic.service.impl;


import com.dominique.utadamusic.dao.SongListMapper;
import com.dominique.utadamusic.domain.SongList;
import com.dominique.utadamusic.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    public boolean updateSongListMsg(SongList songList) {
        return songListMapper.updateSongListMsg(songList) >0 ?true:false;
    }

    @Override
    public boolean deleteSongList(Integer id) {
        return songListMapper.deleteSongList(id) >0 ?true:false;
    }

    @Override
    public List<SongList> listSongLists()
    {
        return songListMapper.listSongLists();
    }

    @Override
    public boolean ifAdd(SongList songList)
    {

        return songListMapper.insertSelective(songList) > 0?true:false;
    }

    @Override
    public boolean updateSongListImg(SongList songList) {

        return songListMapper.updateSongListImg(songList) >0 ?true:false;
    }
}
