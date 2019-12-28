package com.dominique.utadamusic.service.impl;


import com.dominique.utadamusic.dao.SingerMapper;
import com.dominique.utadamusic.domain.Singer;
import com.dominique.utadamusic.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Override
    public boolean updateSingerMsg(Singer singer) {
        return singerMapper.updateSingerMsg(singer) >0 ?true:false;
    }

    @Override
    public boolean updateSingerImg(Singer singer) {

        return singerMapper.updateSingerImg(singer) >0 ?true:false;
    }

    @Override
    public boolean deleteSinger(Integer id) {
        return singerMapper.deleteSinger(id) >0 ?true:false;
    }

    @Override
    public List<Singer> listSingers()
    {
        return singerMapper.listSingers();
    }

    @Override
    public boolean ifAdd(Singer singer)  {

        return singerMapper.insertSelective(singer) > 0 ? true : false;
    }

    @Override
    public List<Singer> searachSinger(String name)

    {
        return singerMapper.searachSinger(name);
    }
}
