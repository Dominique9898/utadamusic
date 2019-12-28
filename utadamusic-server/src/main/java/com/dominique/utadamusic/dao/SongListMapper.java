package com.dominique.utadamusic.dao;

import com.dominique.utadamusic.domain.SongList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "SongListDao")
public interface SongListMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SongList record);

    int insertSelective(SongList record);

    SongList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SongList record);

    int updateByPrimaryKeyWithBLOBs(SongList record);

    int updateByPrimaryKey(SongList record);
    int updateSongListMsg(SongList record);

    int updateSongListImg(SongList record);

    int deleteSongList(Integer id);

    List<SongList> listSongLists();
}