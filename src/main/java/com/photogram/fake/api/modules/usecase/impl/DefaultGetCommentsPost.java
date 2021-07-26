package com.photogram.fake.api.modules.usecase.impl;

import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.repositories.CommentsRepository;
import com.photogram.fake.api.modules.stereotypes.Usecase;
import com.photogram.fake.api.modules.usecase.GetCommentsPost;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Usecase
public class DefaultGetCommentsPost implements GetCommentsPost {
    @Autowired
    CommentsRepository commentsRepository;

    @Override
    public List<Comment> get(Model model) {
        return commentsRepository.get(model.getPostId());
    }
}
