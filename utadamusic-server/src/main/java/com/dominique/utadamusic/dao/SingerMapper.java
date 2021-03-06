package com.dominique.utadamusic.dao;

import com.dominique.utadamusic.domain.Singer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "SingerDao")
public interface SingerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbggenerated
     */
    int insert(Singer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbggenerated
     */
    int insertSelective(Singer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbggenerated
     */
    Singer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Singer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table singer
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Singer record);
    int updateSingerMsg(Singer record);

    int updateSingerImg(Singer record);

    int deleteSinger(Integer id);

    List<Singer> listSingers();

    List<Singer> searachSinger(String name);
}