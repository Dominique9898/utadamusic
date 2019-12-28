package com.dominique.utadamusic.service.impl;


import com.dominique.utadamusic.dao.CollectMapper;
import com.dominique.utadamusic.domain.Collect;
import com.dominique.utadamusic.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public boolean addCollection(Collect collect) {
        return collectMapper.addCollection(collect) > 0 ? true:false;
    }

    @Override
    public boolean existSongId(Integer songId) {
        return collectMapper.existSongId(songId)>0 ? true:false;
    }

    @Override
    public boolean updateCollectMsg(Collect collect) {
        return collectMapper.updateCollectMsg(collect) >0 ?true:false;
    }

    @Override
    public boolean deleteCollect(Integer id,Integer songId) {
        return collectMapper.deleteCollect(id,songId) >0 ?true:false;
    }

    @Override
    public List<Collect> allCollect()

    {
        return collectMapper.allCollect();
    }

    @Override
    public List<Collect> myCollectOfSongs(Integer userId)

    {
        return collectMapper.myCollectOfSongs(userId);
    }
}
