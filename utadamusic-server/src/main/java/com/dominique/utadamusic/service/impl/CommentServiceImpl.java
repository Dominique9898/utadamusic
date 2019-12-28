package com.dominique.utadamusic.service.impl;
import com.dominique.utadamusic.dao.CommentMapper;
import com.dominique.utadamusic.domain.Comment;
import com.dominique.utadamusic.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.addComment(comment) > 0 ? true:false;
    }

    @Override
    public boolean updateCommentMsg(Comment comment) {
        return commentMapper.updateCommentMsg(comment) >0 ?true:false;
    }

//    删除评论
    @Override
    public boolean deleteComment(Integer id) {
        return commentMapper.deleteComment(id) >0 ?true:false;
    }

    @Override
    public List<Comment> songListComment()
    {
        return commentMapper.songListComment();
    }

    @Override
    public List<Comment> songComments(Integer songId)

    {
        return commentMapper.songComments(songId);
    }

}
