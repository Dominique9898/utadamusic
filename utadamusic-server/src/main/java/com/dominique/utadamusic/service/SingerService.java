package com.dominique.utadamusic.service;


import com.dominique.utadamusic.domain.Singer;

import java.util.List;

public interface SingerService {

    boolean ifAdd(Singer singer);

    boolean updateSingerMsg(Singer singer);

    boolean updateSingerImg(Singer singer);

    boolean deleteSinger(Integer id);

    List<Singer> listSingers();

    List<Singer> searachSinger(String name);
}
