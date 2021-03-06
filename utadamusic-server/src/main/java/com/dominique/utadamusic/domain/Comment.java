package com.dominique.utadamusic.domain;

import java.util.Date;

public class Comment {

    private Integer id;

    private Integer userId;

    private Integer songId;

    private String content;

    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.type
     *
     * @mbggenerated
     */

    private Integer up;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.id
     *
     * @return the value of comment.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSongId() {
        return songId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.song_id
     *
     * @param songId the value for comment.song_id
     *
     * @mbggenerated
     */
    public void setSongId(Integer songId) {
        this.songId = songId;
    }


    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.content
     *
     * @param content the value for comment.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.create_time
     *
     * @return the value of comment.create_time
     *
     * @mbggenerated
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.create_time
     *
     * @param createTime the value for comment.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.type
     *
     * @return the value of comment.type
     *
     * @mbggenerated
     */

    public Integer getUp() {
        return up;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.up
     *
     * @param up the value for comment.up
     *
     * @mbggenerated
     */
    public void setUp(Integer up) {
        this.up = up;
    }
}