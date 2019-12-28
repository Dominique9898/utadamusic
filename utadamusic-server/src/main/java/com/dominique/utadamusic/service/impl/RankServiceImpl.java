package com.dominique.utadamusic.service.impl;

import com.dominique.utadamusic.dao.RankMapper;
import com.dominique.utadamusic.domain.Rank;
import com.dominique.utadamusic.service.RankService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    @Override
    public int selectAverScore(Long songListId) {
        return rankMapper.selectScoreSum(songListId)/rankMapper.selectRankNum(songListId);
    }

    @Override
    public boolean insert(Rank rank) {

        return rankMapper.insertSelective(rank) > 0 ? true:false;
    }
}
