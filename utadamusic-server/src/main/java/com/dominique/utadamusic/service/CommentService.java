package com.dominique.utadamusic.service;

import com.dominique.utadamusic.domain.Comment;

import java.util.List;

public interface CommentService {

    boolean addComment(Comment comment);

    boolean updateCommentMsg(Comment comment);

    boolean deleteComment(Integer id);

    List<Comment> songListComment();

    List<Comment> songComments(Integer songId);


}
