package com.dominique.utadamusic.service;

import com.dominique.utadamusic.domain.ListSong;

import java.util.List;

public interface ListSongService {

    boolean ifAdd(ListSong listSong);

    boolean updateListSongMsg(ListSong listSong);

    boolean deleteListSong(Integer songId);

    List<ListSong> allListSong();

    List<ListSong> listSongsOfSingers(Integer songListId);
}
