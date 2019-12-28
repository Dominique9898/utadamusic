package com.dominique.utadamusic.service.impl;

import com.dominique.utadamusic.dao.SongMapper;
import com.dominique.utadamusic.service.SongService;
import com.dominique.utadamusic.domain.Song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    public List<Song> listSongs()
    {
        return songMapper.listSongs();
    }

    @Override
    public boolean ifAdd(Song song)
    {

        return songMapper.insertSelective(song) > 0?true:false;
    }

    @Override
    public boolean updateSongMsg(Song song) {
        return songMapper.updateSongMsg(song) >0 ?true:false;
    }

    @Override
    public boolean updateSongUrl(Song song) {

        return songMapper.updateSongUrl(song) >0 ?true:false;
    }

    @Override
    public boolean updateSongPic(Song song) {

        return songMapper.updateSongPic(song) >0 ?true:false;
    }

    @Override
    public boolean deleteSong(Integer id) {
        return songMapper.deleteSong(id) >0 ?true:false;
    }

    @Override
    public List<Song> listSongsOfSinger(Integer singerId)

    {
        return songMapper.listSongsOfSinger(singerId);
    }

    @Override
    public List<Song> listSongsOfSongs(Integer id)

    {
        return songMapper.listSongsOfSongs(id);
    }

    @Override
    public List<Song> searachSongLists(String name)

    {
        return songMapper.searachSongLists(name);
    }

    @Override
    public List<Song> searchSongBySinger(String name)
    {
        return songMapper.searchSongBySinger(name);
    }
}
