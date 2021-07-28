package com.photogram.fake.api.modules.services;

import com.photogram.fake.api.modules.entities.domain.Comment;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Comment add(long postId);

    List<Comment> get(long postId);

    Comment update(Comment comment);

    void delete(long commentId);
}
