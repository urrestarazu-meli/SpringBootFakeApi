package com.photogram.fake.api.modules.usecase.impl;

import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.entities.domain.User;
import com.photogram.fake.api.modules.repositories.CommentsRepository;
import com.photogram.fake.api.modules.repositories.SessionRepository;
import com.photogram.fake.api.modules.stereotypes.Usecase;
import com.photogram.fake.api.modules.usecase.CreateCommentPost;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Usecase
@AllArgsConstructor
public class DefaultCreateCommentPost implements CreateCommentPost {
    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public Comment create(Model model) {
        User user = sessionRepository.getUser(model.getToken());

        return commentsRepository.create(model.getPostId(), model.getNewComment());
    }
}
