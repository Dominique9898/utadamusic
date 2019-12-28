package com.dominique.utadamusic.service;


import com.dominique.utadamusic.domain.Rank;

public interface RankService {

    int selectAverScore(Long songListId);

    boolean insert(Rank rank);
}
