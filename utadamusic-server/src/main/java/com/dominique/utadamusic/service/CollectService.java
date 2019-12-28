package com.dominique.utadamusic.service;


import com.dominique.utadamusic.domain.Collect;

import java.util.List;

public interface CollectService {

    boolean addCollection(Collect collect);

    boolean existSongId(Integer songId);

    boolean updateCollectMsg(Collect collect);

    boolean deleteCollect(Integer id,Integer songId);

    List<Collect> allCollect();

    List<Collect> myCollectOfSongs(Integer userId);
}
