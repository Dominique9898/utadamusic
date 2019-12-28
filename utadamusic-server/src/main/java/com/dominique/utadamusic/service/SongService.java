package com.dominique.utadamusic.service;

import com.dominique.utadamusic.domain.Song;

import java.util.List;

public interface SongService {

    boolean ifAdd(Song song);

    boolean updateSongMsg(Song song);

    boolean updateSongUrl(Song song);

    boolean updateSongPic(Song song);

    boolean deleteSong(Integer id);

    List<Song> listSongs();

    List<Song> listSongsOfSinger(Integer singerId);

    List<Song> listSongsOfSongs(Integer id);

    List<Song> searachSongLists(String name);

    List<Song> searchSongBySinger(String name);

}
