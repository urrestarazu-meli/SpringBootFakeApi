package com.photogram.fake.api.modules.usecase.impl;

import com.photogram.fake.api.modules.repositories.CommentsRepository;
import com.photogram.fake.api.modules.stereotypes.Usecase;
import com.photogram.fake.api.modules.usecase.DeleteComment;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Usecase
@AllArgsConstructor
public class DefaultDeleteComment implements DeleteComment {
    @Autowired
    CommentsRepository commentsRepository;

    @Override
    public void delete(Model model) {
        commentsRepository.delete(model.getCommentId());
    }
}
