package com.dominique.utadamusic.dao;

import com.dominique.utadamusic.domain.Song;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "SongDao")
@Mapper
public interface SongMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(Song record);

    int insertSelective(Song record);

    Song selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Song record);

    int updateByPrimaryKeyWithBLOBs(Song record);

    int updateByPrimaryKey(Song record);
    int updateSongMsg(Song record);

    int updateSongUrl(Song record);

    int updateSongPic(Song record);

    int deleteSong(Integer id);

    List<Song> listSongs();

    List<Song> listSongsOfSinger(Integer singerId);

    List<Song> listSongsOfSongs(Integer id);

    List<Song> searachSongLists(String name);

    List<Song> searchSongBySinger(String name);
}