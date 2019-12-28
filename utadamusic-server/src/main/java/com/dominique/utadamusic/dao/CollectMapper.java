package com.dominique.utadamusic.dao;

import com.dominique.utadamusic.domain.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "CollectDao")
@Mapper
public interface CollectMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    int addCollection(Collect collect);

    int existSongId(Integer songId);

    int updateCollectMsg(Collect collect);

    int deleteCollect(Integer id,Integer songId);

    List<Collect> allCollect();

    List<Collect> myCollectOfSongs(Integer userId);
}
