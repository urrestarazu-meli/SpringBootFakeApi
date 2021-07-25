package com.photogram.fake.api.modules.services.impl;

import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.repositories.CommentsRepository;
import com.photogram.fake.api.modules.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCommentService implements CommentService {
    @Autowired
    CommentsRepository commentsRepository;

    @Override
    public Comment add(long postId) {
        return commentsRepository.create(postId);
    }

    @Override
    public List<Comment> get(long postId) {
        return commentsRepository.get(postId);
    }

    @Override
    public Comment update(Comment comment) {
        return commentsRepository.update(comment);
    }

    @Override
    public void delete(long commentId) {
        commentsRepository.delete(commentId);
    }
}
