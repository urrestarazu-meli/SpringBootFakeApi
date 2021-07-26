package com.photogram.fake.api.modules.usecase.impl;

import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.repositories.CommentsRepository;
import com.photogram.fake.api.modules.stereotypes.Usecase;
import com.photogram.fake.api.modules.usecase.CreateCommentPost;
import org.springframework.beans.factory.annotation.Autowired;

@Usecase
public class DefaultCreateCommentPost implements CreateCommentPost {
    @Autowired
    CommentsRepository commentsRepository;

    @Override
    public Comment create(Model model) {
        return commentsRepository.create(model.getPostId());
    }
}
