package com.dominique.utadamusic.dao;

import com.dominique.utadamusic.domain.ListSong;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "ListSongDao")
public interface ListSongMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table list_song
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table list_song
     *
     * @mbggenerated
     */
    int insert(ListSong record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table list_song
     *
     * @mbggenerated
     */
    int insertSelective(ListSong record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table list_song
     *
     * @mbggenerated
     */
    ListSong selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table list_song
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ListSong record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table list_song
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ListSong record);
    int updateListSongMsg(ListSong record);

    int deleteListSong(Integer songId);

    List<ListSong> allListSong();

    List<ListSong> listSongsOfSingers(Integer songListId);
}